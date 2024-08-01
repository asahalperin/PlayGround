package base;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import reports.ExtentReportManager;
import utils.WebDriverManagerUtil;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class BaseTestUi {
    protected WebDriver driver;
    protected String url;
    protected String username;
    protected String password;

    @Parameters({"browser", "url", "username", "password"})
    @BeforeClass
    public void setUp(String browser, String url, String username, String password) {
        this.url = System.getProperty("url", url);
        this.username = System.getProperty("username", username);
        this.password = System.getProperty("password", password);
        driver = WebDriverManagerUtil.getDriver(System.getProperty("browser", browser));
        ExtentReportManager.initReports();
    }

    @AfterClass
    public void tearDown() {
        WebDriverManagerUtil.quitDriver();
        ExtentReportManager.flushReports();
    }

    protected void captureScreenshot(String testName) {
        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        try {
            Files.copy(source.toPath(), Paths.get("./Screenshots/" + testName + ".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}