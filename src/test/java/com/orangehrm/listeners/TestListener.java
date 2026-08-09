package com.orangehrm.listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.orangehrm.base.BaseTest;
import com.orangehrm.reports.ExtentManager;
import com.orangehrm.utilities.ScreenshotUtils;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {

    private static final ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();
    private static ExtentReports extentReports;

    @Override
    public void onStart(ITestContext context) {

        extentReports = ExtentManager.getExtentReports();
    }

    @Override
    public void onTestStart(ITestResult result) {

        String testName = result.getMethod().getMethodName();

        ExtentTest test = extentReports.createTest(testName);

        extentTest.set(test);

        extentTest.get().info("Test started on thread: " + Thread.currentThread().getId());
    }

    @Override
    public void onTestSuccess(ITestResult result) {

        extentTest.get().pass("Test Passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {

        extentTest.get().fail(result.getThrowable());

        Object testClass = result.getInstance();

        if (testClass instanceof BaseTest baseTest) {

            if (baseTest.getDriver() != null) {

                String screenshotPath = ScreenshotUtils.captureScreenshot(baseTest.getDriver(), result.getName());

                try {

                    extentTest.get().addScreenCaptureFromPath(screenshotPath);

                } catch (Exception e) {

                    extentTest.get().warning("Unable to attach screenshot: " + e.getMessage());
                }
            }
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {

        extentTest.get().skip("Test Skipped");
    }

    @Override
    public void onFinish(ITestContext context) {

        if (extentReports != null) {

            extentReports.flush();
        }

        extentTest.remove();
    }
}