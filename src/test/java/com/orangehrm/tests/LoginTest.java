package com.orangehrm.tests;

import com.orangehrm.base.BaseTest;
import com.orangehrm.dataproviders.LoginDataProvider;
import com.orangehrm.listeners.TestListener;
import com.orangehrm.pages.DashboardPage;
import com.orangehrm.pages.LoginPage;
import com.orangehrm.retry.RetryAnalyzer;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(TestListener.class)
public class LoginTest extends BaseTest {

    @Test(groups = {"smoke", "regression"}, dataProvider = "loginData",
            dataProviderClass = LoginDataProvider.class, retryAnalyzer = RetryAnalyzer.class)
    public void verifyLogin(String username, String password, String expectedResult, String expectedMessage) {

        LoginPage loginPage = new LoginPage(getDriver());

        loginPage.login(username, password);

        if (expectedResult.equalsIgnoreCase("PASS")) {

            DashboardPage dashboardPage = new DashboardPage(getDriver());

            Assert.assertTrue(dashboardPage.isDashboardDisplayed(),
                    "Dashboard was not displayed after valid login");

        } else if (expectedMessage.equalsIgnoreCase("Required")) {

            Assert.assertEquals(loginPage.getRequiredMessage(), expectedMessage,
                    "Required field validation message is incorrect");

        } else {

            Assert.assertEquals(loginPage.getErrorMessage(), expectedMessage,
                    "Invalid login error message is incorrect");
        }
    }
}