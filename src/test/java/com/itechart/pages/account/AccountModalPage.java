package com.itechart.pages.account;

import com.itechart.pages.BasePage;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.testng.Assert;

import java.util.Map;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

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
            $(MODAL_HEADER_LOCATOR).shouldBe(visible);
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
            sfHelper.fill(fieldLabel, value);
        }
        return this;
    }

    @Step("Clear data from fields")
        public AccountModalPage clearData(Map<String, String> data) {
            log.info("Clearing Account Data: {}", data);
            for (Map.Entry<String, String> entry : data.entrySet()) {
                sfHelper.fieldClear2();
            }
            return this;
        }

    @Step("Click on Save button")
    public AccountDetailsPage clickSaveButton() {
        $(SAVE_BUTTON_LOCATOR).shouldBe(visible);
        $(SAVE_BUTTON_LOCATOR).click();
        return new AccountDetailsPage();
    }

    public AccountDetailsPage clickSaveAndNewButton() {
        $(SAVE_AND_NEW_BUTTON_LOCATOR).click();
        return new AccountDetailsPage();
    }

    public AccountListViewPage clickCancelButton() {
        $(CANCEL_BUTTON_LOCATOR).click();
        return new AccountListViewPage();
    }

    public AccountListViewPage clickCrossButton() {
        $(CROSS_BUTTON_LOCATOR).click();
        return new AccountListViewPage();
    }

    public boolean isEmptyRequiredFieldsValidationError() {
        return $(EMPTY_REQUIRED_FIELD_LOCATOR).isDisplayed();
    }
}
