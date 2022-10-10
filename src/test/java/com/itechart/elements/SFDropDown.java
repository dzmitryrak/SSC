package com.itechart.elements;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static com.codeborne.selenide.Selenide.$;

@Log4j2
public class SFDropDown {
    WebDriver driver;
    String label;
    String locator = "//records-lwc-detail-panel//span[text()='%s']" +
            "/ancestor::div[contains(@class,'uiInput')]//a";
    String optionLocator = "//*[contains(@class, 'uiMenuList') and contains(@class, 'visible')]//a[@title='%s']";

    public SFDropDown(String label) {
        this.label = label;
    }

    public void select(String option) {
        log.debug("Selecting option '{}' in drop-down {}", option, label);
        $(By.xpath(String.format(locator, label))).click();
//        WebElement element = new WebDriverWait(driver, 5).until(ExpectedConditions
//                .presenceOfElementLocated(By.xpath(String.format(optionLocator, option))));
        $(By.xpath(String.format(optionLocator, option))).click();
    }

    public void clear() {
        log.debug("Clearing option in drop-down {}", label);
        $(By.xpath(String.format(locator, label))).click();
//        WebElement element = new WebDriverWait(driver, 5).until(ExpectedConditions
//                .presenceOfElementLocated(By.xpath(String.format(optionLocator, "--None--"))));
        $(By.xpath(String.format(optionLocator, "--None--"))).click();
    }
}
