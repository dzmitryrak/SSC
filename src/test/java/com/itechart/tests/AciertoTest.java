package com.itechart.tests;

import com.itechart.pages.DetailsPage;
import com.itechart.tests.base.BaseTest;
import io.qameta.allure.Step;
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
        aciertoPage.setPersonRecord(INSURANCE_AMOUNT, INSURANCE_PERIOD, DATE_OF_BIRTH, PERSON_GENDER, ZIPCODE, EMAIL, PHONE);
    }

    @Test(description = "Creation of the insurance record and validation it in Salesforce")
    public void aciertoTestValidation() {
        aciertoPage.setPersonRecord(INSURANCE_AMOUNT, INSURANCE_PERIOD, DATE_OF_BIRTH, PERSON_GENDER, ZIPCODE, EMAIL, PHONE);
        loginPage.open();
        loginPage.login(USERNAME, PASSWORD);
        homePage.isPageOpened();
        caseListViewPage.openUrl();
        caseListViewPage.isPageOpened();
        caseListViewPage.openCase(1);
        detailsPage.openDetails();
        validateInput(EMAIL, PHONE, INSURANCE_AMOUNT, INSURANCE_PERIOD);
    }

    @Step("Validation of fields filled")
    private DetailsPage validateInput(String email, String phone, String amount, String period) {

        String expectedPhone = String.format("+34%s", phone);
        String newExpectedAmount = amount.replace("€", "");
        String newExpectedPeriod = period.replace("Annual", "Yearly");

        detailsPage.validateInputField("Email", email);
        detailsPage.validateInputField("Correo electrónico Web", email);
        detailsPage.validateInputField("Teléfono", expectedPhone);
        detailsPage.validateInputField("Teléfono del cliente", phone);
        detailsPage.validateInputOpportunity("Cantidad de capital", newExpectedAmount);
        detailsPage.validateInputOpportunity("Pago de frecuencia", newExpectedPeriod);

        return detailsPage;
    }
}