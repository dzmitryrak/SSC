package io.github.dzmitryrak.pages;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;

import java.util.Map;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

@Log4j2
public class NewObjectModal extends BasePage {
    private final By SAVE_BUTTON_LOCATOR = By.xpath("//button[@name='SaveEdit']");
    private final By CANCEL_BUTTON_LOCATOR = By.cssSelector("[title='Cancel']");
    private final By CROSS_BUTTON_LOCATOR = By.xpath("//button[@title='Close this window']");
    private final By SAVE_AND_NEW_BUTTON_LOCATOR = By.cssSelector("[title='Save & New']");
    private final By EMPTY_REQUIRED_FIELD_LOCATOR = By.xpath("//li[contains(text(),'These required fields must be completed')]");
    private final By MODAL_HEADER_LOCATOR = By.xpath("//*[contains(@class,'slds-modal__header') and not(contains(@class,'empty'))]");
    private final By ERROR_POPUP = By.xpath("//*[contains(@class, 'slds-popover_error')]");
    private final By ERROR_MESSAGE = By.xpath("//*[@class='fieldLevelErrors']");

    /**
     * Wait until modal window header is displayed.
     */
    public NewObjectModal waitTillOpened() {
        try {
            $(MODAL_HEADER_LOCATOR).shouldBe(visible, timeout);
        } catch (Throwable e) {
            log.warn("Failed to open New Object modal. Trying once again");
            refresh();
            $(MODAL_HEADER_LOCATOR).shouldBe(visible, timeout);
        }
        return this;
    }

    /**
     * Fill data into object creation window fields.
     */
    @Step("Filling the form")
    public NewObjectModal enterData(Map<String, String> data) {
        log.info("Filling in the form: {}", data);
        for (Map.Entry<String, String> entry : data.entrySet()) {
            String fieldLabel = entry.getKey();
            String value = entry.getValue();
            sfHelper.fill(fieldLabel, value);
        }
        return this;
    }

    /**
     * This method allows to fill in any field. Usually used to provide comprehensive validation of the form
     *
     * @param field
     * @param value
     * @return current instance of NewObjectModal
     */
    @Step("Filling the field {field} using value {value}")
    public NewObjectModal enterData(String field, String value) {
        sfHelper.fill(field, value);
        return this;
    }

    /**
     * Clear data from object creation window fields.
     *
     * @param data dictionary, where keys are fields names
     * @return current instance of NewObjectModal
     */
    @Step("Clear data from fields")
    public NewObjectModal clearData(Map<String, String> data) {
        log.info("Clearing Account Data: {}", data.keySet());
        for (Map.Entry<String, String> entry : data.entrySet()) {
            String fieldLabel = entry.getKey();
            sfHelper.fill(fieldLabel, "");
        }
        return this;
    }

    /**
     * Click save.
     *
     * @return current instance of NewObjectModal
     */
    @Step("Click on Save button")
    public NewObjectModal save() {
        $(SAVE_BUTTON_LOCATOR).shouldBe(visible);
        $(SAVE_BUTTON_LOCATOR).click();
        return this;
    }

    /**
     * Wait till modal window is closed.
     */
    public DetailsPage waitTillModalClosed() {
        $(MODAL_HEADER_LOCATOR).shouldNotBe(visible);
        return new DetailsPage();
    }

    //TODO add all required methods

    /**
     * Save object and add a new one.
     */
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

    /**
     * Create new record through lookup.
     *
     * @param elementLabel
     * @param data
     * @return current instance of NewObjectModal
     */
    @Step("Create new related object")
    public NewObjectModal createRelatedObject(String elementLabel, Map<String, String> data) {
        log.info("Creating new entity: {}", data);
        sfHelper.createNewRecordThroughLookup(elementLabel, data);
        return this;
    }

    /**
     * Get all error messages on NewObjectModal.
     * log error message
     * @return current instance of NewObjectModal
     */
    @Step("Get Error Messages")
    public String getError() {
        $(ERROR_POPUP).shouldBe(visible);
        String errorMessage = $(ERROR_MESSAGE).shouldBe(visible).getText();
        log.info("Popup Error Message: {}", errorMessage);
        return errorMessage;
    }
}
