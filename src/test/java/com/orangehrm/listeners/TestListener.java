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

    private ExtentReports extentReports;
    private ExtentTest extentTest;

    @Override
    public void onStart(ITestContext context) {

        extentReports = ExtentManager.getExtentReports();
    }

    @Override
    public void onTestStart(ITestResult result) {

        extentTest = extentReports.createTest(result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {

        extentTest.pass("Test Passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {

        extentTest.fail(result.getThrowable());

        BaseTest baseTest =
                (BaseTest) result.getInstance();

        String screenshotPath =
                ScreenshotUtils.captureScreenshot(
                        baseTest.getDriver(),
                        result.getName()
                );

        try {

            extentTest.addScreenCaptureFromPath(screenshotPath);

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {

        extentTest.skip("Test Skipped");
    }

    @Override
    public void onFinish(ITestContext context) {

        extentReports.flush();
    }
}