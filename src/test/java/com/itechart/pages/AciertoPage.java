package com.itechart.pages;

import com.codeborne.selenide.Selenide;
import io.github.dzmitryrak.pages.BasePage;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.closeWebDriver;

@Log4j2
public class AciertoPage extends BasePage {

    private final String ACIERTO_URL = "https://stg-funnel-life.acierto.com/seguros-vida/comparador/";
    private static final String INFO_DETAILS_LOCATOR = "//*[text()='%s']";
    private static final String DATA_LOCATOR = "[data-gtm=%s]";
    private static final String IM_INTERESTED_BUTTON = "(//button//span[text()='Me interesa'])[%s]";
    private static final By LIFE_INSURANCE_LABEL = By.xpath("//*[text() ='Seguro de vida']");
    private static final By FINAL_MODAL_LOCATOR = By.xpath("//*[contains(@class, 'funnel-call-to-me-modal__user-number')]");
    private static final By CALL_ME_ON_THIS_PHONE_BUTTON = By.xpath("(//button[contains(@data-gtm, 'call-me')])[2]");
    private static final By THANKS_YOU_MODAL = By.xpath("//*[contains(@class, 'message-modal__text-title')]");
    private static final By CLOSE_BUTTON = By.xpath("//button//span[text()='Cerrar']");

    @Step("Open Acierto Main Page")
    public AciertoPage open() {
        log.info("Opening Acierto Main page");
        Selenide.open(ACIERTO_URL);
        return this;
    }

    @Step("Choose insurance details")
    public AciertoPage insuranceDetailsClick(String locator){
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
    public void isLifeInsurancePageOpened() {
        log.info("The page with options for insurance services is opened");
        $(LIFE_INSURANCE_LABEL).shouldBe(visible, Duration.ofSeconds(30));
        closeWebDriver();
    }

    @Step("Click on the button [I'm Interested]")
    public AciertoPage imInterestedButtonClick(int index) {
        log.info("Click on I'm interested button with {} index", index);
        $(By.xpath(String.format(IM_INTERESTED_BUTTON, index))).shouldBe(visible, Duration.ofSeconds(25)).click();
        return this;
    }

    @Step
    public boolean isFinalModalDisplayed() {
        return $(FINAL_MODAL_LOCATOR).isDisplayed();
    }

    @Step
    public AciertoPage callMeOnThisPhoneButtonClick() {
        log.info("Click on [Call Me On This Phone] button");
        $(CALL_ME_ON_THIS_PHONE_BUTTON).click();
        return this;
    }

    @Step("Check that grateful Modal is displayed")
    public boolean isGratitudeModalDisplayed() {
        log.info("Gratitude Modal is displayed");
        return $(THANKS_YOU_MODAL).isDisplayed();
    }

    @Step
    public AciertoPage closeButtonClick() {
        log.info("Click on [Close] button on Grateful modal");
        $(CLOSE_BUTTON).click();
        return this;
    }

    @Step("Setting person's data for creation the record")
    public AciertoPage setPersonRecord(String amount, String period, String dateOfBirth,String gender,
                                       String zipcode, String email, String phone) {
        log.info("Creation of an insurance record");
        open();
        insuranceDetailsClick(amount);
        insuranceDetailsClick(period);
        clickContinueButton();
        setPersonData("birth-date",dateOfBirth);
        insuranceDetailsClick(gender);
        setPersonData("zip-code", zipcode);
        clickContinueButton();
        setPersonData("email", email);
        setPersonData("phone", phone);
        clickContinueButton();
        isLifeInsurancePageOpened();
        return this;
    }
}
