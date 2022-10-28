package com.itechart.pages.account;

import com.itechart.pages.BasePage;
import com.itechart.pages.NewObjectModal;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;

import java.util.Map;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

@Log4j2
public class AccountDetailsPage extends BasePage {

    private final By DETAILS_TAB = By.xpath("//a[@data-label='Details']");
    private final By EDIT_DETAILS_BUTTON_LOCATOR = By.xpath("//*[@name='Edit']");
    private final By ICON_DROPDOWN_MENU = By.xpath("//*[contains(@class, 'slds-dropdown-trigger slds-dropdown-trigger_click slds-button_last overflow')]");
    private final By DELETE_BUTTON = By.xpath("//*[@name='Delete']");
    private final By SUCCESS_MESSAGE = By.xpath("//*[text() ='Are you sure you want to delete this account?']");
    private final By DELETE_MODAL_TITLE = By.xpath("//*[text() ='Delete Account']");
    private final By DELETE_MODAL_BUTTON = By.xpath("//div[@class='modal-container slds-modal__container']//button[@title= 'Delete']");

    public AccountDetailsPage() {
    }

    @Step("Check that Account Details page was opened")
    @Override
    public boolean isPageOpened() {
       $(DETAILS_TAB).shouldBe(visible);
        return true;
    }

    @Step("Open Details tab")
    public AccountDetailsPage openDetails() {
        clickJS(DETAILS_TAB);
        return this;
    }

    @Step("Click Edit button")
    public NewObjectModal clickEditDetailsButton() {
        $(EDIT_DETAILS_BUTTON_LOCATOR).click();
        NewObjectModal accountModalPage = new NewObjectModal();
        accountModalPage.isPageOpened();
        return accountModalPage;
    }

    @Step("Validation of entered data")
    public AccountDetailsPage validate(Map<String, String> data) {
        log.info("Validating Account Data: {}", data);
        $(byText(data.get("Account Name"))).shouldBe(visible);

        for (Map.Entry<String, String> entry : data.entrySet()) {
            String fieldLabel = entry.getKey();
            String value = entry.getValue();
            sfHelper.validate(fieldLabel, value);
        }
        return this;
    }

    @Step("Click on Dropdown icon menu")
    public AccountDetailsPage clickIconDropdownMenu() {
        try {
            $(ICON_DROPDOWN_MENU).click();
        } catch (StaleElementReferenceException e) {
            log.warn("Cannot find Icon Dropdown menu icon");
            log.warn(e.getLocalizedMessage());
         //   $(ICON_DROPDOWN_MENU).click;
        }
        return this;
    }

    @Step("Click on Delete button")
    public AccountDetailsPage clickDeleteButton() {
        try {
            $(DELETE_BUTTON).click();
        } catch (StaleElementReferenceException e) {
            log.warn("Cannot find Delete button");
            log.warn(e.getLocalizedMessage());
            $(DELETE_BUTTON).click();
        }
        return this;
    }

    public boolean isModalOpened() {
        $(DELETE_MODAL_TITLE).should(exist);
        $(DELETE_MODAL_BUTTON).should(exist);
        return $(DELETE_MODAL_TITLE).getText().contains("Delete Account");
    }

    @Step("Confirm deletion of an account")
    public AccountListViewPage delete() {
        if (!isModalOpened()) throw new RuntimeException("Delete modal is not opened");
        $(SUCCESS_MESSAGE).should(exist);
        $(DELETE_MODAL_BUTTON).click();
        return new AccountListViewPage();
    }
}
