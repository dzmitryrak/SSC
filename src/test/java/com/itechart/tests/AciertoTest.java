package com.itechart.tests;

import com.itechart.constants.DetailsTabs;
import com.itechart.tests.base.BaseTest;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

public class AciertoTest extends BaseTest {

    private final String ZIPCODE = "22007";
    private final String DATE_OF_BIRTH = "11/10/1967";
    private final String INSURANCE_AMOUNT = "90.000€";
    private final String EMAIL = "acierto1@mailinator.com";
    private final String PHONE = "911000222";
    private final String EXPECTED_PHONE = String.format("+34%s", PHONE);
    private final String INSURANCE_PERIOD = "Anual";
    private final String PERSON_GENDER = "Hombre";
    private final String[] companiesArray = new String[]{"Asisa Vida", "Axa Vida Protec", "Santalucía Vida",
            "FIATC Vida", "Credit Andorra Life", "Zurich Vida", "Previs Vida", "Allianz Vida Riesgo"};
    private final String[] sortingLocatorValues = new String[]{"descending", "ascending"};
    private final String[] columnTitles = new String[]{"Número del caso", "Nombre del contacto", "Asunto", "Estado",
            "Prioridad", "Fecha/Hora de apertura", "Alias del propietario del caso", "Correo electrónico Web"};
    String NEW_INSURANCE_AMOUNT = INSURANCE_AMOUNT.replace("€", "");
    String NEW_INSURANCE_PERIOD = INSURANCE_PERIOD.replace("Anual", "Yearly");

    @Test(description = "Creation of the insurance record")
    public void acierto() {
        aciertoPage.setPersonRecord(INSURANCE_AMOUNT, INSURANCE_PERIOD, DATE_OF_BIRTH, PERSON_GENDER, ZIPCODE, EMAIL, PHONE)
                .imInterestedButtonClick(1)
                .isFinalModalDisplayed();
        aciertoPage.callMeOnThisPhoneButtonClick()
                .isGratitudeModalDisplayed();
        aciertoPage.closeButtonClick()
                .isLifeInsurancePageOpened();
    }

    @Test(description = "Creation of the insurance record with provider AsisaVida and validation it in Salesforce")
    public void aciertoTestAsisaVidaValidation() {
        aciertoPage.setPersonRecord(INSURANCE_AMOUNT, INSURANCE_PERIOD, DATE_OF_BIRTH, PERSON_GENDER, ZIPCODE, EMAIL, PHONE)
                .imInterestedButtonClick(1)
                .isFinalModalDisplayed();
        aciertoPage.callMeOnThisPhoneButtonClick()
                .isGratitudeModalDisplayed();
        aciertoPage.closeButtonClick()
                .isLifeInsurancePageOpened();
        loginPage.open();
        loginPage.login(USERNAME, PASSWORD);
        homePage.isPageOpened();
        listView.open("Case");
        listView.filterSwitcherClick();
        listView.filterValueChoose(2);
        listView.sortColumnAscDesc(columnTitles[0], sortingLocatorValues[0]);
        listView.openObjectFromList(1);
        detailsPage.waitTillOpened();
        detailsPage.clickTab(DetailsTabs.Detalles);
        Map<String, String> userdata = new HashMap<>() {
            {
                put("Correo electrónico Web", EMAIL);
                put("Teléfono del cliente", EXPECTED_PHONE);
                //TODO раскомментировать после доработки sfHelper
//                put("Teléfono", EXPECTED_PHONE);
//                put("Email", EMAIL);
//                put("Cantidad de capital", NEW_INSURANCE_AMOUNT);
//                put("Pago de frecuencia", NEW_INSURANCE_PERIOD);
//                put("Nombre del producto", companiesArray[0]);
            }
        };
        detailsPage.validate(userdata);
    }

