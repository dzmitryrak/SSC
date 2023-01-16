package io.github.dzmitryrak.tests;

import io.github.dzmitryrak.enums.SortOrder;
import io.github.dzmitryrak.tests.base.BaseTest;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.open;
import static org.testng.Assert.assertEquals;

public class CaseTest extends BaseTest {

    //TODO replace by the real test Case CRUD + validation of panels
    @Test(description = "Check that panel could be validated")
    public void panelValidation() {
        loginPage.open().login(USERNAME, PASSWORD);
        open("https://tms41-dev-ed.lightning.force.com/lightning/r/Case/5005g00000UUxpWAAT/view");
        detailsPage
                .panels()
                .panel("Case Details")
                .validate("Subject", "Seeking guidance on electrical wiring installation for GC5060")
                .validate("Case Number", "00001002")
                .validate("Status", "New")
                .validate("Priority", "Low")
                .validate("Description", "");
        detailsPage
                .clickTab("Details")
                .validate("Subject", "Seeking guidance on electrical wiring installation for GC5060")
                .validate("Case Number", "00001002")
                .validate("Status", "New")
                .validate("Priority", "Low")
                .validate("Description", "");
    }

    @Test(description = "Check that values of cells could be interacted")
    public void tableValidation() {
        loginPage.open().login(USERNAME, PASSWORD);
        listView
                .open("Case")
                .table()
                .clickCell("Case Number", 1);
        detailsPage.waitTillOpened();
        String subject =
                listView
                .open("Case")
                .table()
                .sortBy("Case Number", SortOrder.ASC)
                .getTextFromCell("Subject", 1);
        assertEquals(subject, "Starting generator after electrical failure");
    }

    @Test(description = "Check that correct case could be opened from table")
    public void tableSortingValidation() {
        loginPage.open().login(USERNAME, PASSWORD);
        String subject = listView
                .open("Case")
                .table()
                .sortBy("Case Number", SortOrder.DESC)
                .getTextFromCell("Case Number",1);
        listView.table().clickCell("Case Number", 1);
        detailsPage.waitTillOpened();
        detailsPage.panels().panel("Case Details").validate("Case Number",subject);
    }

    @Test(description = "Check that listview sorting exists and works")
    public void sortingListView(){
        loginPage.open().login(USERNAME, PASSWORD);
        listView.open("Case");
        listView.clickSwitcher();
        listView.selectFilter("My Cases");
        listView.table().sortBy("Case Number", SortOrder.DESC);
        listView.table().clickCell("Case Number", 1);
    }
}
