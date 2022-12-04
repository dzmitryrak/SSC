package io.github.dzmitryrak.pages.cases;
import io.github.dzmitryrak.pages.BasePage;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

/**
 * @deprecated
 */
@Log4j2
public class CaseListViewPage extends BasePage {
    protected final String CASE_RECORD_LOCATOR = "(//*[contains(@class, 'slds-cell-edit slds-cell-error errorColumn cellContainer')]/parent::tr//th)[%s]";
    protected final By CASES_FILTER = By.xpath("//*[text() ='Cases Opened Today']");

    @Step("Open List View for Case")
    public CaseListViewPage openUrl() {
        log.info("Opening List View for Case");
        open("/lightning/o/Case/list?filterName=00B7Y000004PyxjUAC");
        return this;
    }

    public boolean isPageOpened() {
        log.info("Check that page is opened");
        return $(CASES_FILTER).isDisplayed();
    }

    @Step("Clicking on the case with the index")
    public CaseListViewPage openCase(int index) {
        log.info("Clicking on the case record with {} index", index);
        $(By.xpath(String.format(CASE_RECORD_LOCATOR, index))).click();
        return this;
    }
}