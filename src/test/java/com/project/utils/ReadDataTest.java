
package com.project.utils;

import org.apache.poi.ss.usermodel.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ReadDataTest {

    @Mock
    private Workbook workbook;

    @Mock
    private Sheet sheet;

    @Mock
    private Row row;

    @Mock
    private Cell keyCell;

    @Mock
    private Cell valueCell;

    @InjectMocks
    private ReadData readData;

    @Test
    void shouldReturnValueWhenKeyExists() throws IOException {
        String key = "testKey";
        String expectedValue = "testValue";
        
        when(workbook.getSheet(ReadData.DEFAULT_SHEET)).thenReturn(sheet);
        when(sheet.iterator()).thenReturn(new RowIterator(keyCell, valueCell, expectedValue));
        when(keyCell.getCellType()).thenReturn(CellType.STRING);
        when(valueCell.getCellType()).thenReturn(CellType.STRING);
        when(keyCell.getStringCellValue()).thenReturn(key);
        when(valueCell.getStringCellValue()).thenReturn(expectedValue);

        Optional<String> result = ReadData.readData(key);

        assertTrue(result.isPresent());
        assertEquals(expectedValue, result.get());
    }

    @Test
    void shouldReturnEmptyWhenKeyDoesNotExist() throws IOException {
        String key = "nonExistentKey";

        when(workbook.getSheet(ReadData.DEFAULT_SHEET)).thenReturn(sheet);
        when(sheet.iterator()).thenReturn(new RowIterator(keyCell, null, null));
        when(keyCell.getCellType()).thenReturn(CellType.STRING);
        when(keyCell.getStringCellValue()).thenReturn("someOtherKey");

        Optional<String> result = ReadData.readData(key);

        assertFalse(result.isPresent());
    }

    @Test
    void shouldThrowExceptionWhenKeyIsNull() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            ReadData.readData(null);
        });
        assertEquals("Key must not be null or blank", exception.getMessage());
    }

    @Test
    void shouldWriteDataWhenKeyIsNew() throws IOException {
        String key = "newKey";
        String value = "newValue";

        when(workbook.getSheet(ReadData.DEFAULT_SHEET)).thenReturn(sheet);
        when(sheet.getLastRowNum()).thenReturn(-1);
        when(sheet.createRow(0)).thenReturn(row);
        when(row.createCell(0)).thenReturn(keyCell);
        when(row.createCell(1)).thenReturn(valueCell);

        ReadData.writeData(key, value);

        verify(keyCell).setCellValue(key);
        verify(valueCell).setCellValue(value);
    }

    @Test
    void shouldUpdateValueWhenKeyExists() throws IOException {
        String key = "existingKey";
        String value = "updatedValue";

        when(workbook.getSheet(ReadData.DEFAULT_SHEET)).thenReturn(sheet);
        when(sheet.iterator()).thenReturn(new RowIterator(keyCell, valueCell, "existingValue"));
        when(keyCell.getCellType()).thenReturn(CellType.STRING);
        when(valueCell.getCellType()).thenReturn(CellType.STRING);
        when(keyCell.getStringCellValue()).thenReturn(key);

        ReadData.writeData(key, value);

        verify(valueCell).setCellValue(value);
    }

    @Test
    void shouldThrowExceptionWhenWritingNullKey() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            ReadData.writeData(null, "value");
        });
        assertEquals("Key must not be null or blank", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenWritingNullValue() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            ReadData.writeData("key", null);
        });
        assertEquals("Value must not be null", exception.getMessage());
    }

    @Test
    void shouldReturnCellDataWhenCellExists() throws IOException {
        int rowNum = 0;
        int colNum = 0;
        String expectedValue = "cellValue";

        when(workbook.getSheet(ReadData.DEFAULT_SHEET)).thenReturn(sheet);
        when(sheet.getRow(rowNum)).thenReturn(row);
        when(row.getCell(colNum)).thenReturn(valueCell);
        when(valueCell.getCellType()).thenReturn(CellType.STRING);
        when(valueCell.getStringCellValue()).thenReturn(expectedValue);

        String result = ReadData.getCellData(rowNum, colNum);

        assertEquals(expectedValue, result);
    }

    @Test
    void shouldReturnEmptyStringWhenCellDoesNotExist() throws IOException {
        int rowNum = 0;
        int colNum = 0;

        when(workbook.getSheet(ReadData.DEFAULT_SHEET)).thenReturn(sheet);
        when(sheet.getRow(rowNum)).thenReturn(row);
        when(row.getCell(colNum)).thenReturn(null);

        String result = ReadData.getCellData(rowNum, colNum);

        assertEquals("", result);
    }

    @Test
    void shouldThrowExceptionWhenRowNumIsNegative() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            ReadData.getCellData(-1, 0);
        });
        assertEquals("Row and column indices must be non-negative (got row=-1, col=0)", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenColNumIsNegative() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            ReadData.getCellData(0, -1);
        });
        assertEquals("Row and column indices must be non-negative (got row=0, col=-1)", exception.getMessage());
    }

    private class RowIterator implements java.util.Iterator<Row> {
        private final Cell[] cells;
        private int index = 0;

        RowIterator(Cell... cells) {
            this.cells = cells;
        }

        @Override
        public boolean hasNext() {
            return index < cells.length && cells[index] != null;
        }

        @Override
        public Row next() {
            return row;
        }
    }
}
