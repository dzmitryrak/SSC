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
    private final String FILTER_LINK = "?filterName=00B7Y000004PyxjUAC";
    private final String INSURANCE_PERIOD = "Anual";
    private final String PERSON_GENDER = "Hombre";
    private final String TENANTID = "ES";
    private final String[] companiesArray = new String[]{"Asisa Vida", "Axa Vida Protec", "Santalucía Vida",
            "FIATC Vida", "Credit Andorra Life", "Zurich Vida", "Previs Vida", "Allianz Vida Riesgo"};
    private final String[] sortingLocatorValues = new String[] {"Clasificados en orden descendente", "Clasificados en orden ascendente"};
    private final String[] columnTitles = new String[] {"Número del caso", "Nombre del contacto", "Asunto", "Estado",
            "Prioridad","Fecha/Hora de apertura", "Alias del propietario del caso", "Correo electrónico Web" };

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
//        aciertoPage.setPersonRecord(INSURANCE_AMOUNT, INSURANCE_PERIOD, DATE_OF_BIRTH, PERSON_GENDER, ZIPCODE, EMAIL, PHONE)
//                .imInterestedButtonClick(1)
//                .isFinalModalDisplayed();
//        aciertoPage.callMeOnThisPhoneButtonClick()
//                .isGratitudeModalDisplayed();
//        aciertoPage.closeButtonClick()
//                .isLifeInsurancePageOpened();
        loginPage.open();
        loginPage.login(USERNAME, PASSWORD);
        homePage.isPageOpened();
        listView.open("Case");
        listView.filterSwitcherClick();
        listView.filterValueChoose(2);
        listView.columnSortingCheck(columnTitles[0], sortingLocatorValues[0]);
        listView.openObjectFromList(1);
        detailsPage.isDetailsPageOpened();
        detailsPage.clickTab(DetailsTabs.Detalles);
        Map<String, String> userdata = new HashMap<>() {
            {
                put("Correo electrónico Web", EMAIL);
                put("Teléfono del cliente", EXPECTED_PHONE);
            }
        };
        detailsPage.validate(userdata);

        detailsPage.clickDetailsTab();
        detailsPage.isDetailTabPageOpened();


    }


    /*
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
        caseListViewPage.openUrl();
        caseListViewPage.isPageOpened();
        caseListViewPage.openCase(1);
        caseDetailsPage.isDetailsCasePageOpened();
        caseDetailsPage.clickOnDetailsTab();
        caseDetailsPage.validateInput(EMAIL, PHONE, INSURANCE_AMOUNT, INSURANCE_PERIOD, companiesArray[0]);
        caseDetailsPage.clickOnAccountDetailsTab();
        caseDetailsPage.isPersonalDetailTabPageOpened();
        caseDetailsPage.validateTenantID(TENANTID);
    }
     */


    @Test(description = "Creation of the insurance record with provider 'Axa Vida Protec' and validation it in Salesforce")
    public void aciertoTestAxaVidaProtecValidation() {
        aciertoPage.setPersonRecord(INSURANCE_AMOUNT, INSURANCE_PERIOD, DATE_OF_BIRTH, PERSON_GENDER, ZIPCODE, EMAIL, PHONE)
                .imInterestedButtonClick(2)
                .isFinalModalDisplayed();
        aciertoPage.callMeOnThisPhoneButtonClick()
                .isGratitudeModalDisplayed();
        aciertoPage.closeButtonClick()
                .isLifeInsurancePageOpened();
        loginPage.open()
                .login(USERNAME, PASSWORD);
        homePage.isPageOpened();
        caseListViewPage.openUrl();
        caseListViewPage.isPageOpened();
        caseListViewPage.openCase(2);
        caseDetailsPage.isDetailsCasePageOpened();
        caseDetailsPage.clickOnDetailsTab();
//        caseDetailsPage.validateInput(EMAIL, PHONE, INSURANCE_AMOUNT, INSURANCE_PERIOD, companiesArray[1]);
//        caseDetailsPage.clickOnAccountDetailsTab();
//        caseDetailsPage.isPersonalDetailTabPageOpened();
        caseDetailsPage.validateTenantID(TENANTID);
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
        caseListViewPage.openUrl();
        caseListViewPage.isPageOpened();
        caseListViewPage.openCase(3);
        caseDetailsPage.isDetailsCasePageOpened();
        caseDetailsPage.clickOnDetailsTab();
//        caseDetailsPage.validateInput(EMAIL, PHONE, INSURANCE_AMOUNT, INSURANCE_PERIOD, companiesArray[2]);
//        caseDetailsPage.clickOnAccountDetailsTab();
//        caseDetailsPage.isPersonalDetailTabPageOpened();
        caseDetailsPage.validateTenantID(TENANTID);
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
        caseListViewPage.openUrl();
        caseListViewPage.isPageOpened();
        caseListViewPage.openCase(3);
        caseDetailsPage.isDetailsCasePageOpened();
        caseDetailsPage.clickOnDetailsTab();
//        caseDetailsPage.validateInput(EMAIL, PHONE, INSURANCE_AMOUNT, INSURANCE_PERIOD, companiesArray[3]);
//        caseDetailsPage.clickOnAccountDetailsTab();
//        caseDetailsPage.isPersonalDetailTabPageOpened();
        caseDetailsPage.validateTenantID(TENANTID);
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
        caseListViewPage.openUrl();
        caseListViewPage.isPageOpened();
        caseListViewPage.openCase(3);
        caseDetailsPage.isDetailsCasePageOpened();
        caseDetailsPage.clickOnDetailsTab();
//        caseDetailsPage.validateInput(EMAIL, PHONE, INSURANCE_AMOUNT, INSURANCE_PERIOD, companiesArray[4]);
//        caseDetailsPage.clickOnAccountDetailsTab();
//        caseDetailsPage.isPersonalDetailTabPageOpened();
        caseDetailsPage.validateTenantID(TENANTID);
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
        caseListViewPage.openUrl();
        caseListViewPage.isPageOpened();
        caseListViewPage.openCase(3);
        caseDetailsPage.isDetailsCasePageOpened();
        caseDetailsPage.clickOnDetailsTab();
//        caseDetailsPage.validateInput(EMAIL, PHONE, INSURANCE_AMOUNT, INSURANCE_PERIOD, companiesArray[5]);
//        caseDetailsPage.clickOnAccountDetailsTab();
//        caseDetailsPage.isPersonalDetailTabPageOpened();
        caseDetailsPage.validateTenantID(TENANTID);
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
        caseListViewPage.openUrl();
        caseListViewPage.isPageOpened();
        caseListViewPage.openCase(3);
        caseDetailsPage.isDetailsCasePageOpened();
        caseDetailsPage.clickOnDetailsTab();
//        caseDetailsPage.validateInput(EMAIL, PHONE, INSURANCE_AMOUNT, INSURANCE_PERIOD, companiesArray[6]);
//        caseDetailsPage.clickOnAccountDetailsTab();
//        caseDetailsPage.isPersonalDetailTabPageOpened();
        caseDetailsPage.validateTenantID(TENANTID);
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
        caseListViewPage.openUrl();
        caseListViewPage.isPageOpened();
        caseListViewPage.openCase(3);
        caseDetailsPage.isDetailsCasePageOpened();
        caseDetailsPage.clickOnDetailsTab();
//        caseDetailsPage.validateInput(EMAIL, PHONE, INSURANCE_AMOUNT, INSURANCE_PERIOD, companiesArray[7]);
//        caseDetailsPage.clickOnAccountDetailsTab();
//        caseDetailsPage.isPersonalDetailTabPageOpened();
        caseDetailsPage.validateTenantID(TENANTID);
    }
}