package com.itechart.tests;

import com.itechart.tests.base.BaseTest;
import org.testng.annotations.Test;

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

        aciertoPage.open()
                .insuranceAmountClick(INSURANCE_AMOUNT)
                .insurancePeriodClick(INSURANCE_PERIOD)
                .clickContinueButton()
                .chooseDateOfBirth(DATE_OF_BIRTH)
                .setPersonsGender(PERSON_GENDER)
                .setZipCode(ZIPCODE)
                .clickContinueButton()
                .setEmail(EMAIL)
                .setPhone(PHONE)
                .clickContinueButton();
    }

    @Test(description = "Creation of the insurance record and validation it in Salesforce")
    public void aciertoTestValidation() {
        aciertoPage.open()
                .insuranceAmountClick(INSURANCE_AMOUNT)
                .insurancePeriodClick(INSURANCE_PERIOD)
                .clickContinueButton()
                .chooseDateOfBirth(DATE_OF_BIRTH)
                .setPersonsGender(PERSON_GENDER)
                .setZipCode(ZIPCODE)
                .clickContinueButton()
                .setEmail(EMAIL)
                .setPhone(PHONE)
                .clickContinueButton();
        loginPage.open();
        loginPage.login(USERNAME, PASSWORD);
        homePage.isPageOpened();
        caseListViewPage.openUrl();
        caseListViewPage.isPageOpened();
        caseListViewPage.openCase(1);
        caseDetailsPage.clickOnDetailsTab();
        caseDetailsPage.validateInput(EMAIL, PHONE, INSURANCE_AMOUNT, INSURANCE_PERIOD);
    }
}