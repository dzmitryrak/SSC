package com.itechart.elements;

import com.codeborne.selenide.Selenide;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static com.codeborne.selenide.Selenide.$;

@Log4j2
public class LightDropDown {
    String label;
    String inputLocator = "//records-lwc-detail-panel//*[text()='%s']/parent::*[contains(@class,'slds-form-element')]//button";
    String lookupOption = "//records-lwc-detail-panel//*[contains(text(), '%s')]/ancestor::lightning-base-combobox-item";

    public LightDropDown(String label) {
        this.label = label;
    }

    public void selectOption(String option) {
        log.info("Selecting option '{}' from {} lookup", option, label);
        WebElement element = $(By.xpath(String.format(inputLocator, label)));
        Selenide.executeJavaScript("arguments[0].click();", element);
        WebElement element1 = $(By.xpath(String.format(lookupOption, option)));
        Selenide.executeJavaScript("arguments[0].click();", element1);
    }

    public void clear() {
        log.info("Clearing option in drop-down {}", label);
        WebElement element = $(By.xpath(String.format(inputLocator, label)));
        Selenide.executeJavaScript("arguments[0].click();", element);
        WebElement element1 = $(By.xpath(String.format(lookupOption, "--None--")));
        Selenide.executeJavaScript("arguments[0].click();", element1);
    }
}
