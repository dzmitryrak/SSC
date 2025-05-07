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
        String caseNumber = listView
                .open("Case")
                .table()
                .sortBy("Case Number", SortOrder.ASC)
                .getTextFromCell("Case Number", 1);
        String subject = listView
                .open("Case")
                .table()
                .sortBy("Case Number", SortOrder.ASC)
                .getTextFromCell("Subject", 1);
        Map<String, String> actualTableData =
                listView
                        .open("Case")
                        .table()
                        .sortBy("Case Number", SortOrder.ASC)
                        .getRecordData("Case Number", caseNumber);
        Map<String, String> expectedTableData = listView
                .open("Case")
                .table()
                .sortBy("Case Number", SortOrder.ASC)
                .getRecordData("Subject", subject);
        assertEquals(actualTableData, expectedTableData);
    }
}
