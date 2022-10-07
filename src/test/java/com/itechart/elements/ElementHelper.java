package com.itechart.elements;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class ElementHelper {
    public static final String BASE_DETAIL_PANEL = "//records-lwc-detail-panel";
    String pickList = BASE_DETAIL_PANEL + "//*[text()='%s']/ancestor::lightning-picklist//button";
    String textInput = BASE_DETAIL_PANEL + "//*[text()='%s']/ancestor::lightning-input//input";
    String lookUpField = BASE_DETAIL_PANEL + "//*[text()='%s']/ancestor::lightning-lookup//input";
    String textArea = BASE_DETAIL_PANEL + "//*[text()='%s']/ancestor::lightning-textarea//textarea";

    //TODO amamzing javadoc
    public void fill(WebDriver driver, String elementLabel, String value) {
        long startTime = System.currentTimeMillis();
        waitForPageLoaded();
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        String elementType;
        //Currency, Date, Date/time, Email, Number Percent Phone Text
         if(driver.findElements(By.xpath(String.format(textInput, elementLabel))).size() > 0) {
             elementType = "Text";
            if(StringUtils.isEmpty(value)) {
                driver.findElement(By.xpath(String.format(textInput, elementLabel))).clear();
            } else {
                driver.findElement(By.xpath(String.format(textInput, elementLabel))).sendKeys(value);
            }
            //PICKLIST
        } else if(driver.findElements(By.xpath(String.format(pickList, elementLabel))).size() > 0) {
             //TODO logging
             //TODO Implement multiselect option with separator
             elementType = "PickList";
             String lookupOption = BASE_DETAIL_PANEL + "//*[contains(text(), '%s')]/ancestor::lightning-base-combobox-item";
             JavascriptExecutor executor = (JavascriptExecutor) driver;
             WebElement element = driver.findElement(By.xpath(String.format(pickList, elementLabel)));
             executor.executeScript("arguments[0].click();", element);
             WebElement element1;
             //TODO how about just passing  "--None--" to clear?
             if(StringUtils.isEmpty(value)) {
                  element1 = driver.findElement(By.xpath(String.format(lookupOption, "--None--")));
             } else {
                  element1 = driver.findElement(By.xpath(String.format(lookupOption, value)));
             }
             executor.executeScript("arguments[0].click();", element1);
         } else if(driver.findElements(By.xpath(String.format(lookUpField, elementLabel))).size() > 0) {
            //Lookup Relationship
             elementType = "Lookup Relationship";
            //TODO add code to clear lookup
             String lookupOption = BASE_DETAIL_PANEL + "(//*[contains(text(), '%s')]/ancestor::lightning-base-combobox-item) [1]";

             JavascriptExecutor executor = (JavascriptExecutor) driver;
             WebElement element = driver.findElement(By.xpath(String.format(lookUpField, elementLabel)));
             executor.executeScript("arguments[0].click();", element);
             new WebDriverWait(driver, 5)
                     .until(ExpectedConditions.presenceOfElementLocated(By.xpath(String.format(lookupOption, value))));
             WebElement element1 = driver.findElement(By.xpath(String.format(lookupOption, value)));
             executor.executeScript("arguments[0].click();", element1);
        } else if(driver.findElements(By.xpath(String.format(textArea, elementLabel))).size() > 0) {
             //TextArea
             elementType = "Text Area";
             if(StringUtils.isEmpty(value)) {
                 driver.findElement(By.xpath(String.format(textArea, elementLabel))).clear();
             } else {
                 driver.findElement(By.xpath(String.format(textArea, elementLabel))).sendKeys(value);
             }
             //TODO add else if for checkbox
        } else {
             elementType = "ERROR ERROR ALARMA!!! Cannot identify element";
         }

        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        long endTime = System.currentTimeMillis();


        System.out.printf("Label: '%s' Element Type: '%s' Time Elapsed: '%sms'%n", elementLabel, elementType,(endTime - startTime));
    }

    public void clear(WebDriver driver, String elementLabel) {
        fill(driver, elementLabel, "");
    }

    public void waitForPageLoaded() {
        new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                return ((JavascriptExecutor) driver).executeScript("return document.readyState").toString().equals("complete");
            }
        };
    }
}
