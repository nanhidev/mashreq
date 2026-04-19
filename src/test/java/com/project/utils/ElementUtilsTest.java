
package com.project.utils;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.time.LocalTime;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ElementUtilsTest {

    @Mock
    private WebDriver driver;

    @InjectMocks
    private ElementUtils elementUtils;

    @Mock
    private WebElement webElement;

    @Test
    public void shouldReturnRandomTime() {
        LocalTime randomTime = ElementUtils.getRandomTime();
        // Assert that the random time is within valid bounds
        assert (randomTime.getHour() >= 0 && randomTime.getHour() < 24);
        assert (randomTime.getMinute() >= 0 && randomTime.getMinute() < 60);
    }

    @Test
    public void shouldClickElement() {
        when(driver.findElement(any(By.class))).thenReturn(webElement);
        doNothing().when(webElement).click();

        elementUtils.clickElement(webElement);

        verify(webElement, times(1)).click();
    }

    @Test
    public void shouldClearAndSendKeys() throws InterruptedException {
        when(driver.findElement(any(By.class))).thenReturn(webElement);
        doNothing().when(webElement).clear();
        doNothing().when(webElement).sendKeys(anyString());
        when(webElement.isDisplayed()).thenReturn(true);

        elementUtils.clearAndSendKeys(webElement, "test");

        verify(webElement, times(1)).clear();
        verify(webElement, times(1)).sendKeys("test");
    }

    @Test
    public void shouldCheckFieldReadOnly() {
        when(driver.findElement(any(By.class))).thenReturn(webElement);
        when(webElement.getAttribute("readonly")).thenReturn("true");

        ElementUtils.checkFieldReadOnly(driver, By.id("test"));

        verify(webElement, times(1)).getAttribute("readonly");
    }

    @Test
    public void shouldCheckFieldNotReadOnly() {
        when(driver.findElement(any(By.class))).thenReturn(webElement);
        when(webElement.getAttribute("readonly")).thenReturn(null);

        ElementUtils.checkFieldNotReadOnly(driver, By.id("test"));

        verify(webElement, times(1)).getAttribute("readonly");
    }

    @Test
    public void shouldSelectOptionInDropdown() {
        when(driver.findElement(any(By.class))).thenReturn(webElement);
        when(webElement.isDisplayed()).thenReturn(true);
        doNothing().when(webElement).click();

        elementUtils.selectOptionInDropdown(webElement, "Option 1");

        verify(webElement, times(1)).click();
    }

    @Test
    public void shouldVerifyDropdownOptions() {
        when(driver.findElement(any(By.class))).thenReturn(webElement);
        
        List<String> expectedOptions = Collections.singletonList("Option 1");
        when(webElement.getText()).thenReturn("Option 1");

        elementUtils.verifyDropdownOptions(webElement, expectedOptions, "Dropdown options count mismatch");

        verify(webElement, times(1)).getText();
    }

    @Test
    public void shouldCheckIfElementDisplayed() {
        when(driver.findElement(any(By.class))).thenReturn(webElement);
        when(webElement.isDisplayed()).thenReturn(true);

        boolean isDisplayed = elementUtils.isElementDisplayed(webElement);

        assert isDisplayed;
        verify(webElement, times(1)).isDisplayed();
    }

    @Test
    public void shouldCheckIfCheckboxChecked() {
        when(webElement.isSelected()).thenReturn(true);

        boolean isChecked = elementUtils.isCheckboxChecked(webElement);

        assert isChecked;
        verify(webElement, times(1)).isSelected();
    }

    @Test
    public void shouldCheckIfElementSelected() {
        when(webElement.isSelected()).thenReturn(true);

        boolean isSelected = elementUtils.isElementSelected(webElement);

        assert isSelected;
        verify(webElement, times(1)).isSelected();
    }
}
