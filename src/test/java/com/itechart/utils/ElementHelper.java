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

import java.time.Duration;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

//TODO rework for Selenide
@Log4j2
public class ElementHelper {
    public static final String BASE_DETAIL_PANEL = "//records-lwc-detail-panel";
    String pickList = BASE_DETAIL_PANEL + "//*[text()='%s']/ancestor::lightning-picklist";
    String pickListButton = pickList + "//button[@lightning-basecombobox_basecombobox]";
    String textInput = BASE_DETAIL_PANEL + "//*[text()='%s']/ancestor::lightning-input//input[@type='text']";
    String lookUpField = BASE_DETAIL_PANEL + "//*[text()='%s']/ancestor::lightning-lookup//input";
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
        } else if ($$(By.xpath(String.format(pickListButton, elementLabel))).size() > 0) {
            elementType = "PickList";
            String picklistOption = BASE_DETAIL_PANEL + "//label[text()='%s']/ancestor::lightning-picklist//*[@title='%s']";
            SelenideElement picklist = $(By.xpath(String.format(pickListButton, elementLabel)));
            log.info("Clicking on '{}' picklist", elementLabel);
            jsClick(picklist);
            WebElement element1;
            if (StringUtils.isEmpty(value)) {
                log.info("Setting value: '--None--'");
                element1 = $(By.xpath(String.format(picklistOption, elementLabel, "--None--")));
            } else {
                element1 = $(By.xpath(String.format(picklistOption, elementLabel, value)));
            }
            jsClick(element1);
        } else if ($$(By.xpath(String.format(lookUpField, elementLabel))).size() > 0) {
            //Lookup Relationship
            elementType = "Lookup Relationship";
            //TODO add code to clear lookup
            String optionLocator = "//lightning-base-combobox-formatted-text[contains(@title, '%s')]";

            WebElement element = $(By.xpath(String.format(lookUpField, elementLabel)));
            jsClick(element);
            element.sendKeys(value);
            SelenideElement lookUpOption = $(By.xpath(String.format(optionLocator, value))).shouldBe(visible, Duration.ofSeconds(10));
            jsClick(lookUpOption);
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
                    jsClick(ch);
                }
            } else {
                if (ch.isSelected()) {
                    jsClick(ch);
                }
            }
        } else if ($$(By.xpath(String.format(pickList, elementLabel))).size() > 0) {

            //Multi-Select
            elementType = "Picklist (Multi-Select)";
            SelenideElement moveToChosen = $(By.xpath(String.format(pickList + "//button[@title='Move selection to Chosen']", elementLabel)));
            String lookupOption = BASE_DETAIL_PANEL + "//*[text()='%s']/ancestor::li[@lightning-duallistbox_duallistbox]";
            var options = StringUtils.split(value, ";");
            for (String option : options) {
                log.info("Selecting option: '{}' in multiselect: '{}'", option, elementLabel);
                SelenideElement element = $(By.xpath(String.format(lookupOption, option)));
                Selenide.executeJavaScript("arguments[0].scrollIntoView();", element);
                //TODO possible clickIntercepted due to the duplicates
                element.shouldBe(visible).click();
                jsClick(moveToChosen);
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
        String locator = "//*[contains(@class,'windowViewMode') and contains(@class,'active')]" +
                "//*[text() = '%s']/ancestor::*[contains(@class, 'slds-hint-parent')]" +
                "//*[contains(@class, 'test-id__field-value')]";
        //TODO throw custom exception with simple text
        SelenideElement input = $(By.xpath(String.format(locator, label)));
        input.shouldHave(text(expectedInput));
    }

    private void waitForPageLoaded() {
        new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                return ((JavascriptExecutor) driver).executeScript("return document.readyState").toString().equals("complete");
            }
        };
    }

    private void jsClick(WebElement el) {
        Selenide.executeJavaScript("arguments[0].click();", el);
    }
}
