package com.itechart.pages.cases;

import com.itechart.pages.BasePage;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

@Log4j2
public class CaseListViewPage extends BasePage {
    protected final By FIRST_CASE_RECORD = By.xpath("//*[contains(@class, 'slds-cell-edit cellContainer')]");
    protected final By CASES_FILER = By.xpath("//*[text() ='Created Today CTI']");


    @Step("Open List View for Case")
    public CaseListViewPage openUrl() {
        log.info("Opening List View for Case");
        open("/lightning/o/Case/list?filterName=00B7Y000004PiHfUAK");
        return this;
    }

    public boolean isPageOpened() {
        log.info("Check that page is opened");
        return $(CASES_FILER).isDisplayed();
    }

    @Step("Clicking on the first case")
    public CaseListViewPage clickOnCase() {
        log.info("Clicking on the first case");
        $(FIRST_CASE_RECORD).click();
        return this;
    }
}
