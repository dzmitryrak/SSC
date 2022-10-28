package com.itechart.pages.account;

import com.itechart.pages.BasePage;
import com.itechart.pages.NewObjectModal;
import lombok.extern.log4j.Log4j2;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

@Log4j2
public class AccountListViewPage extends BasePage {
    private final By BREADCRUMB_LOCATOR = By.cssSelector(".slds-var-p-right_x-small");
    private final By NEW_BUTTON_LOCATOR = By.xpath("(//div[@title ='New']) [1]");
    private final By SUCCESS_DELETE_MESSAGE = By.xpath("//*[contains(@class, 'slds-theme--success')]");

    @Override
    public boolean isPageOpened() {
        $(BREADCRUMB_LOCATOR).shouldBe(visible);
        return $(BREADCRUMB_LOCATOR).getText().contains("Accounts");
    }

    @Step("Open List View for Account")
    public AccountListViewPage openUrl() {
        open("lightning/o/Account/list");
        return this;
    }

    @Step("Click on New button")
    public NewObjectModal clickNewButton() {
        //TODO come back NEW button
        /*wait.until(ExpectedConditions.presenceOfElementLocated(NEW_BUTTON_LOCATOR));
        driver.findElement(NEW_BUTTON_LOCATOR).click();*/
        open(baseUrl + "lightning/o/Account/new?count=1&nooverride=1&useRecordTypeCheck=1&navigationLocation=LIST_VIEW&uid=166452908349622516");
        NewObjectModal accountModalPage = new NewObjectModal();
        accountModalPage.isPageOpened();
        return accountModalPage;
    }

    @Step("Check that Account was deleted successfully")
    public boolean isSuccessDeleteMessageDisplayed() {
        boolean isSuccessMessageDisplayed;
        try {
            $(SUCCESS_DELETE_MESSAGE).should(exist);
            isSuccessMessageDisplayed = $(SUCCESS_DELETE_MESSAGE).isDisplayed();
        } catch (StaleElementReferenceException e) {
            log.warn("Account record successfully deleted message is not found");
            log.warn(e.getLocalizedMessage());
            isSuccessMessageDisplayed = $(SUCCESS_DELETE_MESSAGE).isDisplayed();
        }
        return isSuccessMessageDisplayed;
    }
}
