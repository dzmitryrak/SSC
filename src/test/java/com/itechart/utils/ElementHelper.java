package com.itechart.utils;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.ex.ElementNotFound;
import com.itechart.pages.NewObjectModal;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;

import java.time.Duration;
import java.util.Map;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

//TODO rework for Selenide
@Log4j2
public class ElementHelper {
    public static final String BASE_DETAIL_PANEL = "//records-lwc-detail-panel";
    String pickList = BASE_DETAIL_PANEL + "//*[text()='%s']/ancestor::lightning-picklist";
    String pickListButton = pickList + "//button[@lightning-basecombobox_basecombobox]";
    String textInput = BASE_DETAIL_PANEL + "//*[text()='%s']/ancestor::lightning-input//input[@type='text']";
    String lookUpField = BASE_DETAIL_PANEL + "//*[text()='%s']/ancestor::lightning-lookup//input";
    String clearLookUpField = BASE_DETAIL_PANEL + "//*[text()='%s']/ancestor::lightning-lookup//button[@title='Clear Selection']";
    String textArea = BASE_DETAIL_PANEL + "//*[text()='%s']/ancestor::lightning-textarea//textarea";
    String checkbox = BASE_DETAIL_PANEL + "//*[text()='%s']/ancestor::lightning-input//input[@type='checkbox']";

    //TODO amazing javadoc
    public void fill(String elementLabel, String value) {
        long startTime = System.currentTimeMillis();
        waitForPageLoaded();
        Configuration.pollingInterval = 20;
        String elementType;

        //Currency, Date, Date/time, Email, Number Percent Phone Text
        if ($$(By.xpath(String.format(textInput, elementLabel))).size() > 0) {
            elementType = "Text";
            Selenide.executeJavaScript("arguments[0].scrollIntoView();",
                    $(By.xpath(String.format(textInput, elementLabel))));
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
            log.debug("Clicking on '{}' picklist", elementLabel);
            jsClick(picklist);
            WebElement element1;
            if (StringUtils.isEmpty(value)) {
                log.debug("Setting value: '--None--'");
                element1 = $(By.xpath(String.format(picklistOption, elementLabel, "--None--")));
            } else {
                element1 = $(By.xpath(String.format(picklistOption, elementLabel, value)));
            }
            jsClick(element1);
        } else if ($$(By.xpath(String.format(lookUpField, elementLabel))).size() > 0) {
            //Lookup Relationship
            elementType = "Lookup Relationship";
            SelenideElement element = $(By.xpath(String.format(lookUpField, elementLabel)));
            Selenide.executeJavaScript("arguments[0].scrollIntoView();", element);
            if (StringUtils.isEmpty(value)) {
                $(By.xpath(String.format(clearLookUpField, elementLabel))).click();
            } else {
                if(!searchForLookupValue(element, value)) {
                    screenshot("LookUp Search Failure " + System.currentTimeMillis());
                    throw new RuntimeException(String.format("'%s' option is not found inside Lookup '%s'", value, elementLabel));
                }
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
            log.debug("Checkbox initial value: {}", ch.isSelected());
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
                log.debug("Selecting option: '{}' in multiselect: '{}'", option, elementLabel);
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

        Configuration.pollingInterval = 200;
        long endTime = System.currentTimeMillis();

        log.info("Label: '{}' Element Type: '{}' Time Elapsed: '{}ms' Value: '{}'", elementLabel, elementType, (endTime - startTime), value);
    }

    public void validate(String label, String expectedText) {
        log.info("Validation that '{}' field contains value '{}'", label, expectedText);

        String genericLocator = "//*[contains(@class,'windowViewMode') and contains(@class,'active')]" +
                "//*[text() = '%s']/ancestor::*[contains(@class, 'slds-hint-parent')]" +
                "//*[contains(@class, 'test-id__field-value')]";
        String checkboxLocator = genericLocator + "//input";
        if($$(By.xpath(String.format(checkboxLocator, label))).size() > 0) {
            SelenideElement checkbox = $(By.xpath(String.format(checkboxLocator, label)));
            if(expectedText.equals("true")) {
                checkbox.shouldBe(checked);
            } else {
                checkbox.shouldNotBe(checked);
            }
        } else {
            //TODO throw custom exception with simple text
            SelenideElement input = $(By.xpath(String.format(genericLocator, label)));
            input.shouldHave(text(expectedText));
        }
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

    public boolean searchForLookupValue(SelenideElement lookup, String value) {
        log.info("Searching for lookup value: {}", value);
        boolean isOptionFound = false;
        try {
            String optionLocator = "//lightning-base-combobox-formatted-text[contains(@title, '%s')]";
            lookup.shouldBe(visible).sendKeys(value);
            SelenideElement lookUpOption = $(By.xpath(String.format(optionLocator, value))).shouldBe(visible, Duration.ofSeconds(10));
            jsClick(lookUpOption);
            isOptionFound = true;
        } catch (ElementNotFound e) {
            log.warn("Cannot find look up option: {}", value);
            log.warn(e.getLocalizedMessage());
        }
        return isOptionFound;
    }

    public void createNewRecordThroughLookup(String elementLabel, Map<String, String> data) {
        log.info("Creating new parent record through lookup: {}", data);
        SelenideElement element = $(By.xpath(String.format(lookUpField, elementLabel)));
        jsClick(element);
        SelenideElement createOption = $(By.xpath("//lightning-base-combobox-item[@data-value='actionCreateNew']"));
        jsClick(createOption);
        NewObjectModal newObjectModal = new NewObjectModal();
        newObjectModal.isPageOpened();
        newObjectModal
                .enterData(data)
                .save()
                .waitTillModalClosed();

    }
}
