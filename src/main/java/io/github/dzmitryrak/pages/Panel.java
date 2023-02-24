package io.github.dzmitryrak.pages;

import java.util.Map;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;

public class Panel extends BasePage {
    String panelName;

    public Panel panel(String name) {
        panelName = name;
        $x(String.format(ACTIVE_TAB_LOCATOR + "//*[contains(text(), '%s')]/ancestor::article", name))
                .shouldBe(visible, timeout);
        return this;
    }

    /**
     * Validate any field on Details page. Usually used for comprehensive validation.
     *
     * @param field         field label
     * @param expectedValue
     * @return current instance of Panel
     */
    public Panel validate(String field, String expectedValue) {
        sfHelper.validate(panelName, field, expectedValue);
        return this;
    }

    /**
     * Validate details fields.
     *
     * @param data dictionary of field name and expected field value
     * @return current instance of Panel
     */
    public Panel validate(Map<String, String> data) {
        for (Map.Entry<String, String> entry : data.entrySet()) {
            String fieldLabel = entry.getKey();
            String value = entry.getValue();

            sfHelper.validate(panelName, fieldLabel, value);
        }
        return this;
    }
}
