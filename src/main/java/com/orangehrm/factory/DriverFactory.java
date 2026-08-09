package com.orangehrm.factory;

import com.orangehrm.utilities.ConfigReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class DriverFactory {

    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static void createDriver(String browser) {

        boolean headless = Boolean.parseBoolean(ConfigReader.getProperty("headless"));

        switch (browser.toLowerCase()) {

            case "chrome":

                ChromeOptions chromeOptions = new ChromeOptions();

                chromeOptions.addArguments("--disable-notifications", "--disable-popup-blocking");

                if (headless) {

                    chromeOptions.addArguments("--headless=new", "--window-size=1920,1080");
                }

                driver.set(new ChromeDriver(chromeOptions));

                break;


            case "edge":

                EdgeOptions edgeOptions = new EdgeOptions();

                edgeOptions.addArguments("--disable-notifications", "--disable-popup-blocking");

                if (headless) {

                    edgeOptions.addArguments("--headless=new", "--window-size=1920,1080");
                }

                driver.set(new EdgeDriver(edgeOptions));

                break;


            case "firefox":

                FirefoxOptions firefoxOptions = new FirefoxOptions();

                if (headless) {

                    firefoxOptions.addArguments("-headless");
                }

                driver.set(new FirefoxDriver(firefoxOptions));

                break;


            default:

                throw new IllegalArgumentException("Invalid browser: " + browser);
        }
    }


    public static WebDriver getDriver() {

        return driver.get();
    }


    public static void quitDriver() {

        if (driver.get() != null) {

            driver.get().quit();

            driver.remove();
        }
    }
}