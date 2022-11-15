package com.itechart.pages.acierto;

import com.codeborne.selenide.Selenide;
import com.itechart.pages.BasePage;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

@Log4j2
public class AciertoPage extends BasePage {

    private static final String ACIERTO_URL = "https://stg-funnel-life.acierto.com/seguros-vida/comparador/";
    private static final String INFO_DETAILS_LOCATOR = "//*[text()='%s']";
    private static final String DATA_LOCATOR = "[data-gtm=%s]";
    private static final By LIFE_INSURANCE_LABEL = By.xpath("//*[text() ='Seguro de vida']");

    @Step("Open Acierto Main Page")
    public AciertoPage open() {
        log.info("Opening Acierto Main page");
        Selenide.open(ACIERTO_URL);
        return this;
    }

    @Step("Choose insurance details")
    public AciertoPage insuranceDetailsClick(String locator) {
        log.info(String.format("Choosing %s as data for filling for and clicking on it", locator));
        $(By.xpath(String.format(INFO_DETAILS_LOCATOR, locator))).click();
        return this;
    }

    @Step("Click continue button")
    public AciertoPage clickContinueButton() {
        log.info("Clicking on continue button");
        Selenide.executeJavaScript("arguments[0].click();", $((String.format(DATA_LOCATOR, "continue"))));
        return this;
    }


    @Step("Filling the field with data")
    public AciertoPage setPersonData(String locator, String data) {
        log.info(String.format("Filling %s field with data %s", locator, data));
        $((String.format(DATA_LOCATOR, locator))).setValue(data);
        return this;
    }

    @Step("Check that the Page with options for insurance services is opened")
    public boolean isLifeInsurancePageIsOpened() {
        log.info("The page with options for insurance services is opened");
        $(LIFE_INSURANCE_LABEL).shouldBe(visible, Duration.ofSeconds(15));
        return $(LIFE_INSURANCE_LABEL).isDisplayed();
    }

    @Step("Setting person's data for creation the record")
    public AciertoPage setPersonRecord(String amount, String period, String dateOfBirth, String gender,
                                       String zipcode, String email, String phone) {
        open();
        insuranceDetailsClick(amount);
        insuranceDetailsClick(period);
        clickContinueButton();
        setPersonData("birth-date", dateOfBirth);
        insuranceDetailsClick(gender);
        setPersonData("zip-code", zipcode);
        clickContinueButton();
        setPersonData("email", email);
        setPersonData("phone", phone);
        clickContinueButton();
        isLifeInsurancePageIsOpened();

        return this;
    }
}
