package com.itechart.pages;

import com.itechart.utils.PropertyReader;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

@Log4j2
public abstract class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected String baseUrl;
    protected PropertyReader propertyReader = new PropertyReader("src/test/resources/configuration.properties");
    protected final By USERPROFILE_BUTTON_LOCATOR = By.xpath("//*[contains(@class, 'slds-global-actions__item')]//ancestor::button[contains(@class, 'branding-userProfile-button')]");

    public BasePage() {
        baseUrl = propertyReader.getPropertyValueByKey("base.url");
    }

    public boolean isPageOpened() {
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(USERPROFILE_BUTTON_LOCATOR));
        return $(USERPROFILE_BUTTON_LOCATOR).isDisplayed();
    }

    public void validateInput(String label, String expectedInput) {
        String locator = "//div[contains(@class, 'active')]//span[text()='%s']/ancestor::records-record-layout-item//" +
        "*[@data-output-element-id='output-field']";
        WebElement input = $(By.xpath(String.format(locator, label)));
        String actualInput = input.getText();
        log.debug("Validating Expected input: {} and actual input: {}", expectedInput, actualInput);
        Assert.assertTrue(input.getText().contains(expectedInput),
                String.format("%s input is not correct. Expected: '%s' Actual: '%s'", label, expectedInput, actualInput));
    }

    public void waitForPageLoaded() {
        new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                return ((JavascriptExecutor) driver).executeScript("return document.readyState").toString().equals("complete");
            }
        };
    }

    public void clickJS(By locator) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", $(locator));
    }
}
