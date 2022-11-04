package com.itechart.pages.cases;

import com.codeborne.selenide.Condition;
import com.itechart.pages.BasePage;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.testng.Assert;

import static com.codeborne.selenide.Selenide.$;

@Log4j2
public class CaseDetailsPage extends BasePage {

    private static final String DETAILS_TAB_FIELD_LOCATOR = "//*[text()='%s']/../..//*[contains(@class, 'slds-form-element__control')]";
    private static final String DETAILS_TAB_OPPORTUNITY = "//*[text()='%s']/../..//*[contains(@class, 'slds-input')]";
    protected final By DETAILS_TAB = By.xpath("//*[@title='Detalles']");

    @Step("Clicking on Details tab")
    public CaseDetailsPage clickOnDetailsTab() {
        log.info("Clicking on the first case");
        $(DETAILS_TAB).shouldBe(Condition.visible);
        $(DETAILS_TAB).click();
        return this;
    }

    @Step("Validation of fields filled")
    public CaseDetailsPage validateInput(String email, String phone, String amount, String period) {

        String expectedPhone = String.format("+34%s", phone);
        String newExpectedAmount = amount.replace("€", "");
        String newExpectedPeriod = period.replace("Anual", "Yearly");

        validateInputField("Email", email);
        validateInputField("Correo electrónico Web", email);
        validateInputField("Teléfono", expectedPhone);
        validateInputField("Teléfono del cliente", phone);
        validateInputOpportunity("Cantidad de capital", newExpectedAmount);
        validateInputOpportunity("Pago de frecuencia", newExpectedPeriod);
        return this;
    }

    public CaseDetailsPage validateInputField(String locator, String expectedInput) {
        String actualInput = $(By.xpath(String.format(DETAILS_TAB_FIELD_LOCATOR, locator))).getText();
        Assert.assertTrue(actualInput.contains(expectedInput), String.format("%s input is not correct.Expected: '%s' Actual: '%s'", locator, expectedInput, actualInput));
        log.debug(String.format("Validating %s input.Expected: '%s' Actual: '%s'", locator, expectedInput, actualInput));
        return this;
    }

    public CaseDetailsPage validateInputOpportunity(String locator, String expectedInput) {
        String actualInput = $(By.xpath(String.format(DETAILS_TAB_OPPORTUNITY, locator))).getValue();
        Assert.assertTrue(actualInput.contains(expectedInput), String.format("%s input is not correct.Expected: '%s' Actual: '%s'", locator, expectedInput, actualInput));
        log.debug(String.format("Validating %s input.Expected: '%s' Actual: '%s'", locator, expectedInput, actualInput));
        return this;
    }
}
