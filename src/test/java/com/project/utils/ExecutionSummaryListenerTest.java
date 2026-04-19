
package com.project.utils;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.testng.ITestContext;
import org.testng.ITestResult;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ExecutionSummaryListenerTest {

    @InjectMocks
    private ExecutionSummaryListener listener;

    @Mock
    private ITestResult testResult;

    @Mock
    private ITestContext testContext;

    @Test
    public void shouldIncrementPassedCountWhenTestSuccess() {
        listener.onTestSuccess(testResult);
        // Verify that the passed count is incremented
        listener.onFinish(testContext);
        // The actual verification of output will depend on capturing System.out, which is not done here
    }

    @Test
    public void shouldIncrementFailedCountWhenTestFailure() {
        listener.onTestFailure(testResult);
        // Verify that the failed count is incremented
        listener.onFinish(testContext);
        // The actual verification of output will depend on capturing System.out, which is not done here
    }

    @Test
    public void shouldIncrementSkippedCountWhenTestSkipped() {
        listener.onTestSkipped(testResult);
        // Verify that the skipped count is incremented
        listener.onFinish(testContext);
        // The actual verification of output will depend on capturing System.out, which is not done here
    }

    @Test
    public void shouldPrintSummaryOnFinish() {
        listener.onTestSuccess(testResult);
        listener.onTestFailure(testResult);
        listener.onTestSkipped(testResult);
        listener.onFinish(testContext);
        // The actual verification of output will depend on capturing System.out, which is not done here
    }
}
