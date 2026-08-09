package com.orangehrm.pages;

import com.orangehrm.constants.FrameworkConstants;
import com.orangehrm.utilities.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {

    private final WebDriver driver;
    private final WaitUtils waitUtils;

    private final By usernameTextBox = By.name("username");

    private final By passwordTextBox = By.name("password");

    private final By loginButton = By.xpath("//button[@type='submit']");

    private final By errorMessage = By.xpath("//p[contains(@class,'oxd-alert-content-text')]");

    private final By requiredMessage = By.xpath("//span[text()='Required']");

    public LoginPage(WebDriver driver) {

        this.driver = driver;

        this.waitUtils = new WaitUtils(driver, FrameworkConstants.DEFAULT_WAIT);
    }

    public void enterUsername(String username) {

        waitUtils.waitForVisibility(usernameTextBox).sendKeys(username);
    }

    public void enterPassword(String password) {

        waitUtils.waitForVisibility(passwordTextBox).sendKeys(password);
    }

    public void clickLogin() {

        waitUtils.waitForClickability(loginButton).click();
    }

    public void login(String username, String password) {

        enterUsername(username);
        enterPassword(password);
        clickLogin();
    }

    public String getErrorMessage() {

        return waitUtils.waitForVisibility(errorMessage).getText();
    }

    public String getRequiredMessage() {

        return waitUtils.waitForVisibility(requiredMessage).getText();
    }
}