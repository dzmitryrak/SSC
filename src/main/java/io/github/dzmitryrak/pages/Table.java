package io.github.dzmitryrak.pages;

import com.codeborne.selenide.ClickOptions;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.github.dzmitryrak.enums.SortOrder;
import io.github.dzmitryrak.utils.SalesforceElementNotFoundException;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;

import java.time.Duration;
import java.util.LinkedHashMap;
import java.util.Map;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

@Log4j2
public class Table extends BasePage {
    private final String COLUMN_LOCATOR = "//*[@title='%s']//a";
    private final String ALL_ROWS_LOCATOR = ACTIVE_TAB_LOCATOR + "//tbody//tr";
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

    /**
     * Get text from cell by table name, column name and row index
     *
     * @param tableName cell column name
     * @param columnName cell column name
     * @param rowIndex  cell row index
     * @return cell value
     */
    public String getTextFromCell(String tableName, String columnName, int rowIndex) {
        return findCell(tableName, columnName, rowIndex).text();
    }

    /**
     * Get text from cell by column name and row index
     *
     * @param columnName cell column name
     * @param rowIndex  cell row index
     * @return cell value
     */
    public String getTextFromCell(String columnName, int rowIndex) {
        return findCell("", columnName, rowIndex).text();
    }

    /**
     * Get record data from table in list view found by index
     *
     * @param index cell row index in table
     * @return the mapped data with entities: column name - cell value
     */
    public Map<String, String> getRecordData(int index) {
        log.info("Looking for table data by index '{}'", index);
        Map<String, String> tableData = new LinkedHashMap<>();
        for (SelenideElement header : headers) {
            String columnTitle = header.attr("title");
            String cellValue = getTextFromCell("", columnTitle, index);
            if (columnTitle.isBlank() || cellValue.isBlank()) continue;
            tableData.put(columnTitle, cellValue);
        }
        log.info("Returning table data by index: '{}'", tableData);
        return tableData;
    }

    /**
     * Get record data from table in list view found by column name and cell value
     *
     * @param columnName cell column name
     * @param cellValue  cell value
     * @return the mapped data with entities: column name - cell value
     */
    public Map<String, String> getRecordData(String columnName, String cellValue) {
        log.info("Looking for table data with column name: '{}' and cell value: '{}'", columnName, cellValue);
        int rowIndex = getRowIndex(columnName, cellValue);
        return getRecordData(rowIndex);
    }

    /**
     * Click on cell found by column name and row index
     *
     * @param columnName cell column
     * @param rowIndex  cell row index
     * @return current instance of ListView
     */
    public Table clickCell(String columnName, int rowIndex) {
        findCell("", columnName, rowIndex).find("a").click();
        return this;
    }

    /**
     * Click on cell found by table name, column name and row index
     *
     * @param tableName table name
     * @param columnName cell column
     * @param rowIndex  cell row index
     * @return current instance of ListView
     */
    public Table clickCell(String tableName, String columnName, int rowIndex) {
        findCell(tableName,columnName,rowIndex).$x(".//a").click(ClickOptions.usingJavaScript());
        return this;
    }

    /**
     * Select row found by table name and row index
     *
     * @param tableName table name
     * @param rowIndex  cell row index
     * @return current instance of ListView
     */
    public Table selectCell(String tableName, int rowIndex) {
        findCell(tableName,1,rowIndex).$x(".//input").click(ClickOptions.usingJavaScript());
        return this;
    }
    /**
     * Find cell by table name, column name and row index
     *
     * @param tableName cell column name
     * @param columnName cell column name
     * @param rowIndex  cell row index
     * @return cell instance (dom element)
     */
    private SelenideElement findCell(String tableName, String columnName, int rowIndex) {
        log.debug("Looking for column by title '{}' inside row number '{}'", columnName, rowIndex);
        int columnIndex = getColumnIndex(columnName);
        return findCell(tableName, columnIndex, rowIndex);
    }

