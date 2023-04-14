package io.github.dzmitryrak.pages;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;

import java.util.Map;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

@Log4j2
public class DetailsPage extends BasePage {
    private final String COMMON_TAB = ACTIVE_TAB_LOCATOR + "//a[@data-label='%s' or @title='%s']";
    private final String COMMON_BUTTON = ACTIVE_TAB_LOCATOR + "//*[@title='%s' or text()='%s']";
    private final String COMMON_RADIOBUTTON = ACTIVE_TAB_LOCATOR + "//span[text()='%s']/ancestor::span[@class='slds-radio']//span[@class='slds-radio_faux']";

    /**
     * Wait until Details tab is displayed.
     */
    @Step("Check that Details page was opened")
    public DetailsPage waitTillOpened() {
        $(By.xpath(ACTIVE_TAB_LOCATOR + "//a[@data-tab-value='detailTab']")).shouldBe(visible, timeout);
        return this;
    }

    public DetailsPage click(String locator, String value) {
        By tabLocator = By.xpath(String.format(locator, value, value));
        $(tabLocator).shouldBe(visible, timeout);
        clickJS(tabLocator);
        return this;
    }

    /**
     * Wait until specified tab is displayed.
     *
     * @param tabName
     * @return current instance of DetailsPage
     */
    @Step("Open '{tabName}' tab")
    public DetailsPage clickTab(String tabName) {
        log.info("Opening {} tab", tabName);
        return click(COMMON_TAB,tabName);
    }

    /**
     * Click any button on detail page
     *
     * @param buttonName
     * @return current instance of DetailsPage
     */
    @Step("Click '{buttonName}' button")
    public DetailsPage clickButton(String buttonName) {
        log.info("Click {} button", buttonName);
        return click(COMMON_BUTTON,buttonName);
    }

    /**
     * Select any radioButton on detail page
     *
     * @param radioButtonName
     * @return current instance of DetailsPage
     */
    @Step("Select '{radioButtonName}'")
    public DetailsPage selectRadioButton(String radioButtonName) {
        log.info("Select {}", radioButtonName);
        return click(COMMON_RADIOBUTTON,radioButtonName);
    }

    /**
     * Validate details fields.
     *
     * @param data dictionary of field name and expected field value
     * @return current instance of DetailsPage
     */
    @Step("Validation of entered data")
    public DetailsPage validate(Map<String, String> data) {
        log.info("Validating Details Data. Expected: {}", data);

        for (Map.Entry<String, String> entry : data.entrySet()) {
            String fieldLabel = entry.getKey();
            String value = entry.getValue();

            sfHelper.validate(fieldLabel, value);
        }
        return this;
    }

    /**
     * Validate any field at Details page. Usually used for comprehensive validation.
     *
     * @param label field label
     * @param value expected field value
     * @return current instance of DetailsPage
     */
    @Step("Validation of the field {label}")
    public DetailsPage validate(String label, String value) {
        sfHelper.validate(label, value);
        return this;
    }

    /**
     * Access object actions.
     *
     * @return instance of ObjectAction
     */
    public ObjectAction actions() {
        return new ObjectAction();
    }

    public Panel panels() {
        return new Panel();
    }

    public Table table(String tableName) {
        return new Table(tableName);
    }
}
