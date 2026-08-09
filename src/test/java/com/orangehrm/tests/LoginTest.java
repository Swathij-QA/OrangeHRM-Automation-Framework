package com.orangehrm.tests;

import com.orangehrm.listeners.TestListener;
import com.orangehrm.retry.RetryAnalyzer;
import org.testng.annotations.Listeners;
import com.orangehrm.base.BaseTest;
import com.orangehrm.dataproviders.LoginDataProvider;
import com.orangehrm.pages.DashboardPage;
import com.orangehrm.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

@Listeners(TestListener.class)
public class LoginTest extends BaseTest {

    @Test(
            dataProvider = "loginData",
            dataProviderClass = LoginDataProvider.class,
            retryAnalyzer = RetryAnalyzer.class
    )
    public void verifyLogin(
            String username,
            String password,
            String expectedResult,
            String expectedMessage) {

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(username, password);

        if (expectedResult.equals("PASS")) {

            DashboardPage dashboardPage = new DashboardPage(driver);

            Assert.assertTrue(
                    dashboardPage.isDashboardDisplayed(),
                    "Dashboard was not displayed after valid login"
            );

        } else if (expectedMessage.equals("Required")) {

            Assert.assertEquals(
                    loginPage.getRequiredMessage(),
                    expectedMessage
            );

        } else {

            Assert.assertEquals(
                    loginPage.getErrorMessage(),
                    expectedMessage
            );
        }
    }
}