package com.itechart.utils;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.ex.ElementNotFound;
import com.github.javafaker.Faker;
import com.itechart.pages.NewObjectModal;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.testng.Assert;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

//TODO rework for Selenide
@Log4j2
public class ElementHelper {
    public static final String BASE_DETAIL_PANEL = "//records-lwc-detail-panel";
    String pickList = BASE_DETAIL_PANEL + "//*[text()='%s']/ancestor::lightning-picklist//button";
    String textInput = BASE_DETAIL_PANEL + "//*[text()='%s']/ancestor::lightning-input//input[@type='text']";
    String lookUpField = BASE_DETAIL_PANEL + "//*[text()='%s']/ancestor::lightning-lookup//input";
    String clearLookUpField = BASE_DETAIL_PANEL + "//*[text()='%s']/ancestor::lightning-lookup//button[@title='Clear Selection']";
    String textArea = BASE_DETAIL_PANEL + "//*[text()='%s']/ancestor::lightning-textarea//textarea";
    String checkbox = BASE_DETAIL_PANEL + "//*[text()='%s']/ancestor::lightning-input//input[@type='checkbox']";

    //TODO amazing javadoc
    public void fill(String elementLabel, String value) {
        log.info("Filling '{}' field with '{}' value", elementLabel, value);
        long startTime = System.currentTimeMillis();
        waitForPageLoaded();
        Configuration.timeout = 1000;
        String elementType;

        //Currency, Date, Date/time, Email, Number Percent Phone Text
        if ($$(By.xpath(String.format(textInput, elementLabel))).size() > 0) {
            elementType = "Text";
            if (StringUtils.isEmpty(value)) {
                $(By.xpath(String.format(textInput, elementLabel))).clear();
            } else {
                $(By.xpath(String.format(textInput, elementLabel))).sendKeys(value);
            }

            //PICKLIST
        } else if ($$(By.xpath(String.format(pickList, elementLabel))).size() > 0) {
            //TODO Implement multiselect option with separator
            elementType = "PickList";
            String lookupOption = BASE_DETAIL_PANEL + "//*[contains(text(), '%s')]/ancestor::lightning-base-combobox-item";
            WebElement element = $(By.xpath(String.format(pickList, elementLabel)));
            log.info("Clicking on '{}' picklist", elementLabel);
            Selenide.executeJavaScript("arguments[0].click();", element);
            WebElement element1;
            if (StringUtils.isEmpty(value)) {
                log.info("Setting value: '--None--'");
                element1 = $(By.xpath(String.format(lookupOption, "--None--")));
            } else {
                element1 = $(By.xpath(String.format(lookupOption, value)));
            }
            Selenide.executeJavaScript("arguments[0].click();", element1);
        } else if ($$(By.xpath(String.format(lookUpField, elementLabel))).size() > 0) {

            //Lookup Relationship
            elementType = "Lookup Relationship";
            if (StringUtils.isEmpty(value)) {
                $(String.format(clearLookUpField, lookUpField)).click();
            } else {
                SelenideElement element = $(By.xpath(String.format(lookUpField, elementLabel)));
                Selenide.executeJavaScript("arguments[0].click();", element);
                if(!searchForLookupValue(element, value)) createNewRecordThroughLookup();
            }

        } else if ($$(By.xpath(String.format(textArea, elementLabel))).size() > 0) {

            //TextArea
            elementType = "Text Area";
            if (StringUtils.isEmpty(value)) {
                $(By.xpath(String.format(textArea, elementLabel))).clear();
            } else {
                $(By.xpath(String.format(textArea, elementLabel))).sendKeys(value);
            }
        } else if ($$(By.xpath(String.format(checkbox, elementLabel))).size() > 0) {

            //Checkbox
            elementType = "Checkbox";
            SelenideElement ch = $(By.xpath(String.format(checkbox, elementLabel)));
            log.info("Checkbox initial value: {}", ch.isSelected());
            if (value.equals("true")) {
                if (!ch.isSelected()) {
                    Selenide.executeJavaScript("arguments[0].click();", ch);
                }
            } else {
                if (ch.isSelected()) {
                    Selenide.executeJavaScript("arguments[0].click();", ch);
                }
            }
        } else {
            elementType = "ERROR! Cannot identify element";
            throw new RuntimeException(String.format("Unable to identify type of element. Label: '%s' Element Type: '%s'", elementLabel, elementType));
        }

        Configuration.timeout = 5000;
        long endTime = System.currentTimeMillis();

        log.info("Label: '{}' Element Type: '{}' Time Elapsed: '{}ms'", elementLabel, elementType, (endTime - startTime));
    }

    public void validate(String label, String expectedInput) {
        log.info("Validating '{}' field with '{}' expected value", label, expectedInput);
        String locator = "//*[text() = '%s']/ancestor::*[contains(@class, 'slds-hint-parent')]//*[contains(@class, 'slds-form-element__control')]";
        WebElement input = $(By.xpath(String.format(locator, label)));
        String actualInput = input.getText();
        log.debug("Validating Expected input: {} and actual input: {}", expectedInput, actualInput);
        Assert.assertTrue(input.getText().contains(expectedInput),
                String.format("%s input is not correct. Expected: '%s' Actual: '%s'", label, expectedInput, actualInput));
    }

    public void waitForPageLoaded() {
        log.info("Waiting for page to be opened");
        new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                return ((JavascriptExecutor) driver).executeScript("return document.readyState").toString().equals("complete");
            }
        };
    }

    public boolean searchForLookupValue(SelenideElement lookup, String value) {
        log.info("Searching for lookup value: {}", value);
        boolean isOptionFound = false;
        try {
            String optionLocator = "//lightning-base-combobox-formatted-text[contains(@title, '%s')]";
            lookup.sendKeys(value);
            SelenideElement lookUpOption = $(By.xpath(String.format(optionLocator, value))).shouldBe(visible, Duration.ofSeconds(10));
            isOptionFound = lookUpOption.isDisplayed();
            Selenide.executeJavaScript("arguments[0].click();", lookUpOption);
        } catch (ElementNotFound e) {
            log.warn("Cannot find look up option: {}", value);
            log.warn(e.getLocalizedMessage());
        }
        return isOptionFound;
    }

    public void createNewRecordThroughLookup() {
        log.info("Creating new record");
        Faker faker = new Faker();
        Map<String, String> data = new HashMap<>() {{
            put("Account Name", faker.name().name());
            put("Parent Account", "Erica Larson");
            put("Type", "Prospect");
            put("Website", faker.internet().url());
            put("Phone", faker.phoneNumber().phoneNumber());
            put("Description", faker.lorem().sentence());
            put("Employees", faker.number().digit());
            put("Billing Street", faker.address().streetAddress());
            put("Billing City", faker.address().city());
            put("Billing State/Province", faker.address().state());
            put("Billing Zip/Postal Code", faker.address().zipCode());
            put("Billing Country", faker.address().country());
            put("Shipping Street", faker.address().streetAddress());
            put("Shipping City", faker.address().city());
            put("Shipping State/Province", faker.address().state());
            put("Shipping Zip/Postal Code", faker.address().zipCode());
            put("Shipping Country", faker.address().country());
        }};
        NewObjectModal newObjectModal = new NewObjectModal();
        newObjectModal.isPageOpened();
        newObjectModal
                .enterData(data)
                .save();
    }
}
