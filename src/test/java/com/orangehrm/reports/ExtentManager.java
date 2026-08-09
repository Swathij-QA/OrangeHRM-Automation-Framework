package com.orangehrm.reports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.orangehrm.constants.FrameworkConstants;

public final class ExtentManager {

    private static ExtentReports extentReports;

    private ExtentManager() {
    }

    public static synchronized ExtentReports getExtentReports() {

        if (extentReports == null) {

            ExtentSparkReporter sparkReporter = new ExtentSparkReporter(FrameworkConstants.REPORT_PATH);

            sparkReporter.config().setDocumentTitle("OrangeHRM Automation Report");

            sparkReporter.config().setReportName("OrangeHRM Regression Execution");

            extentReports = new ExtentReports();

            extentReports.attachReporter(sparkReporter);

            extentReports.setSystemInfo("Framework", "Selenium Java TestNG");

            extentReports.setSystemInfo("Environment", "QA");
        }

        return extentReports;
    }
}