package io.github.dzmitryrak.tests;

import io.github.dzmitryrak.enums.SortOrder;
import io.github.dzmitryrak.tests.base.BaseTest;
import org.testng.annotations.Test;
import java.util.Map;

import static org.testng.Assert.*;

public class GetDataFromTableTest extends BaseTest {
    @Test(description = "Validate data from list view table by index")
    public void tableDataValidationByIndex() {
        loginPage.open().login(USERNAME, PASSWORD);
        Map<String, String> actualTableData =
                listView
                        .open("Case")
                        .table()
                        .sortBy("Case Number", SortOrder.ASC)
                        .getRecordData(3);
        assertFalse(actualTableData.isEmpty());
    }

    @Test(description = "Validate data from list view table by column name")
    public void tableDataValidationByColumnName() {
        loginPage.open().login(USERNAME, PASSWORD);
        Map<String, String> actualTableData =
                listView
                        .open("Case")
                        .table()
                        .sortBy("Case Number", SortOrder.ASC)
                        .getRecordData("Subject", "DRak");
        assertFalse(actualTableData.isEmpty());
    }
}
