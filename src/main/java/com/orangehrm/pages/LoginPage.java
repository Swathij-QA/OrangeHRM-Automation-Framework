package com.orangehrm.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.orangehrm.utilities.WaitUtils;

public class LoginPage {

    private WebDriver driver;
    private final WaitUtils waitUtils;
    private By requiredMessage = By.xpath("//span[text()='Required']");
    private By usernameTextBox = By.name("username");
    private By passwordTextBox = By.name("password");
    private By loginButton = By.xpath("//button[@type='submit']");
    private By errorMessage = By.xpath("//p[contains(@class,'oxd-alert-content-text')]");
    public LoginPage(WebDriver driver) {

        this.driver = driver;
        this.waitUtils = new WaitUtils(driver, 10);
    }

    public void enterUsername(String username) {
        waitUtils.waitForVisibility(usernameTextBox)
                .sendKeys(username);
    }

    public void enterPassword(String password) {
        waitUtils.waitForVisibility(passwordTextBox)
                .sendKeys(password);
    }

    public void clickLogin() {
        waitUtils.waitForClickability(loginButton)
                .click();
    }

    public void login(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickLogin();
    }
    public String getErrorMessage() {
        return driver.findElement(errorMessage).getText();
    }
    public String getRequiredMessage() {
        return waitUtils.waitForVisibility(requiredMessage).getText();
    }
}
