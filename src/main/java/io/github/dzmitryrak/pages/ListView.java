package io.github.dzmitryrak.pages;

import com.codeborne.selenide.Selenide;
import io.github.dzmitryrak.enums.SortOrder;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;

@Log4j2
public class ListView extends BasePage {
    protected final String CASE_RECORD_LOCATOR = "(//*[contains(@class, 'slds-cell-edit slds-cell-error errorColumn cellContainer')]/parent::tr//th)[%s]";
    private final By BREADCRUMB_LOCATOR = By.xpath("//*[contains(@class,'slds-breadcrumb__item')]");
    //TODO make sure that active layer is selected everywhere
    private final By NEW_BUTTON_LOCATOR = By.xpath(ACTIVE_TAB_LOCATOR + "//a[@title='New']");
    private final By FILTER_SWITCHER_BUTTON = By.xpath("//*[contains(@class, 'slds-page-header__name-switcher')]//button");
    private final String SELECT_FILTER_LOCATOR = "(//span[contains(@class, ' virtualAutocompleteOptionText') and text()='%s'])[1]";
    private final String COLUMN_LOCATOR = "//*[@title='%s']//a";
    private final String SORTING_COLUMN_LOCATOR = "//th[@title='%s']";

    /**
     * Wait until breadcrumb is displayed.
     *
     * @return current instance of ListView
     */
    public void waitTillOpened() {
        $(BREADCRUMB_LOCATOR).shouldBe(visible);
    }

    /**
     * Open list view.
     *
     * @param listViewName
     * @return current instance of ListView
     */
    @Step("Opening List View")
    public ListView open(String listViewName) {
        log.info("Opening '{}' List View", listViewName);
        Selenide.open(String.format("lightning/o/%s/list", listViewName));
        waitTillOpened();
        return this;
    }

    @Step("Click on New button")
    public NewObjectModal clickNew() {
        $(NEW_BUTTON_LOCATOR).click();
        NewObjectModal newObjectModal = new NewObjectModal();
        newObjectModal.waitTillOpened();
        return newObjectModal;
    }

    //TODO create wrapper for tableview
    /**
     * Open object.
     *
     * @param index 1-based row index
     */
    @Step("Opening object from the list")
    public void openObjectFromList(int index) {
        log.info("Clicking on the record with the index {}", index);
        $(By.xpath(String.format(CASE_RECORD_LOCATOR, index))).click();
    }

    /**
     * Click filter switcher.
     *
     * @return current instance of ListView
     */
    @Step("Click on filter switcher icon")
    public ListView clickSwitcher() {
        log.info("Click on filter switcher icon");
        $(FILTER_SWITCHER_BUTTON).click();
        return this;
    }

    /**
     * Select filter.
     *
     * @param filterValue
     * @return current instance of ListView
     */
    @Step("Click on the filter value")
    public ListView selectFilter(String filterValue) {
        log.info("Click on the filter value {}", filterValue);
        $(By.xpath(String.format(SELECT_FILTER_LOCATOR, filterValue))).click();
        return this;
    }

    /**
     * Sort table column.
     *
     * @param column column to sort
     * @param ascDesc sorting type
     * @return current instance of ListView
     */
    @Step("Check sorting of the column titled {column} with order {ascDesc}")
    public ListView sortBy(String column, SortOrder ascDesc) {
        log.info("Sorting the column titled {} in order", column, ascDesc);
        $(By.xpath(String.format(SORTING_COLUMN_LOCATOR, column))).shouldHave(attributeMatching("class", ".*ending.*"), Duration.ofSeconds(5));

        String actualSortingValue = $(By.xpath(String.format(SORTING_COLUMN_LOCATOR, column))).getAttribute("class");
        log.debug("Actual sorting value is {}", actualSortingValue);
        if(!actualSortingValue.contains(ascDesc.getText())) {
            $(By.xpath(String.format(COLUMN_LOCATOR, column))).click();
            log.info("Click on the column titled {} to sort it", column);
        }
        return this;
    }
}
