package com.itechart.tests;

import com.codeborne.selenide.Selenide;
import com.itechart.tests.base.BaseTest;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.$;

public class AciertoTest extends BaseTest {

    private final String ZIPCODE = "22007";
    private final String DATE_OF_BIRTH = "01/01/1992";
    private final String INSURANCE_AMOUNT = "90.000€";
    private final String EMAIL = "acierto1@mailinator.com";
    private final String PHONE = "911000222";
    private final String PERIOD = "Anual";
    private final String PERIOD_LOCATOR = "//*[text()='%s']";

    @Test
    public void acierto() {
        Selenide.open("https://stg-funnel-life.acierto.com/seguros-vida/comparador/");
        $(By.xpath(String.format("//*[text()='%s']", INSURANCE_AMOUNT))).click();
        $(By.xpath(String.format(PERIOD_LOCATOR, PERIOD))).click();
        clickJS(By.cssSelector("[data-gtm=continue]"));
        $(By.cssSelector("[data-gtm=birth-date]")).sendKeys(DATE_OF_BIRTH);
        $(By.xpath("//p[text()='Hombre']")).click();
        $(By.cssSelector("[data-gtm=zip-code]")).sendKeys(ZIPCODE);
        clickJS(By.cssSelector("[data-gtm=continue]"));
        $(By.cssSelector("[data-gtm=email]")).sendKeys(EMAIL);
        $(By.cssSelector("[data-gtm=phone]")).sendKeys(PHONE);
        clickJS(By.cssSelector("[data-gtm=continue]"));
    }

    @Test
    public void aciertoTestValidation() {
        Selenide.open("https://stg-funnel-life.acierto.com/seguros-vida/comparador/");
        $(By.xpath(String.format("//*[text()='%s']", INSURANCE_AMOUNT))).click();
        $(By.xpath("//*[text()='Anual']")).click();
        clickJS(By.cssSelector("[data-gtm=continue]"));
        $(By.cssSelector("[data-gtm=birth-date]")).sendKeys(DATE_OF_BIRTH);
        $(By.xpath("//p[text()='Hombre']")).click();
        $(By.cssSelector("[data-gtm=zip-code]")).sendKeys(ZIPCODE);
        clickJS(By.cssSelector("[data-gtm=continue]"));
        $(By.cssSelector("[data-gtm=email]")).sendKeys(EMAIL);
        $(By.cssSelector("[data-gtm=phone]")).sendKeys(PHONE);
        clickJS(By.cssSelector("[data-gtm=continue]"));
        Selenide.closeWindow();
        loginPage.open();
        loginPage.login(USERNAME, PASSWORD);
        homePage.isPageOpened();
        caseListViewPage.openUrl();
        caseListViewPage.isPageOpened();
        caseListViewPage.clickOnCase();
        caseDetailsPage.clickOnDetailsTab();
        caseDetailsPage.validateInput(EMAIL, PHONE, INSURANCE_AMOUNT, PERIOD);
    }

    public void clickJS(By locator) {
        Selenide.executeJavaScript("arguments[0].click();", $(locator));
    }
}