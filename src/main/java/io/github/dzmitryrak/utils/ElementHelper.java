package io.github.dzmitryrak.utils;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.github.dzmitryrak.pages.NewObjectModal;
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

/**
 * Class processing the logic of interaction with any Salesforce element
 */
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

    /**
     * Fill any Salesforce element.
     * The method will get the type of given element by itself.
     */
    public void fill(String elementLabel, String value) {
        long startTime = System.currentTimeMillis();
        waitForPageLoaded();
        Configuration.pollingInterval = 20;
        String elementType;

        //Currency, Date, Date/time, Email, Number Percent Phone Text
        if ($$(By.xpath(String.format(textInput, elementLabel))).size() > 0) {
            elementType = "Text";
            SelenideElement element = $(By.xpath(String.format(textInput, elementLabel)));
            scrollToElement(element);
            element.shouldBe(visible);
            if (StringUtils.isEmpty(value)) {
                element.clear();
            } else {
                element.setValue(value);
            }

            //PICKLIST
        } else if ($$(By.xpath(String.format(pickListButton, elementLabel))).size() > 0) {
            elementType = "PickList";
            String picklistOption = BASE_DETAIL_PANEL + "//label[text()='%s']/ancestor::lightning-picklist//*[@title='%s']";
            SelenideElement picklist = $(By.xpath(String.format(pickListButton, elementLabel)));
            log.debug("Clicking on '{}' picklist", elementLabel);
            scrollToElement(picklist);
            picklist.shouldBe(visible);
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
            scrollToElement(element);
            if (StringUtils.isEmpty(value)) {
                $(By.xpath(String.format(clearLookUpField, elementLabel))).click();
            } else {
                searchForLookupValue(element, value);
            }

        } else if ($$(By.xpath(String.format(textArea, elementLabel))).size() > 0) {

            //TextArea
            elementType = "Text Area";
            SelenideElement element = $(By.xpath(String.format(textArea, elementLabel)));
            scrollToElement(element);
            element.shouldBe(visible);
            if (StringUtils.isEmpty(value)) {
                element.clear();
            } else {
                element.setValue(value);
            }
        } else if ($$(By.xpath(String.format(checkbox, elementLabel))).size() > 0) {

            //Checkbox
            elementType = "Checkbox";
            SelenideElement ch = $(By.xpath(String.format(checkbox, elementLabel)));
            scrollToElement(ch);
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
            SelenideElement moveToAvailable = $(By.xpath(String.format(pickList + "//button[@title='Move selection to Available']", elementLabel)));
            String lookupOption = BASE_DETAIL_PANEL + "//*[text()='%s']/ancestor::li[@lightning-duallistbox_duallistbox]";
            if (StringUtils.isEmpty(value)) {
                var chosenOptions = $$(By.xpath(String.format(pickList + "//*[text()='Chosen']//following-sibling::*[@class='slds-dueling-list__options']//li", elementLabel)));
                for (var option : chosenOptions) {
                    scrollToElement(option);
                    option.shouldBe(visible).click();
                    jsClick(moveToAvailable);
                }
            } else {
                var options = StringUtils.split(value, ";");
                for (String option : options) {
                    log.debug("Selecting option: '{}' in multiselect: '{}'", option, elementLabel);
                    SelenideElement element = $(By.xpath(String.format(lookupOption, option)));
                    scrollToElement(element);
                    //TODO possible clickIntercepted due to the duplicates
                    element.shouldBe(visible).click();
                    jsClick(moveToChosen);
                }
            }
        } else {
            throw new SalesforceElementNotFoundException(String.format("Unable to identify type of element. Label: '%s'", elementLabel));
        }

        Configuration.pollingInterval = 200;
        long endTime = System.currentTimeMillis();

        log.info("Label: '{}' Element Type: '{}' Time Elapsed: '{}ms' Value: '{}'", elementLabel, elementType, (endTime - startTime), value);
    }

    /**
     * Validate field value inside the panel.
     *
     * @param panel        Name of the panel
     * @param label        Label of the element
     * @param expectedText Expected text
     */
    public void validate(String panel, String label, String expectedText) {
                log.info("Validation that '{}' field contains value '{}'", label, expectedText);
        String panelLocator = "";
        if (StringUtils.isNotEmpty(panel)) {
            panelLocator = String.format("//*[contains(text(), '%s')]/ancestor::article", panel);
        }
        String standartLocator = "//*[text() = '%s']/ancestor::*[contains(@class, 'slds-hint-parent')]" +
                "//*[contains(@class, 'test-id__field-value')]";
        String lightningLocator = "//*[text() = '%s']/ancestor::lightning-input//input";

        if ($$(By.xpath(String.format(lightningLocator, label))).size() > 0) {
            String genericLocator = "//*[contains(@class,'windowViewMode') and contains(@class,'active')]" +
                    panelLocator + lightningLocator;
            SelenideElement input = $(By.xpath(String.format(genericLocator, label)));
            if (expectedText.equals("true")) {
                input.shouldBe(checked);
            } else if (expectedText.equals("false")){
                input.shouldNotBe(checked);
            } else if (StringUtils.isNotEmpty(expectedText)) {
                input.shouldHave(value(expectedText));
            } else {
                input.shouldHave(exactTextCaseSensitive(expectedText));
            }
        } else {
            String genericLocator = "//*[contains(@class,'windowViewMode') and contains(@class,'active')]" +
                    panelLocator + standartLocator;
            String checkboxLocator = genericLocator + "//input";
            if ($$(By.xpath(String.format(checkboxLocator, label))).size() > 0) {
                SelenideElement checkbox = $(By.xpath(String.format(checkboxLocator, label)));
                if (expectedText.equals("true")) {
                    checkbox.shouldBe(checked);
                } else {
                    checkbox.shouldNotBe(checked);
                }
            } else {
                //TODO throw custom exception with simple text
                SelenideElement input = $(By.xpath(String.format(genericLocator, label)));
                if (StringUtils.isNotEmpty(expectedText)) {
                    input.shouldHave(text(expectedText));
                } else {
                    input.shouldHave(exactTextCaseSensitive(expectedText));
                }
            }
        }
    }

    /**
     * Simple interface for validating any Salesforce element value.
     * The method will get the type of given element by itself.
     */
    public void validate(String label, String expectedText) {
        validate("", label, expectedText);
    }

    /**
     * Wait for "document.readyState" to be "complete".
     */
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

    public void searchForLookupValue(SelenideElement lookup, String value) {
        log.info("Searching for lookup value: {}", value);
        String optionLocator = "//lightning-base-combobox-formatted-text[contains(@title, '%s')]";
        try {
            lookup.shouldBe(visible).sendKeys(value);
            SelenideElement lookUpOption = $(By.xpath(String.format(optionLocator, value))).shouldBe(visible, Duration.ofSeconds(10));
            screenshot("LookUp Search State " + System.currentTimeMillis());
            lookUpOption.click();
        } catch (Throwable exception) {
            log.warn("Failed to find lookup value. Trying once again: {}", value);
            lookup.shouldBe(visible);
            executeJavaScript("arguments[0].value ='';", lookup);
            lookup.shouldBe(visible).sendKeys(value);
            SelenideElement lookUpOption = $(By.xpath(String.format(optionLocator, value))).shouldBe(visible, Duration.ofSeconds(10));
            screenshot("LookUp Search State 2nd attempt " + System.currentTimeMillis());
            lookUpOption.click();
        }
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

    private void scrollToElement(WebElement el) {
        executeJavaScript("arguments[0].scrollIntoView();", el);
    }
}
