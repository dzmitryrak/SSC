package io.github.dzmitryrak.pages;

import com.codeborne.selenide.Selenide;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;

@Log4j2
public class ListView extends BasePage {
    private final By BREADCRUMB_LOCATOR = By.xpath("//*[contains(@class,'slds-breadcrumb__item')]");
    private final By FILTER_SWITCHER_BUTTON = By.xpath("//*[contains(@class, 'slds-page-header__name-switcher')]//button");
    private final String SELECT_FILTER_LOCATOR = "(//span[contains(@class, ' virtualAutocompleteOptionText') and text()='%s'])[1]";

    /**
     * Wait until breadcrumb is displayed.
     */
    public void waitTillOpened() {
        $(BREADCRUMB_LOCATOR).shouldBe(visible, timeout);
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

    public Table table() {
        return new Table();
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

    public ListAction actions() {
        return new ListAction();
    }
}
