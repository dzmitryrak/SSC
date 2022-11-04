package com.itechart.pages;

import com.codeborne.selenide.Selenide;
import com.itechart.utils.ElementHelper;
import com.itechart.utils.PropertyReader;
import static com.codeborne.selenide.Selenide.*;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

@Log4j2
public abstract class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected String baseUrl;
    protected PropertyReader propertyReader = new PropertyReader("src/test/resources/configuration.properties");
    protected final By USERPROFILE_BUTTON_LOCATOR = By.xpath("//*[contains(@class, 'slds-global-actions__item')]//ancestor::button[contains(@class, 'branding-userProfile-button')]");
    protected ElementHelper sfHelper;

    public BasePage() {
        baseUrl = propertyReader.getPropertyValueByKey("base.url");
        sfHelper = new ElementHelper();
    }

    public boolean isPageOpened() {
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(USERPROFILE_BUTTON_LOCATOR));
        return $(USERPROFILE_BUTTON_LOCATOR).isDisplayed();
    }

    public void waitForPageLoaded() {
        new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                return Selenide.executeJavaScript("return document.readyState").toString().equals("complete");
            }
        };
    }

    @Step("Click on the element")
    public void clickJS(By locator) {
        log.info(String.format("Clicking on %s button", locator));
        Selenide.executeJavaScript("arguments[0].click();", $(locator));
    }
}