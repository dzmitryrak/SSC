package com.itechart.pages.acierto;
import com.codeborne.selenide.Selenide;
import com.itechart.pages.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import static com.codeborne.selenide.Selenide.$;

public class AciertoPage extends BasePage {

    private final String ACIERTO_URL = "https://stg-funnel-life.acierto.com/seguros-vida/comparador/";
    private static final String INSURANCE_DETAILS_LOCATOR = "//*[text()='%s']";
    private static final String DATA_LOCATOR = "[data-gtm=%s]";
    private static final String PERSON_GENDER_LOCATOR = "//p[text()='%s']";

    @Step("Open Acierto Main Page")
    public AciertoPage open() {
        Selenide.open(ACIERTO_URL);
        return this;
    }

    @Step("Choose amount of insurance")
    public AciertoPage insuranceAmountClick(String amount){
        $(By.xpath(String.format(INSURANCE_DETAILS_LOCATOR, amount))).click();
        return this;
    }

    @Step("Choose period of insurance")
    public AciertoPage insurancePeriodClick(String period) {
        $(By.xpath(String.format(INSURANCE_DETAILS_LOCATOR, period))).click();
        return this;
    }

    @Step("Click continue button")
    public AciertoPage clickContinueButton() {
        Selenide.executeJavaScript("arguments[0].click();", $(By.cssSelector(String.format(DATA_LOCATOR, "continue"))));
        return this;
    }

    public void clickJS(By locator) {
        Selenide.executeJavaScript("arguments[0].click();", $(locator));
    }

    @Step("Set date of birth")
    public AciertoPage chooseDateOfBirth(String dateOfBirth) {
        $(By.cssSelector(String.format(DATA_LOCATOR, "birth-date"))).setValue(dateOfBirth);
        return this;
    }

    @Step("Set gender of the person")
    public AciertoPage setPersonsGender(String gender) {
        $(By.xpath(String.format(PERSON_GENDER_LOCATOR, gender))).click();
        return this;
    }

    @Step("Set zipcode")
    public AciertoPage setZipCode(String zipcode) {
        $(By.cssSelector(String.format(DATA_LOCATOR, "zip-code"))).setValue(zipcode);
        return this;
    }

    @Step("Set email")
    public AciertoPage setEmail(String email) {
        $(By.cssSelector(String.format(DATA_LOCATOR, "email"))).setValue(email);
        return this;
    }

    @Step("Set phone")
    public AciertoPage setPhone(String phone) {
        $(By.cssSelector(String.format(DATA_LOCATOR, "phone"))).setValue(phone);
        return this;
    }
}
