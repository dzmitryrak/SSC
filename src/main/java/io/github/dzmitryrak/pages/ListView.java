package io.github.dzmitryrak.pages;

import com.codeborne.selenide.Selenide;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;

/**
 * Class representing available lists view.
 */
@Log4j2
public class ListView extends BasePage {
    protected final String CASE_RECORD_LOCATOR = "(//*[contains(@class, 'slds-cell-edit slds-cell-error errorColumn cellContainer')]/parent::tr//th)[%s]";
    private final By BREADCRUMB_LOCATOR = By.xpath("//*[contains(@class,'slds-breadcrumb__item')]");
    private final By FILTER_SWITCHER_BUTTON = By.xpath("//*[contains(@class, 'slds-page-header__name-switcher')]//button");
    private final String SELECT_FILTER_LOCATOR = "(//span[contains(@class, ' virtualAutocompleteOptionText') and text()='%s'])[1]";
    private final String COLUMN_LOCATOR = "//*[@title='%s']//a";
    private final String SORTING_COLUMN_LOCATOR = "//th[@title='%s']";

    public void waitTillOpened() {
        $(BREADCRUMB_LOCATOR).shouldBe(visible, Duration.ofSeconds(20));
    }

    @Step("Opening List View")
    public ListView open(String listViewName) {
        log.info("Opening '{}' List View", listViewName);
        Selenide.open(String.format("lightning/o/%s/list", listViewName));
        waitTillOpened();
        return this;
    }

    //TODO create wrapper for tableview
    @Step("Opening object from the list")
    public void openObjectFromList(int index) {
        log.info("Clicking on the record with the index {}", index);
        $(By.xpath(String.format(CASE_RECORD_LOCATOR, index))).click();
    }

    @Step("Click on filter switcher icon")
    public ListView clickSwitcher() {
        log.info("Click on filter switcher icon");
        $(FILTER_SWITCHER_BUTTON).click();
        return this;
    }

    @Step("Click on the filter value")
    public ListView selectFilter(String filterValue) {
        log.info("Click on the filter value {}", filterValue);
        $(By.xpath(String.format(SELECT_FILTER_LOCATOR, filterValue))).click();
        return this;
    }

    @Step("Check sorting of the column titled {column} with order {ascDesc}")
    public ListView sortBy(String column, String ascDesc) {
        log.info("Sorting the column titled {} in order", column, ascDesc);
        $(By.xpath(String.format(SORTING_COLUMN_LOCATOR, column))).shouldHave(attributeMatching("class", ".*ending.*"), Duration.ofSeconds(5));

        String actualSortingValue = $(By.xpath(String.format(SORTING_COLUMN_LOCATOR, column))).getAttribute("class");
        log.debug("Actual sorting value is {}", actualSortingValue);
        if(!actualSortingValue.contains(ascDesc)) {
            $(By.xpath(String.format(COLUMN_LOCATOR, column))).click();
            log.info("Click on the column titled {} to sort it", column);
        }
        return this;
    }

    public ListAction actions() {
        return new ListAction();
    }
}
