
package com.project.utils;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class FilePathTest {

    @Test
    public void shouldReturnConfigPropertyFilePath() {
        String expectedPath = "/src/test/resources/com/project/config/config.properties";
        String actualPath = FilePath.getConfigPropertyFilePath();
        assertEquals(expectedPath, actualPath);
    }
}
