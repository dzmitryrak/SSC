package com.itechart.elements;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static com.codeborne.selenide.Selenide.$;
import static org.openqa.selenium.By.xpath;

@Log4j2
public class SFInput {
    String label;
    String inputLocator = "//records-lwc-detail-panel//*[text()='%s']/ancestor::div[contains(@class,'uiInput')]//input";

    public SFInput(String label) {
        this.label = label;
    }

    public void write(String text) {
        log.debug("Writing text '{}' into input with label {}", text, label);
//        WebElement element = new WebDriverWait(driver, 5).until(ExpectedConditions
//                .presenceOfElementLocated(xpath(String.format(inputLocator, label))));
        $(xpath(String.format(inputLocator, label))).sendKeys(text);
    }

    public void clear() {
        log.debug("Deleting text from input with label {}", label);
    //    WebElement element = new WebDriverWait(driver, 5).until(ExpectedConditions.presenceOfElementLocated(xpath(String.format(inputLocator, label))));
        $(xpath(String.format(inputLocator, label))).clear();
    }
}