    /**
     * Find cell by table name, column index and row index
     *
     * @param tableName cell table name
     * @param columnIndex cell column index
     * @param rowIndex  cell row index
     * @return cell instance (dom element)
     */
    private SelenideElement findCell(String tableName, int columnIndex, int rowIndex) {
        String nameTableLocator = "";
        if (StringUtils.isNotEmpty(tableName)) {
            nameTableLocator = String.format("//span[text()='%s']/ancestor::lightning-card", tableName);
        }
        ElementsCollection allCells = $$x(String.format(
                ACTIVE_TAB_LOCATOR + nameTableLocator + "//tbody//tr[%s]//td|" +
                        ACTIVE_TAB_LOCATOR + nameTableLocator + "//tbody//tr[%s]//th", rowIndex, rowIndex));
        return allCells.get(columnIndex - 1);
    }

    /**
     * Get column index by column name
     *
     * @param columnName column name to find index
     * @return column index
     */
    private int getColumnIndex(String columnName) {
        if (headers.size() == 0) {
            throw new SalesforceElementNotFoundException(
                    String.format("Cannot find column '%s'. List View was not loaded or column doesn't exist", columnName)
            );
        }
        int columnIndex = 0;
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < headers.size(); i++) {
            //TODO what to pass for action/number and checkbox
            if (headers.get(i).getAttribute("aria-label") != null) {
                String columnTitle = headers.get(i).getAttribute("aria-label");
                builder.append(columnTitle).append(";");
                if (columnTitle.equals(columnName)) {
                    columnIndex = i + 1; //Used +1 here because XPATH index starts from 1 instead of 0
                    break;
                }
            }
        }
        log.debug("Identified that column '{}' has index '{}'. Checked columns: {}", columnName, columnIndex, builder.toString());

        return columnIndex;
    }

    /**
     * Get row index of cell find by column name and cell value
     *
     * @param columnValue cell column name
     * @param cellValue cell value
     * @return row index of cell
     */
    private int getRowIndex(String columnValue, String cellValue) {
        int rowIndex = 0;
        int columnIndex = getColumnIndex(columnValue);
        SelenideElement cellRow = null;
        ElementsCollection allRows = $$(By.xpath(ALL_ROWS_LOCATOR));

        //find row by cell value and cell column(index)
        ElementsCollection allCellsWithCellValue = $$(By.xpath(String.format(ACTIVE_TAB_LOCATOR +
                "//*[contains(text(), '%s')]//ancestor::td", cellValue)));
        for (SelenideElement cellWithCellValue : allCellsWithCellValue) {
            SelenideElement row = cellWithCellValue.$x(".//ancestor::tr");
            ElementsCollection cells = row.$$x(".//td");
            if (cells.get(columnIndex - 2).text().contains(cellValue)) cellRow = row;
        }

        //find index of row
        for (int i = 0; i < allRows.size(); i++) {
            if (allRows.get(i).equals(cellRow)) {
                rowIndex = i + 1;  //Used +1 here because XPATH index starts from 1 instead of 0
            }
        }
        log.debug("Identified that cell with text '{}' has row index '{}'.", cellValue, rowIndex);
        return rowIndex;
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
            $(By.xpath(String.format(SORTING_COLUMN_LOCATOR, column))).shouldHave(attributeMatching("style", ".*width.*"));
        }
        String actualSortingValue = $(By.xpath(String.format(SORTING_COLUMN_LOCATOR, column))).getAttribute("class");
        log.debug("Actual sorting value is {}", actualSortingValue);
        if (!actualSortingValue.contains(ascDesc.getText())) {
            $(By.xpath(String.format(COLUMN_LOCATOR, column))).click();
            $(By.xpath(String.format(SORTING_COLUMN_LOCATOR, column))).shouldHave(attributeMatching("style", ".*width.*"));
            log.info("Click on the column titled {} to sort it", column);
        }
        return this;
    }
}
