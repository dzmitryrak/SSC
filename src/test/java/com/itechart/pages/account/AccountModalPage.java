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

    public AccountModalPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isPageOpened() {
        try {
            wait.until(ExpectedConditions.invisibilityOfElementLocated(MODAL_HEADER_LOCATOR));
            return false;
        } catch (TimeoutException | NoSuchElementException e) {
            log.warn("Account Modal is not open");
            log.warn(e.getLocalizedMessage());
            return true;
        }
    }

    @Step("Enter data into fields")
    public AccountModalPage enterData(Account account) {
        log.info("Entering Account Data: {}", account);
        new LightInput(driver, "Account Name").write(account.getName());
        new LightDropDown(driver, "Type").selectOption(account.getType());
        new LightInput(driver, "Website").write(account.getWebsite());
        new TextArea(driver, "Description").write(account.getDescription());
        new LightInput(driver, "Phone").write(account.getPhone());
        new LightDropDown(driver, "Industry").selectOption(account.getIndustry());
        new LightInput(driver, "Employees").write(account.getNumberOfEmployees());
        new TextArea(driver, "Billing Street").write(account.getBillingStreet());
        new LightInput(driver, "Billing City").write(account.getBillingCity());
        new LightInput(driver, "Billing State/Province").write(account.getBillingState());
        new LightInput(driver, "Billing Zip/Postal Code").write(account.getBillingPostalCode());
        new LightInput(driver, "Billing Country").write(account.getBillingCountry());
        new TextArea(driver, "Shipping Street").write(account.getShippingStreet());
        new LightInput(driver, "Shipping City").write(account.getShippingCity());
        new LightInput(driver, "Shipping State/Province").write(account.getShippingState());
        new LightInput(driver, "Shipping Zip/Postal Code").write(account.getShippingPostalCode());
        new LightInput(driver, "Shipping Country").write(account.getShippingCountry());
        return this;
    }

    @Step("Clear data from fields")
    public AccountModalPage clearData() {
        new LightDropDown(driver, "Industry").clear();
        new LightInput(driver, "Account Name").clear();
        new LightDropDown(driver, "Type").clear();
        new LightInput(driver, "Website").clear();
        new LightInput(driver, "Phone").clear();
        new LightInput(driver, "Employees").clear();
        new LightInput(driver, "Billing City").clear();
        new LightInput(driver, "Billing State/Province").clear();
        new LightInput(driver, "Billing Zip/Postal Code").clear();
        new LightInput(driver, "Billing Country").clear();
        new LightInput(driver, "Shipping City").clear();
        new LightInput(driver, "Shipping State/Province").clear();
        new LightInput(driver, "Shipping Zip/Postal Code").clear();
        new LightInput(driver, "Shipping Country").clear();
        new TextArea(driver, "Billing Street").clear();
        new TextArea(driver, "Shipping Street").clear();
        new TextArea(driver, "Description").clear();
        return this;
    }

    @Step("Click on Save button")
    public AccountDetailsPage clickSaveButton() {
        driver.findElement(SAVE_BUTTON_LOCATOR).click();
        return new AccountDetailsPage(driver);
    }

    public AccountDetailsPage clickSaveAndNewButton() {
        driver.findElement(SAVE_AND_NEW_BUTTON_LOCATOR).click();
        return new AccountDetailsPage(driver);
    }

    public AccountListViewPage clickCancelButton() {
        driver.findElement(CANCEL_BUTTON_LOCATOR).click();
        return new AccountListViewPage(driver);
    }

    public AccountListViewPage clickCrossButton() {
        driver.findElement(CROSS_BUTTON_LOCATOR).click();
        return new AccountListViewPage(driver);
    }

    public boolean isEmptyRequiredFieldsValidationError() {
        return driver.findElement(EMPTY_REQUIRED_FIELD_LOCATOR).isDisplayed();
    }
}