    @Test(description = "Creation of the insurance record with provider 'Axa Vida Protec' and validation it in Salesforce")
    public void aciertoTestAxaVidaProtecValidation() {
        aciertoPage.setPersonRecord(INSURANCE_AMOUNT, INSURANCE_PERIOD, DATE_OF_BIRTH, PERSON_GENDER, ZIPCODE, EMAIL, PHONE)
                .imInterestedButtonClick(2)
                .isFinalModalDisplayed();
        aciertoPage.callMeOnThisPhoneButtonClick()
                .isGratitudeModalDisplayed();
        aciertoPage.closeButtonClick()
                .isLifeInsurancePageOpened();
        loginPage.open();
        loginPage.login(USERNAME, PASSWORD);
        homePage.isPageOpened();
        listView.open("Case");
        listView.filterSwitcherClick();
        listView.filterValueChoose(2);
        listView.sortColumnAscDesc(columnTitles[0], sortingLocatorValues[0]);
        listView.openObjectFromList(1);
        detailsPage.waitTillOpened();
        detailsPage.clickTab(DetailsTabs.Detalles);
        Map<String, String> userdata = new HashMap<>() {
            {
                put("Correo electrónico Web", EMAIL);
                put("Teléfono del cliente", EXPECTED_PHONE);
                //TODO раскомментировать после доработки sfHelper
//                put("Teléfono", EXPECTED_PHONE);
//                put("Email", EMAIL);
//                put("Cantidad de capital", NEW_INSURANCE_AMOUNT);
//                put("Pago de frecuencia", NEW_INSURANCE_PERIOD);
//                put("Nombre del producto", companiesArray[2]);
            }
        };
        detailsPage.validate(userdata);
    }

    @Test(description = "Creation of the insurance record with provider 'Santalucía Vida' and validation it in Salesforce")
    public void aciertoTestSantaluciaVidaValidation() {
        aciertoPage.setPersonRecord(INSURANCE_AMOUNT, INSURANCE_PERIOD, DATE_OF_BIRTH, PERSON_GENDER, ZIPCODE, EMAIL, PHONE)
                .imInterestedButtonClick(3)
                .isFinalModalDisplayed();
        aciertoPage.callMeOnThisPhoneButtonClick()
                .isGratitudeModalDisplayed();
        aciertoPage.closeButtonClick()
                .isLifeInsurancePageOpened();
        loginPage.open();
        loginPage.login(USERNAME, PASSWORD);
        homePage.isPageOpened();
        listView.open("Case");
        listView.filterSwitcherClick();
        listView.filterValueChoose(2);
        listView.sortColumnAscDesc(columnTitles[0], sortingLocatorValues[0]);
        listView.openObjectFromList(1);
        detailsPage.waitTillOpened();
        detailsPage.clickTab(DetailsTabs.Detalles);
        Map<String, String> userdata = new HashMap<>() {
            {
                put("Correo electrónico Web", EMAIL);
                put("Teléfono del cliente", EXPECTED_PHONE);
                //TODO раскомментировать после доработки sfHelper
//                put("Teléfono", EXPECTED_PHONE);
//                put("Email", EMAIL);
//                put("Cantidad de capital", NEW_INSURANCE_AMOUNT);
//                put("Pago de frecuencia", NEW_INSURANCE_PERIOD);
//                put("Nombre del producto", companiesArray[3]);
            }
        };
        detailsPage.validate(userdata);
    }

