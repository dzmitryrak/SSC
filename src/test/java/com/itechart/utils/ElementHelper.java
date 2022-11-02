package com.itechart.utils;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.testng.Assert;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

//TODO rework for Selenide
@Log4j2
public class ElementHelper {
    public static final String BASE_DETAIL_PANEL = "//records-lwc-detail-panel";
    String pickList = BASE_DETAIL_PANEL + "//*[text()='%s']/ancestor::lightning-picklist//button";
    String textInput = BASE_DETAIL_PANEL + "//*[text()='%s']/ancestor::lightning-input//input";
    String lookUpField = BASE_DETAIL_PANEL + "//*[text()='%s']/ancestor::lightning-lookup//input";
    String textArea = BASE_DETAIL_PANEL + "//*[text()='%s']/ancestor::lightning-textarea//textarea";

    //TODO amazing javadoc
    public void fill(String elementLabel, String value) {
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
            //TODO logging
            //TODO Implement multiselect option with separator
            elementType = "PickList";
            String lookupOption = BASE_DETAIL_PANEL + "//*[contains(text(), '%s')]/ancestor::lightning-base-combobox-item";
            WebElement element = $(By.xpath(String.format(pickList, elementLabel)));
            Selenide.executeJavaScript("arguments[0].click();", element);
            WebElement element1;
            //TODO how about just passing  "--None--" to clear?
            if (StringUtils.isEmpty(value)) {
                element1 = $(By.xpath(String.format(lookupOption, "--None--")));
            } else {
                element1 = $(By.xpath(String.format(lookupOption, value)));
            }
            Selenide.executeJavaScript("arguments[0].click();", element1);
        } else if ($$(By.xpath(String.format(lookUpField, elementLabel))).size() > 0) {

            //Lookup Relationship
            elementType = "Lookup Relationship";
            //TODO add code to clear lookup
            String optionLocator = "//lightning-base-combobox-formatted-text[contains(@title, '%s')]";

            WebElement element = $(By.xpath(String.format(lookUpField, elementLabel)));
            Selenide.executeJavaScript("arguments[0].click();", element);
            element.sendKeys(value);
            SelenideElement lookUpOption = $(By.xpath(String.format(optionLocator, value))).shouldBe(visible, Duration.ofSeconds(10));
            Selenide.executeJavaScript("arguments[0].click();", lookUpOption);
        } else if ($$(By.xpath(String.format(textArea, elementLabel))).size() > 0) {
            //TextArea
            elementType = "Text Area";
            if (StringUtils.isEmpty(value)) {
                $(By.xpath(String.format(textArea, elementLabel))).clear();
            } else {
                $(By.xpath(String.format(textArea, elementLabel))).sendKeys(value);
            }
            //TODO add else if for checkbox
        } else {
            elementType = "ERROR! Cannot identify element";
            throw new RuntimeException(String.format("Unable to identify type of element. Label: '%s' Element Type: '%s'", elementLabel, elementType));
        }

        Configuration.timeout = 5000;
        long endTime = System.currentTimeMillis();

        System.out.printf("Label: '%s' Element Type: '%s' Time Elapsed: '%sms'%n", elementLabel, elementType, (endTime - startTime));
    }

    public void validate(String label, String expectedInput) {
        String locator = "//div[contains(@class, 'active')]//span[text()='%s']/ancestor::records-record-layout-item//" +
                "*[@data-output-element-id='output-field']";
        WebElement input = $(By.xpath(String.format(locator, label)));
        String actualInput = input.getText();
         log.debug("Validating Expected input: {} and actual input: {}", expectedInput, actualInput);
        Assert.assertTrue(input.getText().contains(expectedInput),
                String.format("%s input is not correct. Expected: '%s' Actual: '%s'", label, expectedInput, actualInput));
    }

    public void waitForPageLoaded() {
        new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                return ((JavascriptExecutor) driver).executeScript("return document.readyState").toString().equals("complete");
            }
        };
    }
}
