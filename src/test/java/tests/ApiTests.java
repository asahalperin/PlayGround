package tests;

import base.BaseTestApi;
import base.BaseTestUi;
import dto.UserDTO;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import reports.ExtentReportManager;
import utils.ApiUtils;

public class ApiTests extends BaseTestApi {

    @Test(priority = 1, description = "Create a new user using the API")
    public void testCreateUser() {
        ExtentReportManager.createTest("Create new user in API - Valid");

        UserDTO user = new UserDTO("morpheus", "leader");
        try {
            Response response = ApiUtils.createUser(user);
            Assert.assertEquals(response.getStatusCode(), 201);
            ExtentReportManager.logPass("Create user API test passed");
        } catch (Exception e) {
            ExtentReportManager.logFail("Create user API test failed with exception: " + e.getMessage());
            throw e;
        }
    }
}