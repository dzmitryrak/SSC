package io.github.dzmitryrak.tests;

import io.github.dzmitryrak.enums.SortOrder;
import io.github.dzmitryrak.tests.base.BaseTest;
import org.testng.annotations.Test;

import java.util.Map;

import static com.codeborne.selenide.Selenide.open;

public class ListViewTest extends BaseTest {
    @Test(description = "Check that listview sorting exists and works")
    public void sortingListView(){
        loginPage.open().login(USERNAME, PASSWORD);
        listView.open("Case");
        listView.clickSwitcher();
        listView.selectFilter("My Cases");
        listView.sortBy("Case Number", SortOrder.DESC);
        listView.openObjectFromList(1);
    }

    @Test(description = "Check that all opened tabs are closed")
    public void closeOpenedTabs(){
        loginPage.open().login(USERNAME, PASSWORD);
        listView.open("Case");
        listView.clickSwitcher();
        listView.selectFilter("My Cases");
        listView.openObjectFromList(1);
        listView.xButtonClick();
    }
}
