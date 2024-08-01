package utils;

import dto.UserDTO;
import io.restassured.response.Response;
import reports.ExtentReportManager;

import static io.restassured.RestAssured.given;

public class ApiUtils {

    private static final String BASE_URL = "https://reqres.in/api";

    public static Response createUser(UserDTO user) {
        return performApiRequest(() -> given().contentType("application/json").body(user).post(BASE_URL + "/users"),
                "Create user successfully",
                "Failed to create user");
    }

    private static Response performApiRequest(ApiRequest request, String successMessage, String failureMessage) {
        try {
            Response response = request.execute();
            if (response.getStatusCode() == 201) {
                ExtentReportManager.logPass(successMessage + ": " + response.asString());
            } else {
                ExtentReportManager.logFail(failureMessage + ": " + response.asString());
            }
            return response;
        } catch (Exception e) {
            ExtentReportManager.logFail(failureMessage + ": " + e.getMessage());
            throw e;
        }
    }

    @FunctionalInterface
    private interface ApiRequest {
        Response execute();
    }
}