    @Test(description = "Creation of the insurance record with provider 'FIATC Vida' and validation it in Salesforce")
    public void aciertoTestFiatcVidaValidation() {
        aciertoPage.setPersonRecord(INSURANCE_AMOUNT, INSURANCE_PERIOD, DATE_OF_BIRTH, PERSON_GENDER, ZIPCODE, EMAIL, PHONE)
                .imInterestedButtonClick(4)
                .isFinalModalDisplayed();
        aciertoPage.callMeOnThisPhoneButtonClick()
                .isGratitudeModalDisplayed();
        aciertoPage.closeButtonClick()
                .isLifeInsurancePageOpened();
        loginPage.open();
        loginPage.login(USERNAME, PASSWORD);
        homePage.isPageOpened();
        listView.open("Case");
        listView.filterSwitcherClick();
        listView.filterValueChoose(2);
        listView.sortColumnAscDesc(columnTitles[0], sortingLocatorValues[0]);
        listView.openObjectFromList(1);
        detailsPage.waitTillOpened();
        detailsPage.clickTab(DetailsTabs.Detalles);
        Map<String, String> userdata = new HashMap<>() {
            {
                put("Correo electrónico Web", EMAIL);
                put("Teléfono del cliente", EXPECTED_PHONE);
                //TODO раскомментировать после доработки sfHelper
//                put("Teléfono", EXPECTED_PHONE);
//                put("Email", EMAIL);
//                put("Cantidad de capital", NEW_INSURANCE_AMOUNT);
//                put("Pago de frecuencia", NEW_INSURANCE_PERIOD);
//                put("Nombre del producto", companiesArray[4]);
            }
        };
        detailsPage.validate(userdata);
    }

    @Test(description = "Creation of the insurance record with provider 'Credit Andorra Life' and validation it in Salesforce")
    public void aciertoTestCreditAndorraLifeValidation() {
        aciertoPage.setPersonRecord(INSURANCE_AMOUNT, INSURANCE_PERIOD, DATE_OF_BIRTH, PERSON_GENDER, ZIPCODE, EMAIL, PHONE)
                .imInterestedButtonClick(5)
                .isFinalModalDisplayed();
        aciertoPage.callMeOnThisPhoneButtonClick()
                .isGratitudeModalDisplayed();
        aciertoPage.closeButtonClick()
                .isLifeInsurancePageOpened();
        loginPage.open();
        loginPage.login(USERNAME, PASSWORD);
        homePage.isPageOpened();
        listView.open("Case");
        listView.filterSwitcherClick();
        listView.filterValueChoose(2);
        listView.sortColumnAscDesc(columnTitles[0], sortingLocatorValues[0]);
        listView.openObjectFromList(1);
        detailsPage.waitTillOpened();
        detailsPage.clickTab(DetailsTabs.Detalles);
        Map<String, String> userdata = new HashMap<>() {
            {
                put("Correo electrónico Web", EMAIL);
                put("Teléfono del cliente", EXPECTED_PHONE);
                //TODO раскомментировать после доработки sfHelper
//                put("Teléfono", EXPECTED_PHONE);
//                put("Email", EMAIL);
//                put("Cantidad de capital", NEW_INSURANCE_AMOUNT);
//                put("Pago de frecuencia", NEW_INSURANCE_PERIOD);
//                put("Nombre del producto", companiesArray[5]);
            }
        };
        detailsPage.validate(userdata);
    }

    @Test(description = "Creation of the insurance record with provider 'Zurich Vida' and validation it in Salesforce")
    public void aciertoTestZurichVidaValidation() {
        aciertoPage.setPersonRecord(INSURANCE_AMOUNT, INSURANCE_PERIOD, DATE_OF_BIRTH, PERSON_GENDER, ZIPCODE, EMAIL, PHONE)
                .imInterestedButtonClick(6)
                .isFinalModalDisplayed();
        aciertoPage.callMeOnThisPhoneButtonClick()
                .isGratitudeModalDisplayed();
        aciertoPage.closeButtonClick()
                .isLifeInsurancePageOpened();
        loginPage.open();
        loginPage.login(USERNAME, PASSWORD);
        homePage.isPageOpened();
        listView.open("Case");
        listView.filterSwitcherClick();
        listView.filterValueChoose(2);
        listView.sortColumnAscDesc(columnTitles[0], sortingLocatorValues[0]);
        listView.openObjectFromList(1);
        detailsPage.waitTillOpened();
        detailsPage.clickTab(DetailsTabs.Detalles);
        Map<String, String> userdata = new HashMap<>() {
            {
                put("Correo electrónico Web", EMAIL);
                put("Teléfono del cliente", EXPECTED_PHONE);
                //TODO раскомментировать после доработки sfHelper
//                put("Teléfono", EXPECTED_PHONE);
//                put("Email", EMAIL);
//                put("Cantidad de capital", NEW_INSURANCE_AMOUNT);
//                put("Pago de frecuencia", NEW_INSURANCE_PERIOD);
//                put("Nombre del producto", companiesArray[6]);
            }
        };
        detailsPage.validate(userdata);
    }

