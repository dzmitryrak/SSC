package com.itechart.pages.account;

import com.itechart.pages.BasePage;
import lombok.extern.log4j.Log4j2;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

@Log4j2
public class AccountListViewPage extends BasePage {
    private final By BREADCRUMB_LOCATOR = By.cssSelector(".slds-var-p-right_x-small");
    private final By NEW_BUTTON_LOCATOR = By.xpath("(//div[@title ='New']) [1]");
    private final By SUCCESS_DELETE_MESSAGE = By.xpath("//*[contains(@class, 'slds-theme--success')]");

    public AccountListViewPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isPageOpened() {
        wait.until(ExpectedConditions.presenceOfElementLocated(BREADCRUMB_LOCATOR));
        return driver.findElement(BREADCRUMB_LOCATOR).getText().contains("Accounts");
    }

    @Step("Open List View for Account")
    public AccountListViewPage open() {
        driver.get(baseUrl + "lightning/o/Account/list");
        return this;
    }

    @Step("Click on New button")
    public AccountModalPage clickNewButton() {
        /*wait.until(ExpectedConditions.presenceOfElementLocated(NEW_BUTTON_LOCATOR));
        driver.findElement(NEW_BUTTON_LOCATOR).click();*/
        driver.get(baseUrl + "lightning/o/Account/new?count=1&nooverride=1&useRecordTypeCheck=1&navigationLocation=LIST_VIEW&uid=166452908349622516");
        return new AccountModalPage(driver);
    }

    @Step("Check that Account was deleted successfully")
    public boolean isSuccessDeleteMessageDisplayed() {
        boolean isSuccessMessageDisplayed;
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(SUCCESS_DELETE_MESSAGE));
            isSuccessMessageDisplayed = driver.findElement(SUCCESS_DELETE_MESSAGE).isDisplayed();
        } catch (StaleElementReferenceException e) {
            log.warn("Account record successfully deleted message is not found");
            log.warn(e.getLocalizedMessage());
            isSuccessMessageDisplayed = driver.findElement(SUCCESS_DELETE_MESSAGE).isDisplayed();
        }
        return isSuccessMessageDisplayed;
    }
}
