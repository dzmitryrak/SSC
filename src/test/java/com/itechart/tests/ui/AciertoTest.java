package com.itechart.tests.ui;

import com.codeborne.selenide.Selenide;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.$;

public class AciertoTest extends BaseTest {

    @Test
    public void acierto() {
        Selenide.open("https://stg-funnel-life.acierto.com/seguros-vida/comparador/");
        $(By.xpath("//*[text()='60.000€']")).click();
        $(By.xpath("//*[text()='Anual']")).click();
        clickJS(By.cssSelector("[data-gtm=continue]"));
        $(By.cssSelector("[data-gtm=birth-date]")).sendKeys("01/01/1992");
        $(By.xpath("//p[text()='Hombre']")).click();
        $(By.cssSelector("[data-gtm=zip-code]")).sendKeys("22007");
        clickJS(By.cssSelector("[data-gtm=continue]"));
        $(By.cssSelector("[data-gtm=email]")).sendKeys("acierto1@mailinator.com");
        $(By.cssSelector("[data-gtm=phone]")).sendKeys("911000222");
        clickJS(By.cssSelector("[data-gtm=continue]"));
    }

    public void clickJS(By locator) {
        Selenide.executeJavaScript("arguments[0].click();", $(locator));
    }
}