package io.github.dzmitryrak.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

/**
 * Class representing Salesforce login page.
 */
@Log4j2
public class LoginPage extends BasePage {
    private static final By USERNAME_LOCATOR = By.id("username");
    private static final By PASSWORD_LOCATOR = By.id("password");
    private static final By LOGIN_BUTTON_LOCATOR = By.id("Login");
    private static final By ERROR_MESSAGE_LOCATOR = By.xpath("//*[contains(text(),'Please enter your password.')]");
    private static final By LOGO_LOCATOR = By.id("logo");
    private static final By USERNAME_LABEL_LOCATOR = By.cssSelector(".usernamelabel");

    public LoginPage waitTillOpened() {
        $(LOGO_LOCATOR).shouldBe(Condition.visible);
        return this;
    }

    @Step("Open Login Page")
    public LoginPage open() {
        Selenide.open("/");
        waitTillOpened();
        return this;
    }

    @Step("Login by user: {username}")
    public HomePage login(String username, String password) {
        log.info("Logging into Salesforce with username: {} and password: {}", username, password);
        $(USERNAME_LOCATOR).setValue(username);
        $(PASSWORD_LOCATOR).setValue(password);
        $(LOGIN_BUTTON_LOCATOR).click();
        return new HomePage();
    }

    @Step("Get error message")
    public String getErrorMessage() {
        return $(ERROR_MESSAGE_LOCATOR).getText();
    }

    @Step("Check the displaying of Username")
    public boolean isUsernameDisplayed() {
        return $(USERNAME_LABEL_LOCATOR).isDisplayed();
    }
}
