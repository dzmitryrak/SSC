package com.itechart.elements;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

@Log4j2
public class TextArea {
    WebDriver driver;
    String label;
    String textAreaLocator = "//records-lwc-detail-panel//*[text()='%s']" +
            "/ancestor::*[contains(@class, 'uiInput') or contains(@class, 'slds-form-element')]//textarea";

    public TextArea(WebDriver driver, String label) {
        this.driver = driver;
        this.label = label;
    }

    public void write(String text) {
        log.debug("Writing text '{}' into text-area field with label {}", text, label);
        driver.findElement(By.xpath(String.format(textAreaLocator, label))).sendKeys(text);
    }

    public void clear() {
        log.debug("Deleting text from text-area field with label {}", label);
        driver.findElement(By.xpath(String.format(textAreaLocator, label))).clear();
    }
}
