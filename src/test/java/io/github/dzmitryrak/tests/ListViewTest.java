package io.github.dzmitryrak.tests;

import io.github.dzmitryrak.enums.SortOrder;
import io.github.dzmitryrak.tests.base.BaseTest;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.open;

public class ListViewTest extends BaseTest {
    @Test(description = "Check that listview sorting exists and works")
    public void sortingListView(){
        loginPage.open().login(USERNAME, PASSWORD);
        open("https://tms41-dev-ed.lightning.force.com/lightning/o/Case/list?filterName=Recent");
        listView.sortBy("Case Number", SortOrder.ASC);
        listView.clickSwitcher();
        listView.selectFilter("My Cases");
        listView.sortBy("Case Number", SortOrder.DESC);
        listView.openObjectFromList(1);
    }
}
