package com.itechart.tests;

import com.itechart.tests.base.BaseTest;
import org.testng.annotations.Test;

import java.lang.reflect.Array;

public class AciertoTest extends BaseTest {

    private final String ZIPCODE = "22007";
    private final String DATE_OF_BIRTH = "11/10/1967";
    private final String INSURANCE_AMOUNT = "90.000€";
    private final String EMAIL = "acierto1@mailinator.com";
    private final String PHONE = "911000222";
    private final String INSURANCE_PERIOD = "Anual";
    private final String PERSON_GENDER = "Hombre";
    private final String TENANTID = "ES";
    private String[] companiesArray = new String[] {"Asisa Vida", "Axa Vida Protec", "Santalucía Vida",
            "FIATC Vida", "Credit Andorra Life", "Zurich Vida", "Previs Vida", "Allianz Vida Riesgo"};

    @Test(description = "Creation of the insurance record")
    public void acierto() {
        aciertoPage.setPersonRecord(INSURANCE_AMOUNT, INSURANCE_PERIOD, DATE_OF_BIRTH, PERSON_GENDER, ZIPCODE, EMAIL, PHONE);
        aciertoPage.imInterestedButtonClick(1);
        aciertoPage.isFinalModalDisplayed();
        aciertoPage.callMeOnThisPhoneButtonClick();
        aciertoPage.isGratitudeModalDisplayed();
        aciertoPage.closeButtonClick();
        aciertoPage.isLifeInsurancePageOpened();
    }

    @Test(description = "Creation of the insurance record and validation it in Salesforce")
    public void aciertoTestAsisaVidaValidation() {
        aciertoPage.setPersonRecord(INSURANCE_AMOUNT, INSURANCE_PERIOD, DATE_OF_BIRTH, PERSON_GENDER, ZIPCODE, EMAIL, PHONE);
        aciertoPage.imInterestedButtonClick(1);
        aciertoPage.isFinalModalDisplayed();
        aciertoPage.callMeOnThisPhoneButtonClick();
        aciertoPage.isGratitudeModalDisplayed();
        aciertoPage.closeButtonClick();
        aciertoPage.isLifeInsurancePageOpened();
        loginPage.open();
        loginPage.login(USERNAME, PASSWORD);
        homePage.isPageOpened();
        caseListViewPage.openUrl();
        caseListViewPage.isPageOpened();
        caseListViewPage.openCase(1);
        caseDetailsPage.clickOnDetailsTab();
        caseDetailsPage.validateInput(EMAIL, PHONE, INSURANCE_AMOUNT, INSURANCE_PERIOD, companiesArray[0]);
        caseDetailsPage.clickOnAccountDetailsTab();
        caseDetailsPage.isPersonaDetailTabPageOpened();
        caseDetailsPage.validateTenantID(TENANTID);
    }
}