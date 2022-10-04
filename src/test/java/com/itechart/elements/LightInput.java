package com.itechart.elements;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

@Log4j2
public class LightInput {
    WebDriver driver;
    String label;
    String inputLocator = "//records-lwc-detail-panel//*[text()='%s']/parent::*[contains(@class,'slds-form-element')]//input";

    public LightInput(WebDriver driver, String label) {
        this.driver = driver;
        this.label = label;
    }

    public void write(String text) {
        log.debug("Writing text '{}' into input with label {}", text, label);
        driver.findElement(By.xpath(String.format(inputLocator, label))).sendKeys(text);
    }

    public void clear() {
        log.debug("Deleting text from input with label {}", label);
        driver.findElement(By.xpath(String.format(inputLocator, label))).clear();
    }
}
