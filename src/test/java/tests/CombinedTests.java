package tests;

import base.BaseTestUi;
import dto.UserDTO;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.SecurePage;
import reports.ExtentReportManager;
import utils.ApiUtils;

public class CombinedTests extends BaseTestUi {

    @Test(description = "Combined test for API and UI")
    public void testCombined() {
        ExtentReportManager.createTest("Combined test for API and UI");

        // API call to create user
        UserDTO user = new UserDTO("morpheus", "leader");
        try {
            Response response = ApiUtils.createUser(user);
            Assert.assertEquals(response.getStatusCode(), 201);
            ExtentReportManager.logPass("Create user API test passed");

            // UI Test to check login
            driver.get(url);
            ExtentReportManager.logInfo("Navigated to login page");

            LoginPage loginPage = new LoginPage(driver);
            loginPage.enterUsername(username);
            loginPage.enterPassword(password);
            SecurePage securePage = loginPage.clickLoginButton();

            Assert.assertTrue(securePage.isSuccessMessageDisplayed());
            ExtentReportManager.logPass("Login test passed");

        } catch (Exception e) {
            captureScreenshot("testCombined");
            ExtentReportManager.logFail("Combined test failed with exception: " + e.getMessage());
            throw e;
        }
    }
}