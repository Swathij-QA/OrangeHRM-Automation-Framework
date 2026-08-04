package com.orangehrm.tests;

import com.orangehrm.base.BaseTest;
import com.orangehrm.pages.DashboardPage;
import com.orangehrm.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    @Test
    public void verifyLogin() {

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("Admin","admin123");
        DashboardPage dashboardPage = new DashboardPage(driver);
        Assert.assertTrue(dashboardPage.isDashboardDisplayed()
        );
    }
    @Test

    public void verifyInvalidPassword() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("Admin", "WrongPassword");
        Assert.assertEquals(
                loginPage.getErrorMessage(),
                "Invalid credentials"
        );
    }
}
