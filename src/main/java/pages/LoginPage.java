package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.WebDriverActions;

public class LoginPage {
    private WebDriver driver;
    private WebDriverActions actions;

    @FindBy(id = "username")
    private WebElement usernameField;

    @FindBy(id = "password")
    private WebElement passwordField;

    @FindBy(css = "button[type='submit']")
    private WebElement loginButton;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.actions = new WebDriverActions(driver);
        PageFactory.initElements(driver, this);
    }

    public void enterUsername(String username) {
        actions.sendKeys(usernameField, username, "Entered username: " + username, "Failed to enter username");
    }

    public void enterPassword(String password) {
        actions.sendKeys(passwordField, password, "Entered password: " + password, "Failed to enter password");
    }

    public SecurePage clickLoginButton() {
        actions.click(loginButton, "Clicked login button", "Failed to click login button");
        return new SecurePage(driver);
    }
}