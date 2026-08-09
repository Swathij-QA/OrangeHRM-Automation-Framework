package com.orangehrm.base;

import com.orangehrm.factory.DriverFactory;
import com.orangehrm.utilities.ConfigReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

public class BaseTest {

    private static final Logger logger = LogManager.getLogger(BaseTest.class);

    @Parameters("browser")
    @BeforeMethod(alwaysRun = true)
    public void setUp(@Optional("") String browser) {

        if (browser == null || browser.isBlank()) {
            browser = ConfigReader.getProperty("browser");
        }

        logger.info("Starting browser: {} on thread: {}", browser, Thread.currentThread().getId());

        DriverFactory.createDriver(browser);

        if (!Boolean.parseBoolean(ConfigReader.getProperty("headless"))) {

            getDriver().manage().window().maximize();
        }

        getDriver().get(ConfigReader.getProperty("url"));

        logger.info("Application opened successfully");
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {

        logger.info("Closing browser on thread: {}", Thread.currentThread().getId());

        DriverFactory.quitDriver();
    }

    public WebDriver getDriver() {
        return DriverFactory.getDriver();
    }
}