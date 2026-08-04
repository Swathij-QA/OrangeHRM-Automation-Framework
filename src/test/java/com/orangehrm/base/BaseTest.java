package com.orangehrm.base;

import com.orangehrm.factory.DriverFactory;
import com.orangehrm.utilities.ConfigReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.time.Duration;

public class BaseTest {
    protected WebDriver driver;

    @BeforeMethod
    public void setUp() {

        ConfigReader.loadProperties();

        String browser = ConfigReader.getProperty("browser");

        driver = DriverFactory.getDriver(browser);

        driver.manage().window().maximize();

        driver.manage().timeouts()
                .implicitlyWait(Duration.ofSeconds(
                        Long.parseLong(
                                ConfigReader.getProperty("implicitWait")
                        )));

        driver.get(ConfigReader.getProperty("url"));
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
