
package com.project.utils;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoExtension;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.asserts.SoftAssert;

import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AssertTest {

    @Mock
    private WebDriver driver;

    @Mock
    private WebElement element;

    @InjectMocks
    private Assert assertUtil;

    @Test
    void shouldAssertAll() {
        SoftAssert softAssert = new SoftAssert();
        SoftAssert.set(softAssert);
        assertUtil.assertAll();
        // Verify softAssert.assertAll() is called
        verify(softAssert).assertAll();
    }

    @Test
    void shouldResetSoftAssert() {
        assertUtil.resetSoftAssert();
        // Verify softAssert.remove() is called
        verify(softAssert).remove();
    }

    @Test
    void shouldAssertTrue() {
        assertUtil.assertTrue(true, "Condition should be true");
        assertUtil.assertTrue(false, "Condition should fail");
    }

    @Test
    void shouldAssertFalse() {
        assertUtil.assertFalse(false, "Condition should be false");
        assertUtil.assertFalse(true, "Condition should fail");
    }

    @Test
    void shouldAssertEquals() {
        assertUtil.assertEquals("expected", "expected", "Values should be equal");
        assertUtil.assertEquals("actual", "expected", "Values should fail");
    }

    @Test
    void shouldAssertNotEquals() {
        assertUtil.assertNotEquals("actual", "expected", "Values should not be equal");
        assertUtil.assertNotEquals("expected", "expected", "Values should fail");
    }

    @Test
    void shouldAssertNotNull() {
        assertUtil.assertNotNull(new Object(), "Object should not be null");
        assertUtil.assertNotNull(null, "Object should fail");
    }

    @Test
    void shouldAssertNull() {
        assertUtil.assertNull(null, "Object should be null");
        assertUtil.assertNull(new Object(), "Object should fail");
    }

    @Test
    void shouldAssertContains() {
        assertUtil.assertContains("Hello World", "World", "String should contain substring");
        assertUtil.assertContains("Hello", "World", "String should fail");
    }

    @Test
    void shouldAssertNotContains() {
        assertUtil.assertNotContains("Hello World", "World", "String should not contain substring");
        assertUtil.assertNotContains("Hello", "World", "String should fail");
    }

    @Test
    void shouldAssertElementVisibleByElement() {
        when(driver.findElement(any(By.class))).thenReturn(element);
        assertUtil.assertElementVisible(driver, element, "Element should be visible");
    }

    @Test
    void shouldAssertElementVisibleByLocator() {
        By locator = By.id("test");
        when(driver.findElement(locator)).thenReturn(element);
        assertUtil.assertElementVisible(driver, locator, "Element should be visible");
    }

    @Test
    void shouldAssertElementNotVisibleByElement() {
        when(driver.findElement(any(By.class))).thenReturn(element);
        assertUtil.assertElementNotVisible(driver, element, "Element should not be visible");
    }

    @Test
    void shouldAssertElementNotVisibleByLocator() {
        By locator = By.id("test");
        when(driver.findElement(locator)).thenReturn(element);
        assertUtil.assertElementNotVisible(driver, locator, "Element should not be visible");
    }

    @Test
    void shouldAssertElementEnabled() {
        when(element.isEnabled()).thenReturn(true);
        assertUtil.assertElementEnabled(driver, element, "Element should be enabled");
    }

    @Test
    void shouldAssertElementDisabled() {
        when(element.isEnabled()).thenReturn(false);
        assertUtil.assertElementDisabled(driver, element, "Element should be disabled");
    }

    @Test
    void shouldAssertElementClickable() {
        when(element.isEnabled()).thenReturn(true);
        assertUtil.assertElementClickable(driver, element, "Element should be clickable");
    }

    @Test
    void shouldAssertElementTextByElement() {
        when(element.getText()).thenReturn("Expected Text");
        assertUtil.assertElementText(driver, element, "Expected Text", "Element should have correct text");
    }

    @Test
    void shouldAssertElementTextByLocator() {
        By locator = By.id("test");
        when(driver.findElement(locator)).thenReturn(element);
        when(element.getText()).thenReturn("Expected Text");
        assertUtil.assertElementText(driver, locator, "Expected Text", "Element should have correct text");
    }

    @Test
    void shouldAssertElementPresent() {
        By locator = By.id("test");
        when(driver.findElement(locator)).thenReturn(element);
        assertUtil.assertElementPresent(driver, locator, "Element should be present in DOM");
    }

    @Test
    void shouldAssertElementNotPresent() {
        By locator = By.id("test");
        when(driver.findElements(locator)).thenReturn(Collections.emptyList());
        assertUtil.assertElementNotPresent(driver, locator, "Element should not be present in DOM");
    }

    @Test
    void shouldAssertListSize() {
        List<Object> list = Collections.singletonList(new Object());
        assertUtil.assertListSize(list, 1, "List should have size 1");
    }

    @Test
    void shouldAssertListNotEmpty() {
        List<Object> list = Collections.singletonList(new Object());
        assertUtil.assertListNotEmpty(list, "List should not be empty");
    }

    @Test
    void shouldAssertListEmpty() {
        List<Object> list = Collections.emptyList();
        assertUtil.assertListEmpty(list, "List should be empty");
    }

    @Test
    void shouldAssertListContains() {
        List<Object> list = Collections.singletonList(new Object());
        assertUtil.assertListContains(list, new Object(), "List should contain the item");
    }
}
