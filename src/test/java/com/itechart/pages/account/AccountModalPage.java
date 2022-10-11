package com.itechart.pages.account;

import com.itechart.elements.*;
import com.itechart.pages.BasePage;
import com.itechart.models.Account;
import lombok.extern.log4j.Log4j2;
import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;

@Log4j2
public class AccountModalPage extends BasePage {
    private final By SAVE_BUTTON_LOCATOR = By.xpath("//button[@name='SaveEdit']");
    private final By CANCEL_BUTTON_LOCATOR = By.cssSelector("[title='Cancel']");
    private final By CROSS_BUTTON_LOCATOR = By.xpath("//button[@title='Close this window']");
    private final By SAVE_AND_NEW_BUTTON_LOCATOR = By.cssSelector("[title='Save & New']");
    private final By EMPTY_REQUIRED_FIELD_LOCATOR = By.xpath("//li[contains(text(),'These required fields must be completed')]");
    private final By MODAL_HEADER_LOCATOR = By.xpath("//div[@class='slds-modal__header']");

    @Override
    public boolean isPageOpened() {
        try {
            wait.until(ExpectedConditions.invisibilityOfElementLocated(MODAL_HEADER_LOCATOR));
            return false;
        } catch (TimeoutException | NoSuchElementException e) {
            log.warn("Account Modal is not openUrl");
            log.warn(e.getLocalizedMessage());
            return true;
        }
    }

    @Step("Enter data into fields")
    public AccountModalPage enterData(Account account) {
        log.info("Entering Account Data: {}", account);
        new LightInput( "Account Name").write(account.getName());
        new LightDropDown("Type").selectOption(account.getType());
        new LightInput("Website").write(account.getWebsite());
        new TextArea("Description").write(account.getDescription());
        new LightInput( "Phone").write(account.getPhone());
        new LightDropDown( "Industry").selectOption(account.getIndustry());
        new LightInput( "Employees").write(account.getNumberOfEmployees());
        new TextArea("Billing Street").write(account.getBillingStreet());
        new LightInput( "Billing City").write(account.getBillingCity());
        new LightInput("Billing State/Province").write(account.getBillingState());
        new LightInput( "Billing Zip/Postal Code").write(account.getBillingPostalCode());
        new LightInput("Billing Country").write(account.getBillingCountry());
        new TextArea("Shipping Street").write(account.getShippingStreet());
        new LightInput("Shipping City").write(account.getShippingCity());
        new LightInput( "Shipping State/Province").write(account.getShippingState());
        new LightInput( "Shipping Zip/Postal Code").write(account.getShippingPostalCode());
        new LightInput( "Shipping Country").write(account.getShippingCountry());
        return this;
    }

    @Step("Clear data from fields")
    public AccountModalPage clearData() {
        new LightDropDown( "Industry").clear();
        new LightInput("Account Name").clear();
        new LightDropDown( "Type").clear();
        new LightInput("Website").clear();
        new LightInput( "Phone").clear();
        new LightInput("Employees").clear();
        new LightInput( "Billing City").clear();
        new LightInput( "Billing State/Province").clear();
        new LightInput( "Billing Zip/Postal Code").clear();
        new LightInput( "Billing Country").clear();
        new LightInput( "Shipping City").clear();
        new LightInput( "Shipping State/Province").clear();
        new LightInput("Shipping Zip/Postal Code").clear();
        new LightInput("Shipping Country").clear();
        new TextArea("Billing Street").clear();
        new TextArea("Shipping Street").clear();
        new TextArea("Description").clear();
        return this;
    }

    @Step("Click on Save button")
    public AccountDetailsPage clickSaveButton() {
        driver.findElement(SAVE_BUTTON_LOCATOR).click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(SAVE_BUTTON_LOCATOR));
        return new AccountDetailsPage();
    }

    public AccountDetailsPage clickSaveAndNewButton() {
        driver.findElement(SAVE_AND_NEW_BUTTON_LOCATOR).click();
        return new AccountDetailsPage();
    }

    public AccountListViewPage clickCancelButton() {
        driver.findElement(CANCEL_BUTTON_LOCATOR).click();
        return new AccountListViewPage();
    }

    public AccountListViewPage clickCrossButton() {
        driver.findElement(CROSS_BUTTON_LOCATOR).click();
        return new AccountListViewPage();
    }

    public boolean isEmptyRequiredFieldsValidationError() {
        return driver.findElement(EMPTY_REQUIRED_FIELD_LOCATOR).isDisplayed();
    }
}
