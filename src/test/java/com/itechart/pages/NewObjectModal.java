package com.itechart.pages;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.testng.Assert;

import java.time.Duration;
import java.util.Map;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

@Log4j2
public class NewObjectModal extends BasePage {
    private final By SAVE_BUTTON_LOCATOR = By.xpath("//button[@name='SaveEdit']");
    private final By CANCEL_BUTTON_LOCATOR = By.cssSelector("[title='Cancel']");
    private final By CROSS_BUTTON_LOCATOR = By.xpath("//button[@title='Close this window']");
    private final By SAVE_AND_NEW_BUTTON_LOCATOR = By.cssSelector("[title='Save & New']");
    private final By EMPTY_REQUIRED_FIELD_LOCATOR = By.xpath("//li[contains(text(),'These required fields must be completed')]");
    private final By MODAL_HEADER_LOCATOR = By.xpath("//*[contains(@class,'slds-modal__header') and not(contains(@class,'empty'))]");

    @Override
    public boolean isPageOpened() {
        try {
            //TODO generic wait timeout
            $(MODAL_HEADER_LOCATOR).shouldBe(visible, Duration.ofSeconds(15));
            return true;
        } catch (TimeoutException | NoSuchElementException e) {
            log.warn("Modal is not opened");
            log.warn(e.getLocalizedMessage());
            Assert.fail();
            return false;
        }
    }

    public NewObjectModal enterData(Map<String, String> data) {
        log.info("Entering Account Data: {}", data);
        for (Map.Entry<String, String> entry : data.entrySet()) {
            String fieldLabel = entry.getKey();
            String value = entry.getValue();
            sfHelper.fill(fieldLabel, value);
        }
        return this;
    }

    @Step("Clear data from fields")
    public NewObjectModal clearData(Map<String, String> data) {
        log.info("Clearing Account Data: {}", data);
        for (Map.Entry<String, String> entry : data.entrySet()) {
            String fieldLabel = entry.getKey();
            sfHelper.fill(fieldLabel, "");
        }
        return this;
    }

    @Step("Click on Save button")
    public void save() {
        $(SAVE_BUTTON_LOCATOR).shouldBe(visible);
        $(SAVE_BUTTON_LOCATOR).click();
        //TODO return Detail page
    }

    //TODO add all required methods
    public NewObjectModal saveAndNew() {
        $(SAVE_AND_NEW_BUTTON_LOCATOR).click();
        return this;
    }

    public void clickCancelButton() {
        clickJS(CANCEL_BUTTON_LOCATOR);
        //TODO return List page
    }

    public void clickCrossButton() {
        $(CROSS_BUTTON_LOCATOR).click();
        //TODO return List page
    }

    public boolean isEmptyRequiredFieldsValidationError() {
        return $(EMPTY_REQUIRED_FIELD_LOCATOR).isDisplayed();
    }

    @Step("Create new parent entity")
    public NewObjectModal createParentEntity(String elementLabel, Map<String, String> data) {
        log.info("Creating new entity: {}", data);
        sfHelper.createNewRecordThroughLookup(elementLabel, data);
        return this;
    }
}
