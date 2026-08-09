package com.orangehrm.utilities;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ScreenshotUtils {

    public static String captureScreenshot(WebDriver driver, String testName) {

        String timestamp = LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));

        String screenshotPath =
                "screenshots/" + testName + "_" + timestamp + ".png";

        File source =
                ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

        try {

            Files.createDirectories(Path.of("screenshots"));

            Files.copy(
                    source.toPath(),
                    Path.of(screenshotPath)
            );

        } catch (IOException e) {

            throw new RuntimeException(
                    "Unable to save screenshot",
                    e
            );
        }

        return screenshotPath;
    }
}