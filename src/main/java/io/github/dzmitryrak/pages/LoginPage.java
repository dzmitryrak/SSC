package io.github.dzmitryrak.pages;

import com.codeborne.selenide.Selenide;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

@Log4j2
public class LoginPage extends BasePage {
    private static final By USERNAME_LOCATOR = By.id("username");
    private static final By PASSWORD_LOCATOR = By.id("password");
    private static final By LOGIN_BUTTON_LOCATOR = By.id("Login");
    private static final By ERROR_MESSAGE_LOCATOR = By.xpath("//*[contains(text(),'Please enter your password.')]");
    private static final By LOGO_LOCATOR = By.id("logo");
    private static final By USERNAME_LABEL_LOCATOR = By.cssSelector(".usernamelabel");

    /**
     * Check if main logo is displayed.
     *
     * @return current instance of LoginPage
     */
    public LoginPage waitTillOpened() {
        $(LOGO_LOCATOR).shouldBe(visible, timeout);
        return this;
    }

    /**
     * Open login page.
     *
     * @return current instance of LoginPage
     */
    @Step("Open Login Page")
    public LoginPage open() {
        Selenide.open("/");
        waitTillOpened();
        return this;
    }

    /**
     * @param username
     * @param password
     * @return current instance of LoginPage
     */
    @Step("Login by user: {username}")
    public HomePage login(String username, String password) {
        log.info("Logging into Salesforce with username: {} and password: {}", username, password);
        $(USERNAME_LOCATOR).setValue(username);
        $(PASSWORD_LOCATOR).setValue(password);
        $(LOGIN_BUTTON_LOCATOR).click();
        return new HomePage();
    }

    /**
     * @return login error message
     */
    @Step("Get error message")
    public String getErrorMessage() {
        return $(ERROR_MESSAGE_LOCATOR).getText();
    }

    /**
     * @return true if username is displayed
     */
    @Step("Check the displaying of Username")
    public boolean isUsernameDisplayed() {
        return $(USERNAME_LABEL_LOCATOR).isDisplayed();
    }
}
