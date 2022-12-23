package io.github.dzmitryrak.pages;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;

import java.time.Duration;
import java.util.Map;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;

@Log4j2
public class DetailsPage extends BasePage {
   private final String COMMON_TAB = ACTIVE_TAB_LOCATOR + "//a[@data-label='%s']";

    /**
     * Wait until Details tab is displayed.
     */
    @Step("Check that Details page was opened")
    public DetailsPage waitTillOpened() {
        $(By.xpath(String.format(COMMON_TAB, "Details"))).shouldBe(visible, Duration.ofSeconds(20));
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

        By tabLocator = By.xpath(String.format(COMMON_TAB, tabName));
        $(tabLocator).shouldBe(Condition.visible, Duration.ofSeconds(10));
        clickJS(tabLocator);
        waitForPageLoaded();
        $(tabLocator).shouldBe(visible, Duration.ofSeconds(10));
        return this;
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
}
