
package com.project.utils;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoExtension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import io.cucumber.java.Scenario;

import java.io.File;
import java.io.IOException;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CommonUtilsTest {

    @Mock
    private WebDriver driver;

    @Mock
    private WebElement element;

    @Mock
    private Scenario scenario;

    @InjectMocks
    private CommonUtils commonUtils;

    @Test
    public void shouldReturnEmailWithTimestamp() {
        String email = CommonUtils.getEmailWithTimeStamp();
        // Assert that email is generated correctly (basic check, since we can't predict the exact string)
        assertTrue(email.startsWith("newemail") && email.endsWith("@gmail.com"));
    }

    @Test
    public void shouldCaptureScreenshot() {
        when(driver).thenReturn(mock(TakesScreenshot.class));
        when(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)).thenReturn(new byte[0]);

        byte[] result = CommonUtils.takeScreenShot(scenario, driver, "testScenario");

        assertNotNull(result);
        verify(((TakesScreenshot) driver), times(1)).getScreenshotAs(OutputType.BYTES);
    }

    @Test
    public void shouldScrollToBottom() {
        when(driver).thenReturn(mock(JavascriptExecutor.class));
        when(((JavascriptExecutor) driver).executeScript("return document.body.scrollHeight")).thenReturn(100L);

        CommonUtils.scrollToBottom(driver);

        verify((JavascriptExecutor) driver, atLeast(1)).executeScript("window.scrollTo(0, document.body.scrollHeight);");
    }

    @Test
    public void shouldScrollToTop() {
        when(driver).thenReturn(mock(JavascriptExecutor.class));
        when(((JavascriptExecutor) driver).executeScript("return window.pageYOffset")).thenReturn(100L);

        CommonUtils.scrollToTop(driver);

        verify((JavascriptExecutor) driver, atLeast(1)).executeScript("window.scrollBy(0, -1000);");
    }

    @Test
    public void shouldVerifyDropdownSelectedOption() {
        Select mockSelect = mock(Select.class);
        WebElement mockOption = mock(WebElement.class);
        
        when(mockSelect.getFirstSelectedOption()).thenReturn(mockOption);
        when(mockSelect.getOptions()).thenReturn(Arrays.asList(mockOption));
        when(mockOption.equals(mockOption)).thenReturn(true);

        CommonUtils.DropdownSelectedOptionVerification(mockSelect, 0, "Option not selected correctly");

        verify(mockSelect, times(1)).getFirstSelectedOption();
        verify(mockSelect, times(1)).getOptions();
    }

    @Test
    public void shouldCaptureScreenshotAndReturnPath() throws IOException {
        when(driver).thenReturn(mock(TakesScreenshot.class));
        File mockFile = mock(File.class);
        when(((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE)).thenReturn(mockFile);

        String result = CommonUtils.captureScreenshot(driver, "testScreenshot");

        assertTrue(result.endsWith("/screenshots/testScreenshot.png"));
        verify(mockFile, times(1)).copy(mockFile, new File(System.getProperty("user.dir") + "/screenshots/testScreenshot.png"));
    }

    @Test
    public void shouldSwitchToNewWindow() {
        when(driver.getWindowHandle()).thenReturn("currentWindow");
        when(driver.getWindowHandles()).thenReturn(new HashSet<>(Arrays.asList("window1", "currentWindow")));

        CommonUtils.switchToNewWindow(driver);

        verify(driver, times(1)).switchTo().window("window1");
    }

    @Test
    public void shouldSwitchToParentWindow() {
        when(driver.getWindowHandles()).thenReturn(new HashSet<>(Arrays.asList("window1", "window2")));

        CommonUtils.switchToParentWindow(driver);

        verify(driver, times(1)).switchTo().window("window1");
    }

    @Test
    public void shouldUploadFile() {
        String filePath = "path/to/file";
        CommonUtils.uploadFile(element, filePath);

        verify(element, times(1)).sendKeys(filePath);
    }

    @Test
    public void shouldCheckIfAlertIsPresent() {
        when(driver.switchTo().alert()).thenReturn(mock(Alert.class));

        boolean result = CommonUtils.isAlertPresent(driver);

        assertTrue(result);
    }

    @Test
    public void shouldAcceptAlert() {
        Alert mockAlert = mock(Alert.class);
        when(driver.switchTo().alert()).thenReturn(mockAlert);

        CommonUtils.acceptAlert(driver);

        verify(mockAlert, times(1)).accept();
    }

    @Test
    public void shouldDismissAlert() {
        Alert mockAlert = mock(Alert.class);
        when(driver.switchTo().alert()).thenReturn(mockAlert);

        CommonUtils.dismissAlert(driver);

        verify(mockAlert, times(1)).dismiss();
    }

    @Test
    public void shouldWaitForElementVisible() {
        when(driver).thenReturn(mock(WebDriverWait.class));
        
        CommonUtils.waitForElementVisible(driver, element);

        verify(driver, times(1)).until(any());
    }

    @Test
    public void shouldWaitForElementClickable() {
        when(driver).thenReturn(mock(WebDriverWait.class));

        CommonUtils.waitForElementClickable(driver, element);

        verify(driver, times(1)).until(any());
    }

    @Test
    public void shouldWaitForPageLoad() {
        when(driver).thenReturn(mock(WebDriverWait.class));

        CommonUtils.waitForPageLoad(driver);

        verify(driver, times(1)).until(any());
    }

    @Test
    public void shouldRefreshPage() {
        CommonUtils.refreshPage(driver);

        verify(driver, times(1)).navigate().refresh();
    }

    @Test
    public void shouldMaximizeWindow() {
        CommonUtils.maximizeWindow(driver);

        verify(driver, times(1)).manage().window().maximize();
    }

    @Test
    public void shouldSleep() throws InterruptedException {
        CommonUtils.sleep(1);

        // Verify that the thread slept for the expected duration
        verify(Thread.class, times(1)).sleep(1000);
    }
}
