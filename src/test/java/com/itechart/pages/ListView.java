package com.itechart.pages;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

@Log4j2
public class ListView extends BasePage {
    private final By BREADCRUMB_LOCATOR = By.cssSelector(".slds-var-p-right_x-small");
    //TODO make sure that active layer is selected everywhere
    private final By NEW_BUTTON_LOCATOR = By.xpath("//div[contains(@class, 'oneContent active')]//a[@title='New']");
    private final By SUCCESS_DELETE_MESSAGE = By.xpath("//*[contains(@class, 'slds-theme--success')]");

    public void isOpened() {
        $(BREADCRUMB_LOCATOR).shouldBe(visible);
    }

    @Step("Opening List View")
    public ListView openUrl(String listViewName) {
        open(String.format("lightning/o/%s/list", listViewName));
        return this;
    }

    @Step("Click on New button")
    public NewObjectModal clickNew() {
        $(NEW_BUTTON_LOCATOR).click();
        //open(baseUrl + "lightning/o/Account/new?count=1&nooverride=1&useRecordTypeCheck=1&navigationLocation=LIST_VIEW&uid=166452908349622516");
        NewObjectModal accountModalPage = new NewObjectModal();
        accountModalPage.isPageOpened();
        return accountModalPage;
    }

    @Step("Check that object was deleted successfully")
    public boolean isSuccessDeleteMessageDisplayed() {
        boolean isSuccessMessageDisplayed;
        try {
            $(SUCCESS_DELETE_MESSAGE).should(exist);
            isSuccessMessageDisplayed = $(SUCCESS_DELETE_MESSAGE).isDisplayed();
        } catch (Exception e) {
            log.warn("Account record successfully deleted message is not found");
            log.warn(e.getLocalizedMessage());
            isSuccessMessageDisplayed = false;
        }
        return isSuccessMessageDisplayed;
    }
}
