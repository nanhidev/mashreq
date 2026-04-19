
package com.project.utils;

import static org.mockito.Mockito.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoExtension;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

@ExtendWith(MockitoExtension.class)
public class WaitUtilsTest {

    @Mock
    private WebDriver driver;

    @Mock
    private WebElement element;

    @Mock
    private By locator;

    @Mock
    private Alert alert;

    @InjectMocks
    private WaitUtils waitUtils;

    @Test
    public void shouldReturnElementWhenWaitForVisibilityWithElement() {
        when(driver).thenReturn(driver);
        when(driver.findElement(locator)).thenReturn(element);
        when(driver.findElements(locator)).thenReturn(List.of(element));
        when(driver.getPageSource()).thenReturn("some source");

        waitUtils.waitForVisibility(driver, element);
        
        verify(driver).findElement(locator);
    }

    @Test
    public void shouldReturnElementWhenWaitForVisibilityWithLocator() {
        when(driver.findElement(locator)).thenReturn(element);

        waitUtils.waitForVisibility(driver, locator);
        
        verify(driver).findElement(locator);
    }

    @Test
    public void shouldReturnElementWhenWaitForClickableWithElement() {
        when(driver).thenReturn(driver);
        when(driver.findElement(locator)).thenReturn(element);

        waitUtils.waitForClickable(driver, element);
        
        verify(driver).findElement(locator);
    }

    @Test
    public void shouldReturnElementWhenWaitForClickableWithLocator() {
        when(driver.findElement(locator)).thenReturn(element);

        waitUtils.waitForClickable(driver, locator);
        
        verify(driver).findElement(locator);
    }

    @Test
    public void shouldReturnTrueWhenWaitForInvisibilityWithElement() {
        when(driver).thenReturn(driver);
        when(driver.findElement(locator)).thenReturn(element);

        waitUtils.waitForInvisibility(driver, element);
        
        verify(driver).findElement(locator);
    }

    @Test
    public void shouldReturnTrueWhenWaitForInvisibilityWithLocator() {
        when(driver.findElement(locator)).thenReturn(element);

        waitUtils.waitForInvisibility(driver, locator);
        
        verify(driver).findElement(locator);
    }

    @Test
    public void shouldReturnTrueWhenWaitForTextToBeWithElement() {
        when(driver).thenReturn(driver);
        when(driver.findElement(locator)).thenReturn(element);
        when(element.getText()).thenReturn("expected text");

        waitUtils.waitForTextToBe(driver, element, "expected text");
        
        verify(driver).findElement(locator);
    }

    @Test
    public void shouldReturnTrueWhenWaitForTextToBeWithLocator() {
        when(driver.findElement(locator)).thenReturn(element);
        when(element.getText()).thenReturn("expected text");

        waitUtils.waitForTextToBe(driver, locator, "expected text");
        
        verify(driver).findElement(locator);
    }

    @Test
    public void shouldReturnAlertWhenWaitForAlert() {
        when(driver).thenReturn(driver);
        when(driver.switchTo().alert()).thenReturn(alert);

        waitUtils.waitForAlert(driver);
        
        verify(driver).switchTo();
    }

    @Test
    public void shouldReturnWebElementWhenWaitForFrameAndSwitchWithLocator() {
        when(driver).thenReturn(driver);
        when(driver.findElement(locator)).thenReturn(element);

        waitUtils.waitForFrameAndSwitch(driver, locator);
        
        verify(driver).findElement(locator);
    }
}
