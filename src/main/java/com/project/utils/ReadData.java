package com.project.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.*;

import java.io.*;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.file.*;
import java.util.Optional;

public class ReadData {

    private static final Logger logger = LogManager.getLogger(ReadData.class);

    // -------------------------------------------------------------------------
    // CONFIG — override EXCEL_DATA_PATH env var in containers / CI
    // -------------------------------------------------------------------------
    private static final String DEFAULT_SHEET = "Sheet1";

    private static String getExcelPath() {
        return Optional.ofNullable(System.getenv("EXCEL_DATA_PATH"))
                .orElse(Paths.get(System.getProperty("user.dir"),
                        "LinqxData", "linqxdata.xlsx").toString());
    }

    private static final DataFormatter FORMATTER = new DataFormatter();

    // =========================================================================
    // PRIVATE HELPERS
    // =========================================================================

    /**
     * Opens and fully loads the workbook into memory, then closes the
     * FileInputStream immediately — avoids keeping a file handle open and
     * eliminates the Windows file-lock conflict during writes.
     */
    private static Workbook getWorkbook() throws IOException {
        Path path = Paths.get(getExcelPath());

        if (!Files.exists(path)) {
            throw new FileNotFoundException("Excel file not found at: " + path);
        }

        // try-with-resources ensures FIS is closed even if WorkbookFactory throws
        try (FileInputStream fis = new FileInputStream(path.toFile())) {
            return WorkbookFactory.create(fis); // POI buffers entirely in memory
        }
    }

    /**
     * Resolves a sheet by name; throws a clear IllegalStateException if absent.
     */
    private static Sheet requireSheet(Workbook workbook, String sheetName) {
        Sheet sheet = workbook.getSheet(sheetName);
        if (sheet == null) {
            throw new IllegalStateException("Sheet not found in workbook: " + sheetName);
        }
        return sheet;
    }

    /**
     * Writes the workbook to a temp file then atomically replaces the target,
     * combined with a FileLock for cross-process safety.
     */
    private static void writeWorkbookSafely(Workbook workbook) throws IOException {
        String excelPath = getExcelPath();
        Path target = Paths.get(excelPath);
        Path temp = Files.createTempFile(target.getParent(), "excel-write-", ".tmp");

        try {
            // 1. Write to temp file first (no lock needed — temp is private)
            try (FileOutputStream fos = new FileOutputStream(temp.toFile())) {
                workbook.write(fos);
            }

            // 2. Acquire cross-process lock then atomically replace target
            try (FileChannel channel = FileChannel.open(target,
                    StandardOpenOption.WRITE, StandardOpenOption.CREATE);
                 FileLock ignored = channel.lock()) {

                Files.move(temp, target,
                        StandardCopyOption.REPLACE_EXISTING,
                        StandardCopyOption.ATOMIC_MOVE);
            }

            logger.info("Workbook saved successfully → {}", excelPath);

        } catch (AtomicMoveNotSupportedException e) {
            // Fallback for file systems that don't support atomic move
            logger.warn("Atomic move not supported, falling back to regular replace");
            Files.move(temp, target, StandardCopyOption.REPLACE_EXISTING);
        } finally {
            // Clean up temp file if move failed
            Files.deleteIfExists(temp);
        }
    }

    // =========================================================================
    // PUBLIC API
    // =========================================================================

    // -------------------------------------------------------------------------
    // READ — key → Optional<value>
    // -------------------------------------------------------------------------

    /**
     * Looks up a key in column A and returns the corresponding column B value.
     *
     * @param key case-insensitive key to search for
     * @return Optional containing the value, or empty if key is not found
     * @throws RuntimeException wrapping any IO failure
     */
    public static synchronized Optional<String> readData(String key) {

        if (key == null || key.isBlank()) {
            throw new IllegalArgumentException("Key must not be null or blank");
        }

        try (Workbook workbook = getWorkbook()) {

            Sheet sheet = requireSheet(workbook, DEFAULT_SHEET);

            for (Row row : sheet) {
                Cell keyCell = row.getCell(0);
                if (keyCell != null &&
                        FORMATTER.formatCellValue(keyCell).equalsIgnoreCase(key)) {

                    Cell valueCell = row.getCell(1);
                    String value = (valueCell != null)
                            ? FORMATTER.formatCellValue(valueCell)
                            : "";

                    logger.info("Found → {} = {}", key, value);
                    return Optional.of(value);
                }
            }

            logger.warn("Key not found: {}", key);
            return Optional.empty();

        } catch (IllegalStateException e) {
            throw e; // re-throw config/sheet errors as-is
        } catch (Exception e) {
            logger.error("Error reading Excel for key: {}", key, e);
            throw new RuntimeException("Excel read failed for key: " + key, e);
        }
    }