    @Test(description = "Creation of the insurance record with provider 'Previs Vida' and validation it in Salesforce")
    public void aciertoTestPrevisVidaValidation() {
        aciertoPage.setPersonRecord(INSURANCE_AMOUNT, INSURANCE_PERIOD, DATE_OF_BIRTH, PERSON_GENDER, ZIPCODE, EMAIL, PHONE)
                .imInterestedButtonClick(7)
                .isFinalModalDisplayed();
        aciertoPage.callMeOnThisPhoneButtonClick()
                .isGratitudeModalDisplayed();
        aciertoPage.closeButtonClick()
                .isLifeInsurancePageOpened();
        loginPage.open();
        loginPage.login(USERNAME, PASSWORD);
        homePage.isPageOpened();
        listView.open("Case");
        listView.filterSwitcherClick();
        listView.filterValueChoose(2);
        listView.sortColumnAscDesc(columnTitles[0], sortingLocatorValues[0]);
        listView.openObjectFromList(1);
        detailsPage.waitTillOpened();
        detailsPage.clickTab(DetailsTabs.Detalles);
        Map<String, String> userdata = new HashMap<>() {
            {
                put("Correo electrónico Web", EMAIL);
                put("Teléfono del cliente", EXPECTED_PHONE);
                //TODO раскомментировать после доработки sfHelper
//                put("Teléfono", EXPECTED_PHONE);
//                put("Email", EMAIL);
//                put("Cantidad de capital", NEW_INSURANCE_AMOUNT);
//                put("Pago de frecuencia", NEW_INSURANCE_PERIOD);
//                put("Nombre del producto", companiesArray[7]);
            }
        };
        detailsPage.validate(userdata);
    }

    @Test(description = "Creation of the insurance record with provider 'Allianz Vida Riesgo' and validation it in Salesforce")
    public void aciertoTestAllianzVidaRiesgoValidation() {
        aciertoPage.setPersonRecord(INSURANCE_AMOUNT, INSURANCE_PERIOD, DATE_OF_BIRTH, PERSON_GENDER, ZIPCODE, EMAIL, PHONE)
                .imInterestedButtonClick(8)
                .isFinalModalDisplayed();
        aciertoPage.callMeOnThisPhoneButtonClick()
                .isGratitudeModalDisplayed();
        aciertoPage.closeButtonClick()
                .isLifeInsurancePageOpened();
        loginPage.open();
        loginPage.login(USERNAME, PASSWORD);
        homePage.isPageOpened();
        listView.open("Case");
        listView.filterSwitcherClick();
        listView.filterValueChoose(2);
        listView.sortColumnAscDesc(columnTitles[0], sortingLocatorValues[0]);
        listView.openObjectFromList(1);
        detailsPage.waitTillOpened();
        detailsPage.clickTab(DetailsTabs.Detalles);
        Map<String, String> userdata = new HashMap<>() {
            {
                put("Correo electrónico Web", EMAIL);
                put("Teléfono del cliente", EXPECTED_PHONE);
                //TODO раскомментировать после доработки sfHelper
//                put("Teléfono", EXPECTED_PHONE);
//                put("Email", EMAIL);
//                put("Cantidad de capital", NEW_INSURANCE_AMOUNT);
//                put("Pago de frecuencia", NEW_INSURANCE_PERIOD);
//                put("Nombre del producto", companiesArray[8]);
            }
        };
        detailsPage.validate(userdata);
    }
}