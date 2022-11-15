package com.itechart.pages;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

@Log4j2
public class ListView extends BasePage {
    protected final String CASE_RECORD_LOCATOR = "(//*[contains(@class, 'slds-cell-edit slds-cell-error errorColumn cellContainer')]/parent::tr//th)[%s]";
    private final By BREADCRUMB_LOCATOR = By.xpath("//*[contains(@class,'slds-breadcrumb__item')]");
    //TODO make sure that active layer is selected everywhere
    private final By NEW_BUTTON_LOCATOR = By.xpath("//div[contains(@class, 'oneContent active')]//a[@title='New']");
    private final By SUCCESS_DELETE_MESSAGE = By.xpath("//*[contains(@class, 'slds-theme--success')]");

    public void isOpened() {
        $(BREADCRUMB_LOCATOR).shouldBe(visible);
    }

    @Step("Opening List View")
    public ListView openUrl(String listViewName) {
        open(String.format("lightning/o/%s/list", listViewName));
        isOpened();
        return this;
    }

    @Step("Click on New button")
    public NewObjectModal clickNew() {
        $(NEW_BUTTON_LOCATOR).click();
        NewObjectModal newObjectModal = new NewObjectModal();
        newObjectModal.isPageOpened();
        return newObjectModal;
    }

    //TODO create wrapper for tableview
    @Step("Opening object from the list")
    public void openObjectFromList(int index) {
        log.info("Clicking on the record with the index {}", index);
        $(By.xpath(String.format(CASE_RECORD_LOCATOR, index))).click();
    }

    @Step("Check that object was deleted successfully")
    public boolean isSuccessDeleteMessageDisplayed() {
        boolean isSuccessMessageDisplayed;
        try {
            $(SUCCESS_DELETE_MESSAGE).should(exist);
            isSuccessMessageDisplayed = $(SUCCESS_DELETE_MESSAGE).isDisplayed();
        } catch (Exception e) {
            log.warn("Record successfully deleted message is not found");
            log.warn(e.getLocalizedMessage());
            isSuccessMessageDisplayed = false;
        }
        return isSuccessMessageDisplayed;
    }
}
