package com.itechart.pages;

import com.codeborne.selenide.Selenide;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static com.codeborne.selenide.Selenide.$;

@Log4j2
public class LoginPage extends BasePage {
    private final String loginUrl = propertyReader.getPropertyValueByKey("loginUrl");

    private static final By USERNAME_LOCATOR = By.id("username");
    private static final By PASSWORD_LOCATOR = By.id("password");
    private static final By LOGIN_BUTTON_LOCATOR = By.id("Login");
    private static final By ERROR_MESSAGE_LOCATOR = By.xpath("//*[contains(text(),'Please enter your password.')]");
    private static final By LOGO_LOCATOR = By.id("logo");
    private static final By USERNAME_LABEL_LOCATOR = By.cssSelector(".usernamelabel");


    public LoginPage() {
    }

    @Step("Check that page was opened")
    @Override
    public boolean isPageOpened() {
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(LOGO_LOCATOR));
        return $(LOGO_LOCATOR).isDisplayed();
    }

    @Step("Open Login Page")
    public LoginPage open() {
        log.info("Opening Login page: {}", loginUrl);
        Selenide.open(loginUrl);
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
