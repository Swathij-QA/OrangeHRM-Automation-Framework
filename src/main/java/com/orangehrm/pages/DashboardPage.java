package com.orangehrm.pages;

import com.orangehrm.constants.FrameworkConstants;
import com.orangehrm.utilities.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DashboardPage {

    private final WaitUtils waitUtils;

    private final By dashboardHeading = By.xpath("//h6[text()='Dashboard']");

    public DashboardPage(WebDriver driver) {

        this.waitUtils = new WaitUtils(driver, FrameworkConstants.DEFAULT_WAIT);
    }

    public boolean isDashboardDisplayed() {

        return waitUtils.waitForVisibility(dashboardHeading).isDisplayed();
    }
}