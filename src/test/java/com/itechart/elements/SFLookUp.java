package com.itechart.elements;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

@Log4j2
public class SFLookUp {
    WebDriver driver;
    String label;
    String inputLocator = "//records-lwc-detail-panel//*[text()='%s']" +
            "/ancestor::div[contains(@class,'uiInput')]//input";
    String lookupOption = "(//div[contains(@title, '%s')]//ancestor::li[not(contains(@class, 'invisible'))]) [1]";
    String DELETE_ACTION_LOCATOR = "//records-lwc-detail-panel//*[text()='%s']/ancestor::*[contains(@class,'uiInput')]//a[@class='deleteAction']";

    public SFLookUp(WebDriver driver, String label) {
        this.driver = driver;
        this.label = label;
    }

    public void selectOption(String option) {
        log.debug("Selecting option '{}' from {} lookup", option, label);
        By LOOKUP_OPTION = By.xpath(String.format(lookupOption, option));
        driver.findElement(By.xpath(String.format(inputLocator, label))).click();
        WebElement element = new WebDriverWait(driver, 10)
                .until(ExpectedConditions.presenceOfElementLocated(LOOKUP_OPTION));
        driver.findElement(LOOKUP_OPTION).click();
    }

    public void clear() {
        log.debug("Deleting lookup of {}", label);
        WebElement element = new WebDriverWait(driver, 5).until(ExpectedConditions
                .presenceOfElementLocated(By.xpath(String.format(DELETE_ACTION_LOCATOR, label))));
        driver.findElement(By.xpath(String.format(DELETE_ACTION_LOCATOR, label))).click();
    }
}
