package com.itechart.pages.account;

import com.itechart.elements.*;
import com.itechart.pages.BasePage;
import com.itechart.models.Account;
import lombok.extern.log4j.Log4j2;
import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import java.util.Map;

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
            wait.until(ExpectedConditions.visibilityOfElementLocated(MODAL_HEADER_LOCATOR));
            return true;
        } catch (TimeoutException | NoSuchElementException e) {
            log.warn("Account Modal is not open");
            log.warn(e.getLocalizedMessage());
            Assert.fail();
            return false;
        }
    }

    public AccountModalPage enterData(Map<String, String> data) {
        log.info("Entering Account Data: {}", data);
        for (Map.Entry<String, String> entry : data.entrySet()) {
            String fieldLabel = entry.getKey();
            String value = entry.getValue();
            sfHelper.fill(driver, fieldLabel, value);
        }
        return this;
    }

    @Step("Enter data into fields")
    public AccountModalPage enterData(Account account) {
        log.info("Entering Account Data: {}", account);
        sfHelper.fill(driver, "Account Name", account.getName());
        sfHelper.fill(driver, "Type", account.getType());
        sfHelper.fill(driver, "Description", account.getDescription());
        //new LightInput(driver, "Account Name").write(account.getName());
        //new LightDropDown(driver, "Type").selectOption(account.getType());
        sfHelper.fill(driver, "Website", account.getWebsite());
        //new TextArea(driver, "Description").write(account.getDescription());
        sfHelper.fill(driver, "Phone", account.getPhone());
        sfHelper.fill(driver, "Industry", account.getIndustry());
        sfHelper.fill(driver, "Employees", account.getNumberOfEmployees());
        sfHelper.fill(driver, "Billing Street", account.getBillingStreet());
        sfHelper.fill(driver, "Billing City", account.getBillingCity());
        sfHelper.fill(driver, "Billing State/Province", account.getBillingState());
        sfHelper.fill(driver, "Billing Zip/Postal Code", account.getBillingPostalCode());
        sfHelper.fill(driver, "Billing Country", account.getBillingCountry());
        sfHelper.fill(driver, "Shipping Street", account.getShippingStreet());
        sfHelper.fill(driver, "Shipping City", account.getShippingCity());
        sfHelper.fill(driver, "Shipping State/Province", account.getShippingState());
        sfHelper.fill(driver, "Shipping Zip/Postal Code", account.getShippingPostalCode());
        sfHelper.fill(driver, "Shipping Country", account.getShippingCountry());
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
