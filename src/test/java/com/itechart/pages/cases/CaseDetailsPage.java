package com.itechart.pages.cases;

import com.codeborne.selenide.Condition;
import com.itechart.pages.BasePage;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.testng.Assert;

import java.time.Duration;
import java.util.Map;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

@Log4j2
public class CaseDetailsPage extends BasePage {

    private static final String DETAILS_TAB_FIELD_LOCATOR = "//*[text()='%s']/../..//*[contains(@class, 'slds-form-element__control')]";
    private static final String DETAILS_TAB_OPPORTUNITY = "//*[text()='%s']/../..//*[contains(@class, 'slds-input')]";
    protected final By DETAILS_TAB = By.xpath("//*[@title='Detalles']");
    private static final By ACCOUNT_DETAILS_TAB_LOCATOR = By.xpath("//*[contains(@class, 'slds-media__body slds-text-heading--small')]//a");
    private static final By PERSONAL_ACCOUNT_LOCATOR = By.xpath("//*[contains(@class, 'slds-media__body slds-text-heading--small')]//a");
    private static final By CONTACT_EMAIL_LOCATOR = By.xpath("(//p[@title='Correo electrónico del contacto'])[2]");




    @Step("Check that case page is opened")
    public boolean isDetailsCasePageOpened() {
        log.info("Check that Details Case page is opened");
        return $(CONTACT_EMAIL_LOCATOR).isDisplayed();
    }

    @Step("Clicking on Details tab")
    public CaseDetailsPage clickOnDetailsTab() {
        log.info("Clicking on Details tab to open it");
        $(DETAILS_TAB).shouldBe(visible, Duration.ofSeconds(10));
        $(DETAILS_TAB).click();
        return this;
    }

    @Step("Validation of fields filled")
    public CaseDetailsPage validateInput(String email, String phone, String amount, String period, String company) {
        validateInputField("Email", email);
        validateInputField("Correo electrónico Web", email);
        validateInputField("Teléfono", phone);
        validateInputField("Teléfono del cliente", phone);
        validateInputOpportunity("Cantidad de capital", amount);
        validateInputOpportunity("Pago de frecuencia", period);
        validateInputOpportunity("Nombre del producto", company);

        return this;
    }

    @Step("Clicking on Details tab")
    public CaseDetailsPage clickOnAccountDetailsTab() {
        log.info("Clicking on Account Details Tab");
        clickJS(ACCOUNT_DETAILS_TAB_LOCATOR);
        waitForPageLoaded();
        return this;
    }

    @Step("Check that personal detail page is opened")
    public boolean isDetailTabPageOpened() {
        log.info("The personal detail page is opened is opened");
        $(PERSONAL_ACCOUNT_LOCATOR).shouldBe(visible, Duration.ofSeconds(10));
        return $(PERSONAL_ACCOUNT_LOCATOR).isDisplayed();
    }

    @Step("Validation of fields filled")
    public CaseDetailsPage validateTenantID(String value) {
        log.info("Validate TenantID field value: {}",value);
        validateInputField("TenantID", value);
        return this;
    }

    public CaseDetailsPage validateInputField(String locator, String expectedInput) {
        String actualInput = $(By.xpath(String.format(DETAILS_TAB_FIELD_LOCATOR, locator))).getText();
        Assert.assertTrue(actualInput.contains(expectedInput), String.format("%s input is not correct.Expected: '%s' Actual: '%s'", locator, expectedInput, actualInput));
        log.debug("Validating {} input.Expected: '{}' Actual: '{}'", locator, expectedInput, actualInput);
        return this;
    }

    public CaseDetailsPage validateInputOpportunity(String locator, String expectedInput) {
        String actualInput = $(By.xpath(String.format(DETAILS_TAB_OPPORTUNITY, locator))).getValue();
        Assert.assertTrue(actualInput.contains(expectedInput), String.format("%s input is not correct.Expected: '%s' Actual: '%s'", locator, expectedInput, actualInput));
        log.debug("Validating {} input.Expected: '{}' Actual: {}", locator, expectedInput, actualInput);
        return this;
    }
}
