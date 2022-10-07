package com.itechart.tests.ui;

import com.itechart.configurations.Retry;
import com.itechart.models.Account;
import com.itechart.pages.account.AccountDetailsPage;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.Test;

public class AccountCRUDTest extends BaseTest {

    private final String username = propertyReader.getPropertyValueByKey("username");
    private final String password = propertyReader.getPropertyValueByKey("password");

    Account account = AccountDetailsPage.createNewAccount();
    Account updatedAccount = AccountDetailsPage.createNewAccount();

    @Test(description = "Create Read Update Delete Account record")
    public void createNewAccountRecord() {

        loginPage.open();
        loginPage.login(username, password);
        homePage.isPageOpened();
        accountListViewPage.open();
        accountListViewPage
                .clickNewButton()
                .enterData(account)
                .clickSaveButton()
                .isPageOpened();
        accountDetailsPage
                .openDetails()
                .validate(account);
    }

    @Test(description = "Edit new account created")
    public void editNewAccountRecord() {

        loginPage.open();
        loginPage.login(username, password);
        homePage.isPageOpened();
        accountListViewPage.open();
        accountListViewPage
                .clickNewButton()
                .enterData(account)
                .clickSaveButton()
                .isPageOpened();
        accountDetailsPage
                .openDetails()
                .validate(account)
                .clickIconDropdownMenu()
                .clickEditDetailsButton()
                .clearData()
                .enterData(updatedAccount)
                .clickSaveButton();
        accountDetailsPage.openDetails();
        accountDetailsPage.validate(updatedAccount);
    }

    @Test(retryAnalyzer = Retry.class, description = "Delete new account created")
    public void deleteNewAccountRecord() {

        loginPage.open();
        loginPage.login(username, password);
        homePage.isPageOpened();
        accountListViewPage.open();
        accountListViewPage
                .clickNewButton()
                .enterData(account)
                .clickSaveButton()
                .isPageOpened();
        accountDetailsPage
                .openDetails()
                .validate(account);
        accountDetailsPage
                .clickIconDropdownMenu()
                .clickDeleteButton()
                .delete()
                .isSuccessDeleteMessageDisplayed();
    }

    public void clickJS(By locator) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(locator));
    }
}