package io.github.dzmitryrak.pages;

import com.codeborne.selenide.Condition;
import io.github.dzmitryrak.constants.DetailsTabs;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;

import java.time.Duration;
import java.util.Map;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;

/**
 * Class representing Salesforce details page.
 */
@Log4j2
public class DetailsPage extends BasePage {
   private final String COMMON_TAB = ACTIVE_TAB_LOCATOR + "//a[@data-label='%s']";

    @Step("Check that Details page was opened")
    public DetailsPage waitTillOpened() {
        $(By.xpath(String.format(COMMON_TAB, DetailsTabs.Details))).shouldBe(visible, Duration.ofSeconds(20));
        return this;
    }

    @Step("Check that Details page was opened")
    public DetailsPage waitTillOpened(String tabTitle) {
        $(By.xpath(String.format(COMMON_TAB, tabTitle))).shouldBe(visible, Duration.ofSeconds(20));
        return this;
    }

    @Step("Open {tabName} tab")
    public DetailsPage clickTab(String tabName) {
        log.info("Opening {} tab", tabName);

        By tabLocator = By.xpath(String.format(COMMON_TAB, tabName));
        $(tabLocator).shouldBe(Condition.visible, Duration.ofSeconds(10));
        clickJS(tabLocator);
        waitForPageLoaded();
        waitTillOpened(tabName);

        return this;
    }

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
     * Method allows to validate any field at Details page. Usually used for comprehensive validation
     *
     * @param label
     * @param value
     * @return
     */
    @Step("Validation of the field {label}")
    public DetailsPage validate(String label, String value) {
        sfHelper.validate(label, value);
        return this;
    }

    public ObjectAction actions() {
        return new ObjectAction();
    }

    public Panel panels() {
        return new Panel();
    }
}
