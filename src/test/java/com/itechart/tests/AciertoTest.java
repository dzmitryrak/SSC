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
    private final String INSURANCE_PERIOD = "Anual";
    private final String PERSON_GENDER = "Hombre";

    @Test(description = "Creation of the insurance record")
    public void acierto() {

        aciertoPage.open();
        aciertoPage.insuranceAmountClick(INSURANCE_AMOUNT);
        aciertoPage.insurancePeriodClick(INSURANCE_PERIOD);
        aciertoPage.clickContinueButton();
        aciertoPage.chooseDateOfBirth(DATE_OF_BIRTH);
        aciertoPage.setPersonsGender(PERSON_GENDER);
        aciertoPage.setZipCode(ZIPCODE);
        aciertoPage.clickContinueButton();
        aciertoPage.setEmail(EMAIL);
        aciertoPage.setPhone(PHONE);
        aciertoPage.clickContinueButton();
    }

    @Test(description = "Creation of the insurance record and validation it in Salesforce")
    public void aciertoTestValidation() {
        aciertoPage.open();
        aciertoPage.insuranceAmountClick(INSURANCE_AMOUNT);
        aciertoPage.insurancePeriodClick(INSURANCE_PERIOD);
        aciertoPage.clickContinueButton();
        aciertoPage.chooseDateOfBirth(DATE_OF_BIRTH);
        aciertoPage.setPersonsGender(PERSON_GENDER);
        aciertoPage.setZipCode(ZIPCODE);
        aciertoPage.clickContinueButton();
        aciertoPage.setEmail(EMAIL);
        aciertoPage.setPhone(PHONE);
        aciertoPage.clickContinueButton();
        loginPage.open();
        loginPage.login(USERNAME, PASSWORD);
        homePage.isPageOpened();
        caseListViewPage.openUrl();
        caseListViewPage.isPageOpened();
        caseListViewPage.clickOnCase();
        caseDetailsPage.clickOnDetailsTab();
        caseDetailsPage.validateInput(EMAIL, PHONE, INSURANCE_AMOUNT, INSURANCE_PERIOD);
    }

    public void clickJS(By locator) {
        Selenide.executeJavaScript("arguments[0].click();", $(locator));
    }
}