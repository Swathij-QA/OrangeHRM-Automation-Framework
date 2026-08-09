package com.orangehrm.base;

import com.orangehrm.factory.DriverFactory;
import com.orangehrm.utilities.ConfigReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.time.Duration;

public class BaseTest {

    protected WebDriver driver;

    // Logger
    private static final Logger logger =
            LogManager.getLogger(BaseTest.class);

    @BeforeMethod
    public void setUp() {

        ConfigReader.loadProperties();

        String browser = ConfigReader.getProperty("browser");

        logger.info("Starting browser: {}", browser);

        driver = DriverFactory.getDriver(browser);

        driver.manage().window().maximize();

        driver.manage().timeouts().implicitlyWait(
                Duration.ofSeconds(
                        Long.parseLong(
                                ConfigReader.getProperty("implicitWait")
                        )
                )
        );

        driver.get(ConfigReader.getProperty("url"));

        logger.info("Application opened successfully");
    }

    @AfterMethod
    public void tearDown() {

        if (driver != null) {

            logger.info("Closing browser");

            driver.quit();
        }
    }

    public WebDriver getDriver() {
        return driver;
    }
}