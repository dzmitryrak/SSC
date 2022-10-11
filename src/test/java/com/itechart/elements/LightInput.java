package com.itechart.elements;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

@Log4j2
public class LightInput {
    String label;
    String inputLocator = "//records-lwc-detail-panel//*[text()='%s']/parent::*[contains(@class,'slds-form-element')]//input";

    public LightInput(String label) {
        this.label = label;
    }

    public void write(String text) {
        log.debug("Writing text '{}' into input with label {}", text, label);
        $(By.xpath(String.format(inputLocator, label))).sendKeys(text);
    }

    public void clear() {
        log.debug("Deleting text from input with label {}", label);
        $(By.xpath(String.format(inputLocator, label))).clear();
    }
}
