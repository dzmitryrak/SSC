package com.itechart.elements;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

@Log4j2
public class LightLookup {
    WebDriver driver;
    String label;
    String inputLocator = "//records-lwc-detail-panel//*[text()='%s']/parent::*[contains(@class,'slds-form-element')]//input";
    String lookupOption = "(//records-lwc-detail-panel//*[contains(text(), '%s')]/ancestor::lightning-base-combobox-item) [1]";
    String DELETE_ACTION_LOCATOR = "//records-lwc-detail-panel//*[text()='%s']/ancestor::*[@class='slds-form-element']//lightning-primitive-icon[@lightning-basecombobox_basecombobox]";

    public LightLookup(WebDriver driver, String label) {
        this.driver = driver;
        this.label = label;
    }

    public void selectOption(String option) {
        log.debug("Selecting option '{}' from {} lookup", option, label);
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        WebElement element = driver.findElement(By.xpath(String.format(inputLocator, label)));
        executor.executeScript("arguments[0].click();", element);
        WebElement element3 = new WebDriverWait(driver, 5)
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath(String.format(lookupOption, option))));
        WebElement element1 = driver.findElement(By.xpath(String.format(lookupOption, option)));
        executor.executeScript("arguments[0].click();", element1);
    }

    public void clear() {
        log.debug("Deleting lookup {}", label);
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        WebElement element = new WebDriverWait(driver, 5).until(ExpectedConditions.
                elementToBeClickable(By.xpath(String.format(DELETE_ACTION_LOCATOR, label))));
        WebElement element1 = driver.findElement(By.xpath(String.format(DELETE_ACTION_LOCATOR, label)));
        executor.executeScript("arguments[0].click();", element1);
    }
}
