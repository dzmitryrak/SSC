package com.itechart.pages.acierto;
import com.codeborne.selenide.Selenide;
import com.itechart.pages.BasePage;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import static com.codeborne.selenide.Selenide.$;

@Log4j2
public class AciertoPage extends BasePage {

    private final String ACIERTO_URL = "https://stg-funnel-life.acierto.com/seguros-vida/comparador/";
    private static final String INFO_DETAILS_LOCATOR = "//*[text()='%s']";
    private static final String DATA_LOCATOR = "[data-gtm=%s]";

    @Step("Open Acierto Main Page")
    public AciertoPage open() {
        log.info("Opening Acierto Main page");
        Selenide.open(ACIERTO_URL);
        return this;
    }

    @Step("Choose amount of insurance")
    public AciertoPage insuranceAmountClick(String amount){
        log.info(String.format("Choosing %s as amount of insurance and clicking on it", amount));
        $(By.xpath(String.format(INFO_DETAILS_LOCATOR, amount))).click();
        return this;
    }

    @Step("Choose period of insurance")
    public AciertoPage insurancePeriodClick(String period) {
        log.info(String.format("Choosing %s as period of insurance and clicking on it", period));
        $(By.xpath(String.format(INFO_DETAILS_LOCATOR, period))).click();
        return this;
    }

    @Step("Click continue button")
    public AciertoPage clickContinueButton() {
        log.info("Clicking on continue button");
        try {
            Selenide.executeJavaScript("arguments[0].click();", $((String.format(DATA_LOCATOR, "continue"))));
        } catch (Exception e) {
            log.info(String.format(DATA_LOCATOR, "continue")+ "is not found");
        }
        return this;
    }

    @Step("Click on the element")
    public void clickJS(By locator) {
        log.info(String.format("Clicking on %s button", locator));
        Selenide.executeJavaScript("arguments[0].click();", $(locator));
    }

    @Step("Set date of birth")
    public AciertoPage chooseDateOfBirth(String dateOfBirth) {
        log.info(String.format("Setting date of birth of the person as %s", dateOfBirth));
        $((String.format(DATA_LOCATOR, "birth-date"))).setValue(dateOfBirth);
        return this;
    }

    @Step("Set gender of the person")
    public AciertoPage setPersonsGender(String gender) {
        log.info(String.format("Setting gender of the person as %s", gender));
        $(By.xpath(String.format(INFO_DETAILS_LOCATOR, gender))).click();
        return this;
    }

    @Step("Set zipcode")
    public AciertoPage setZipCode(String zipcode) {
        log.info(String.format("Setting zipcode as %s", zipcode));
        $((String.format(DATA_LOCATOR, "zip-code"))).setValue(zipcode);
        return this;
    }

    @Step("Set email")
    public AciertoPage setEmail(String email) {
        log.info(String.format("Setting email as %s", email));
        $((String.format(DATA_LOCATOR, "email"))).setValue(email);
        return this;
    }

    @Step("Set phone")
    public AciertoPage setPhone(String phone) {
        log.info(String.format("Setting phone as %s", phone));
        $((String.format(DATA_LOCATOR, "phone"))).setValue(phone);
        return this;
    }
}
