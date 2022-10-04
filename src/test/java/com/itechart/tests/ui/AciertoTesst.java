package com.itechart.tests.ui;

import com.itechart.models.Account;
import com.itechart.tests.ui.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class AciertoTesst extends BaseTest {

    @Test
    public void acierto() {
        driver.get("https://stg-funnel-life.acierto.com/seguros-vida/comparador/");
        driver.findElement(By.xpath("//*[text()='60.000€']")).click();
        driver.findElement(By.xpath("//*[text()='Anual']")).click();
        clickJS(By.cssSelector("[data-gtm=continue]"));
        driver.findElement(By.cssSelector("[data-gtm=birth-date]")).sendKeys("01/01/1992");
        driver.findElement(By.xpath("//p[text()='Hombre']")).click();
        driver.findElement(By.cssSelector("[data-gtm=zip-code]")).sendKeys("22007");
        clickJS(By.cssSelector("[data-gtm=continue]"));
        driver.findElement(By.cssSelector("[data-gtm=email]")).sendKeys("acierto1@mailinator.com");
        driver.findElement(By.cssSelector("[data-gtm=phone]")).sendKeys("911000222");
        clickJS(By.cssSelector("[data-gtm=continue]"));
        new WebDriverWait(driver, 60).until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".results-list")));
    }

    public void clickJS(By locator) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(locator));
    }
}