    // -------------------------------------------------------------------------
    // WRITE — upsert key/value
    // -------------------------------------------------------------------------

    /**
     * Updates an existing key's value, or appends a new row if the key is absent.
     *
     * @param key   column A key (case-insensitive match)
     * @param value column B value to write
     * @throws RuntimeException wrapping any IO failure
     */
    public static synchronized void writeData(String key, String value) {

        if (key == null || key.isBlank()) {
            throw new IllegalArgumentException("Key must not be null or blank");
        }
        if (value == null) {
            throw new IllegalArgumentException("Value must not be null");
        }

        try (Workbook workbook = getWorkbook()) {

            Sheet sheet = workbook.getSheet(DEFAULT_SHEET);
            if (sheet == null) {
                sheet = workbook.createSheet(DEFAULT_SHEET);
                logger.info("Created new sheet: {}", DEFAULT_SHEET);
            }

            boolean found = false;

            for (Row row : sheet) {
                Cell keyCell = row.getCell(0);
                if (keyCell != null &&
                        FORMATTER.formatCellValue(keyCell).equalsIgnoreCase(key)) {

                    row.getCell(1, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK)
                            .setCellValue(value);

                    found = true;
                    logger.info("Updated → {} = {}", key, value);
                    break;
                }
            }

            if (!found) {
                int newRowNum = sheet.getLastRowNum() + 1;
                Row newRow = sheet.createRow(newRowNum);
                newRow.createCell(0).setCellValue(key);
                newRow.createCell(1).setCellValue(value);
                logger.info("Added → {} = {}", key, value);
            }

            // Atomic write-back with cross-process lock
            writeWorkbookSafely(workbook);

        } catch (IllegalArgumentException | IllegalStateException e) {
            throw e;
        } catch (Exception e) {
            logger.error("Error writing Excel for key: {}", key, e);
            throw new RuntimeException("Excel write failed for key: " + key, e);
        }
    }

    // -------------------------------------------------------------------------
    // GENERIC CELL READ — by row/column index
    // -------------------------------------------------------------------------

    /**
     * Reads a cell value by zero-based row and column indices.
     *
     * @param rowNum zero-based row index
     * @param colNum zero-based column index
     * @return cell value as String, or empty string if the cell is blank/absent
     * @throws RuntimeException wrapping any IO failure
     */
    public static synchronized String getCellData(int rowNum, int colNum) {

        if (rowNum < 0 || colNum < 0) {
            throw new IllegalArgumentException(
                    "Row and column indices must be non-negative (got row=" + rowNum + ", col=" + colNum + ")");
        }

        try (Workbook workbook = getWorkbook()) {

            Sheet sheet = requireSheet(workbook, DEFAULT_SHEET);

            Row row = sheet.getRow(rowNum);
            if (row == null) {
                logger.warn("Row {} not found in sheet '{}'", rowNum, DEFAULT_SHEET);
                return "";
            }

            Cell cell = row.getCell(colNum);
            if (cell == null) {
                logger.warn("Cell [{}, {}] not found in sheet '{}'", rowNum, colNum, DEFAULT_SHEET);
                return "";
            }

            return FORMATTER.formatCellValue(cell);

        } catch (IllegalArgumentException | IllegalStateException e) {
            throw e;
        } catch (Exception e) {
            logger.error("Error reading cell [{}, {}]", rowNum, colNum, e);
            throw new RuntimeException(
                    "Cell read failed at [" + rowNum + ", " + colNum + "]", e);
        }
    }
}