package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import reports.ExtentReportManager;

import java.time.Duration;

public class SecurePage {
    private WebDriver driver;
    private WebDriverWait wait;

    @FindBy(css = ".flash.success")
    private WebElement successMessage;

    public SecurePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public boolean isSuccessMessageDisplayed() {
        try {
            wait.until(ExpectedConditions.visibilityOf(successMessage));
            boolean isDisplayed = successMessage.isDisplayed();
            ExtentReportManager.logPass("Success message is displayed");
            return isDisplayed;
        } catch (Exception e) {
            ExtentReportManager.logFail("Success message is not displayed: " + e.getMessage());
            throw e;
        }
    }
}