
package com.project.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

@ExtendWith(MockitoExtension.class)
public class ConfigReaderTest {

    @InjectMocks
    private ConfigReader configReader;

    @Mock
    private Properties properties;

    @BeforeEach
    public void setUp() throws IOException {
        MockitoAnnotations.initMocks(this);
        // Mock the properties to return predetermined values
        when(properties.getProperty("browser")).thenReturn("chrome");
        when(properties.getProperty("url")).thenReturn("http://localhost");
        when(properties.getProperty("implicit.wait")).thenReturn("10");
        when(properties.getProperty("explicit.wait")).thenReturn("20");
        when(properties.getProperty("browser.headless")).thenReturn("false");
        when(properties.getProperty("environment")).thenReturn("test");
        when(properties.getProperty("framework.type")).thenReturn("cucumber-junit");
        when(properties.getProperty("test.runner")).thenReturn("junit");
        when(properties.getProperty("cucumber.tags")).thenReturn("@smoke");
        when(properties.getProperty("cucumber.features.path")).thenReturn("src/test/resources/features");
        when(properties.getProperty("cucumber.glue.path")).thenReturn("com.project.stepdefinitions");
        when(properties.getProperty("report.path")).thenReturn("target/reports");
        when(properties.getProperty("screenshot.on.failure")).thenReturn("true");
        when(properties.getProperty("playwright.enabled")).thenReturn("false");
        when(properties.getProperty("visual.regression.enabled")).thenReturn("false");
        when(properties.getProperty("playwright.browser")).thenReturn("chromium");
    }

    @Test
    public void shouldReturnBrowserWhenCalled() {
        assertEquals("chrome", configReader.getBrowser());
    }

    @Test
    public void shouldReturnBaseUrlWhenCalled() {
        assertEquals("http://localhost", configReader.getBaseUrl());
    }

    @Test
    public void shouldReturnImplicitWaitWhenCalled() {
        assertEquals(10, configReader.getImplicitWait());
    }

    @Test
    public void shouldReturnExplicitWaitWhenCalled() {
        assertEquals(20, configReader.getExplicitWait());
    }

    @Test
    public void shouldReturnIsHeadlessWhenCalled() {
        assertTrue(!configReader.isHeadless());
    }

    @Test
    public void shouldReturnEnvironmentWhenCalled() {
        assertEquals("test", configReader.getEnvironment());
    }

    @Test
    public void shouldReturnFrameworkTypeWhenCalled() {
        assertEquals("cucumber-junit", configReader.getFrameworkType());
    }

    @Test
    public void shouldReturnTestRunnerWhenCalled() {
        assertEquals("junit", configReader.getTestRunner());
    }

    @Test
    public void shouldReturnCucumberTagsWhenCalled() {
        assertEquals("@smoke", configReader.getCucumberTags());
    }

    @Test
    public void shouldReturnFeaturePathWhenCalled() {
        assertEquals("src/test/resources/features", configReader.getFeaturePath());
    }

    @Test
    public void shouldReturnGluePathWhenCalled() {
        assertEquals("com.project.stepdefinitions", configReader.getGluePath());
    }

    @Test
    public void shouldReturnReportPathWhenCalled() {
        assertEquals("target/reports", configReader.getReportPath());
    }

    @Test
    public void shouldReturnIsScreenshotOnFailureWhenCalled() {
        assertTrue(configReader.isScreenshotOnFailure());
    }

    @Test
    public void shouldReturnIsPlaywrightEnabledWhenCalled() {
        assertTrue(!configReader.isPlaywrightEnabled());
    }

    @Test
    public void shouldReturnIsVisualRegressionEnabledWhenCalled() {
        assertTrue(!configReader.isVisualRegressionEnabled());
    }

    @Test
    public void shouldReturnPlaywrightBrowserWhenCalled() {
        assertEquals("chromium", configReader.getPlaywrightBrowser());
    }
}
