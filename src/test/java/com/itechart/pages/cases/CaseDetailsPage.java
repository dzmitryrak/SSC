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

    @Step("Clicking on the first case")
    public CaseDetailsPage clickOnDetailsTab() {
        log.info("Clicking on the first case");
        $(DETAILS_TAB).shouldBe(Condition.visible);
        $(DETAILS_TAB).click();
        return this;
    }

    @Step("Validation of fields filled")
    public CaseDetailsPage validateInput(String email, String phone, String amount, String period) {
        log.info("Validation of fields filled");

        String expectedPhone = String.format("+34%s", phone);
        String actualEmail = $(By.xpath(String.format(DETAILS_TAB_FIELD_LOCATOR, "Email"))).getText();
        String actualDetailsEmail = $(By.xpath(String.format(DETAILS_TAB_FIELD_LOCATOR, "Correo electrónico Web"))).getText();
        String actualPhone = $(By.xpath(String.format(DETAILS_TAB_FIELD_LOCATOR, "Teléfono"))).getText();
        String actualDetailsPhone = $(By.xpath(String.format(DETAILS_TAB_FIELD_LOCATOR, "Teléfono del cliente"))).getText();
        String actualAmount = $(By.xpath(String.format(DETAILS_TAB_OPPORTUNITY, "Cantidad de capital"))).getValue();
        String actualPeriod = $(By.xpath(String.format(DETAILS_TAB_OPPORTUNITY, "Pago de frecuencia"))).getValue();

        String newExpectedAmount = amount.replace("€", "");
        String newExpectedPeriod = period.replace("Anual", "Yearly");

        Assert.assertTrue(actualEmail.contains(email), String.format("Email input is not correct.Expected: '%s' Actual: '%s'", email, actualEmail));
        Assert.assertTrue(actualDetailsEmail.contains(email), String.format("Correo electrónico Web is not correct.Expected: '%s' Actual: '%s'", email, actualDetailsEmail));
        Assert.assertTrue(actualPhone.contains(phone), String.format("Phone number input is not correct.Expected: '%s' Actual: '%s'", expectedPhone, actualPhone));
        Assert.assertTrue(actualDetailsPhone.contains(phone), String.format("Teléfono del cliente input is not correct.Expected: '%s' Actual: '%s'", expectedPhone, actualDetailsPhone));
        Assert.assertTrue(actualAmount.contains(newExpectedAmount), String.format("Cantidad de capital input is not correct. Expected: %s Actual: %s", newExpectedAmount, actualAmount));
        Assert.assertTrue(actualPeriod.contains(newExpectedPeriod), String.format("Pago de frecuencia input is not correct. Expected: %s Actual: %s", newExpectedPeriod, period));
        return this;
    }
}
