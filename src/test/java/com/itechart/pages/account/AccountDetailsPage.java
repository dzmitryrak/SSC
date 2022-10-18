package com.itechart.pages.account;

import com.itechart.models.Account;
import com.itechart.pages.BasePage;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

@Log4j2
public class AccountDetailsPage extends BasePage {

    private final By DETAILS_TAB = By.xpath("//a[@data-label='Details']");
    private final By EDIT_DETAILS_BUTTON_LOCATOR = By.xpath("//*[@name='Edit']");
    private final By ICON_DROPDOWN_MENU = By.xpath("//*[contains(@class, 'slds-button slds-button_icon-border-filled')]");
    private final By DELETE_BUTTON = By.xpath("//*[@name ='Delete']");
    private final By SUCCESS_MESSAGE = By.xpath("//*[contains(@class, 'slds-theme--success')]");
    private final By DELETE_MODAL_TITLE = By.xpath("//div[@class='modal-container slds-modal__container']//h2");
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
    public AccountModalPage clickEditDetailsButton() {
        $(EDIT_DETAILS_BUTTON_LOCATOR).click();
        return new AccountModalPage();
    }

    @Step("Validation of entered data")
    public AccountDetailsPage validate(Account account) {
        log.info("Validating Account Data: {}", account);
        $(byText(account.getName())).shouldBe(visible);
        validateInput("Account Name", account.getName());
        validateInput("Type", account.getType());
        validateInput("Description", account.getDescription());
        validateInput("Industry", account.getIndustry());
        validateInput("Website", account.getWebsite());
        validateInput("Phone", account.getPhone());
        validateInput("Employees", account.getNumberOfEmployees());
        validateInput("Account Owner", account.getAccountOwner());
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
        return $(DELETE_MODAL_TITLE).getText().contains("Delete");
    }

    @Step("Confirm deletion of an account")
    public AccountListViewPage delete() {
        if (!isModalOpened()) throw new RuntimeException("Delete modal is not opened");
        $(SUCCESS_MESSAGE).should(exist);
        $(DELETE_MODAL_BUTTON).click();
        return new AccountListViewPage();
    }
}
