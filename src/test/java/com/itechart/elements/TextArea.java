package com.itechart.elements;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static com.codeborne.selenide.Selenide.$;
import static java.lang.String.format;

@Log4j2
public class TextArea {
    String label;
    String textAreaLocator = "//records-lwc-detail-panel//*[text()='%s']" +
            "/ancestor::*[contains(@class, 'uiInput') or contains(@class, 'slds-form-element')]//textarea";

    public TextArea(String label) {
        this.label = label;
    }

    public void write(String text) {
        log.debug("Writing text '{}' into text-area field with label {}", text, label);
        $(By.xpath(format(textAreaLocator, label))).sendKeys(text);
    }

    public void clear() {
        log.debug("Deleting text from text-area field with label {}", label);
        $(By.xpath(format(textAreaLocator, label))).clear();
    }
}
