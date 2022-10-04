package com.itechart.elements;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

@Log4j2
public class LightDropDown {
    WebDriver driver;
    String label;
    String inputLocator = "//records-lwc-detail-panel//*[text()='%s']/parent::*[contains(@class,'slds-form-element')]//button";
    String lookupOption = "//records-lwc-detail-panel//*[contains(text(), '%s')]/ancestor::lightning-base-combobox-item";

    public LightDropDown(WebDriver driver, String label) {
        this.driver = driver;
        this.label = label;
    }

    public void selectOption(String option) {
        log.info("Selecting option '{}' from {} lookup", option, label);
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        WebElement element = driver.findElement(By.xpath(String.format(inputLocator, label)));
        executor.executeScript("arguments[0].click();", element);
        WebElement element1 = driver.findElement(By.xpath(String.format(lookupOption, option)));
        executor.executeScript("arguments[0].click();", element1);
    }

    public void clear() {
        log.info("Clearing option in drop-down {}", label);
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        WebElement element = driver.findElement(By.xpath(String.format(inputLocator, label)));
        executor.executeScript("arguments[0].click();", element);
        WebElement element1 = driver.findElement(By.xpath(String.format(lookupOption, "--None--")));
        executor.executeScript("arguments[0].click();", element1);
    }
}
