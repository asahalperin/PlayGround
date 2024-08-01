package tests;

import base.BaseTestUi;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.SecurePage;
import reports.ExtentReportManager;

public class UiTests extends BaseTestUi {

    @Test(priority = 1, description = "User can login successfully with valid credentials")
    public void testLogin() {
        ExtentReportManager.createTest("Create user - Valid");

        try {
            driver.get(url);
            ExtentReportManager.logInfo("Navigated to login page");

            LoginPage loginPage = new LoginPage(driver);
            loginPage.enterUsername(username);
            loginPage.enterPassword(password);
            SecurePage securePage = loginPage.clickLoginButton();

            Assert.assertTrue(securePage.isSuccessMessageDisplayed());
            ExtentReportManager.logPass("Login test passed");

        } catch (Exception e) {
            captureScreenshot("testLogin");
            ExtentReportManager.logFail("Login test failed with exception: " + e.getMessage());
            throw e;
        }
    }
}