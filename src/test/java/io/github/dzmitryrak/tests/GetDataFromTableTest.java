package io.github.dzmitryrak.tests;

import io.github.dzmitryrak.enums.SortOrder;
import io.github.dzmitryrak.tests.base.BaseTest;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static org.testng.Assert.*;

public class GetDataFromTableTest extends BaseTest {
    @Test(description = "Validate data from list view table by index")
    public void tableDataValidationByIndex() {
        Map<String, String> expectedTableData = new HashMap<>();
        expectedTableData.put("Case Number", "00001016");
        loginPage.open().login(USERNAME, PASSWORD);
        listView
                .open("Case")
                .table()
                .clickCell("Case Number", 1);
        detailsPage.waitTillOpened();
        Map<String, String> actualTableData =
                listView
                        .open("Case")
                        .table()
                        .sortBy("Case Number", SortOrder.ASC)
                        .getRecordData(2);
        assertFalse(actualTableData.isEmpty());
    }

    @Test(description = "Validate data from list view table by column name")
    public void tableDataValidationByColumnName() {
        Map<String, String> expectedTableData = new HashMap<>();
        expectedTableData.put("Case Number", "00001016");
        loginPage.open().login(USERNAME, PASSWORD);
        listView
                .open("Case")
                .table()
                .clickCell("Case Number", 1);
        detailsPage.waitTillOpened();
        Map<String, String> actualTableData =
                listView
                        .open("Case")
                        .table()
                        .sortBy("Case Number", SortOrder.ASC)
                        .getRecordData("Subject", "Maintenance guidelines for generator unclear");
        assertFalse(actualTableData.isEmpty());
    }
}
