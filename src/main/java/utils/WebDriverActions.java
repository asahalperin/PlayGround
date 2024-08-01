package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import reports.ExtentReportManager;

import java.time.Duration;

public class WebDriverActions {
    private WebDriver driver;
    private WebDriverWait wait;

    public WebDriverActions(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void sendKeys(WebElement element, String text, String successMessage, String failureMessage) {
        performAction(() -> {
            wait.until(ExpectedConditions.visibilityOf(element));
            element.sendKeys(text);
        }, successMessage, failureMessage);
    }

    public void click(WebElement element, String successMessage, String failureMessage) {
        performAction(() -> {
            wait.until(ExpectedConditions.elementToBeClickable(element));
            element.click();
        }, successMessage, failureMessage);
    }

    private void performAction(Action action, String successMessage, String failureMessage) {
        try {
            action.execute();
            ExtentReportManager.logPass(successMessage);
        } catch (Exception e) {
            ExtentReportManager.logFail(failureMessage + ": " + e.getMessage());
            throw e;
        }
    }

    @FunctionalInterface
    private interface Action {
        void execute();
    }
}