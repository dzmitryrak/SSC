package io.github.dzmitryrak.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.github.dzmitryrak.enums.SortOrder;
import io.github.dzmitryrak.utils.SalesforceElementNotFoundException;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;

import java.time.Duration;
import java.util.Map;

import static com.codeborne.selenide.Condition.attributeMatching;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

@Log4j2
public class Table extends BasePage {
    private final String COLUMN_LOCATOR = "//*[@title='%s']//a";
    private final String SORTING_COLUMN_LOCATOR = "//th[@title='%s']";
    private ElementsCollection headers;
    private final By HEADER_LOCATOR = By.xpath(ACTIVE_TAB_LOCATOR + "//thead//th");

    public Table() {
        waitTillOpened();
        waitForPageLoaded();
        headers = $$(HEADER_LOCATOR);
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < headers.size(); i++) {
            builder.append(headers.get(0).attr("title"));
            builder.append("|");
        }
        log.debug("Loaded table with headers: {}", builder.toString());
    }

    private void waitTillOpened() {
        $(HEADER_LOCATOR).shouldBe(visible, Duration.ofSeconds(10));
    }

    public String getTextFromCell(String columnName, int rowIndex) {
        return findCell(columnName, rowIndex).text();
    }

    //TODO
    public Map<String, String> getRecordData(int index) {
        return null;
    }

    //TODO
    public Map<String, String> getRecordData(String columnName, String columnValue) {
        return null;
    }

    public Table clickCell(String columnName, int rowIndex) {
        findCell(columnName, rowIndex).find("a").click();
        return this;
    }

    private SelenideElement findCell(String columnName, int rowIndex) {
        log.debug("Looking for column by title '{}' inside row number '{}'", columnName, rowIndex);
        int columnIndex = getColumnIndex(columnName);
        return findCell(columnIndex, rowIndex);
    }

    private SelenideElement findCell(int columnIndex, int rowIndex) {
        ElementsCollection allCells = $$x(String.format(
                ACTIVE_TAB_LOCATOR + "//tbody//tr[%s]//td|" +
                ACTIVE_TAB_LOCATOR + "//tbody//tr[%s]//th", rowIndex, rowIndex));
        return allCells.get(columnIndex - 1);
    }

    private int getColumnIndex(String columnName) {
        if(headers.size() == 0) {
            throw new SalesforceElementNotFoundException(
                    String.format("Cannot find column '%s'. List View was not loaded or column doesn't exist", columnName)
            );
        }
        int columnIndex = 0;
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < headers.size(); i++) {
            //TODO what if null
            //TODO what to pass for action/number and checkbox
            String columnTitle = headers.get(i).attr("title");
            builder.append(columnTitle).append(";");
            if (columnTitle.equals(columnName)) {
                columnIndex = i+1; //Used +1 here because XPATH index starts from 1 instead of 0
                break;
            }
        }
        log.debug("Identified that column '{}' has index '{}'. Checked columns: {}", columnName, columnIndex, builder.toString());

        return columnIndex;
    }

    /**
     * Sort table column.
     *
     * @param column  column to sort
     * @param ascDesc sorting type
     * @return current instance of ListView
     */
    @Step("Check sorting of the column titled {column} with order {ascDesc}")
    public Table sortBy(String column, SortOrder ascDesc) {
        log.info("Sorting the column titled {} in order", column, ascDesc);
        try {
            $(By.xpath(String.format(SORTING_COLUMN_LOCATOR, column))).shouldHave(attributeMatching("class", ".*ending.*"), Duration.ofSeconds(5)).exists();
        } catch (Throwable exception) {
            $(By.xpath(String.format(COLUMN_LOCATOR, column))).click();
            waitForPageLoaded();
        }
        String actualSortingValue = $(By.xpath(String.format(SORTING_COLUMN_LOCATOR, column))).getAttribute("class");
        log.debug("Actual sorting value is {}", actualSortingValue);
        if (!actualSortingValue.contains(ascDesc.getText())) {
            $(By.xpath(String.format(COLUMN_LOCATOR, column))).click();
            waitForPageLoaded();
            log.info("Click on the column titled {} to sort it", column);
        }
        return this;
    }
}
