package com.itechart.tests;

import com.itechart.constants.DetailsTabs;
import com.itechart.tests.base.BaseTest;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

public class AciertoTest extends BaseTest {

    private final String ZIPCODE = "22007";
    private final String DATE_OF_BIRTH = "01/01/1992";
    private final String INSURANCE_AMOUNT = "90.000€";
    private final String EMAIL = "acierto1@mailinator.com";
    private final String PHONE = "911000222";
    private final String INSURANCE_PERIOD = "Anual";
    private final String PERSON_GENDER = "Hombre";

    @Test(description = "Creation of the insurance record and validation it in Salesforce")
    public void aciertoTestValidation() {
        aciertoPage.setPersonRecord(INSURANCE_AMOUNT, INSURANCE_PERIOD, DATE_OF_BIRTH, PERSON_GENDER, ZIPCODE, EMAIL, PHONE);
        loginPage.open();
        loginPage.login(USERNAME, PASSWORD);
        homePage.isPageOpened();
        listView
                .openUrl("Case")
                .openObjectFromList(1);
        detailsPage.clickTab(DetailsTabs.Details);

        Map<String, String> account = new HashMap<>() {{
            put("Email", EMAIL);
            put("Correo electrónico Web", EMAIL);
            put("Teléfono", String.format("+34%s", PHONE));
            put("Teléfono del cliente", String.format("+34%s", PHONE));
            put("Cantidad de capital", INSURANCE_AMOUNT.replace("€", ""));
            put("Pago de frecuencia", INSURANCE_PERIOD.replace("Annual", "Yearly"));
        }};

        detailsPage.validate(account);
    }
}