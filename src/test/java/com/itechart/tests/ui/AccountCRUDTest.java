package com.itechart.tests.ui;

import com.itechart.models.Account;
import com.itechart.configurations.Retry;
import com.itechart.pages.account.AccountDetailsPage;
import groovy.util.slurpersupport.Attribute;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AccountCRUDTest extends BaseTest {

    private final String username = propertyReader.getPropertyValueByKey("username");
    private final String password = propertyReader.getPropertyValueByKey("password");

    Account account = AccountDetailsPage.createNewAccount();
    Account updatedAccount = AccountDetailsPage.createNewAccount();
    // Создал метод создания нового аккаунта и updated аккаунта в AccountDetailPage, который создает в тестах новый акк, и он передается в тесты

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
//        boolean isAccountCreated =
//                accountListViewPage
//                        .clickNewButton()
//                        .enterData(account)
//                        .clickSaveButton()
//                        .isPageOpened();
//        Assert.assertTrue(isAccountCreated, "Account is not created");
        accountDetailsPage
                .openDetails()
                .validate(account);
    }

    @Test(description = "Create Read Update Delete Account record")
        public void create1NewAccountRecord() {
            Account account = accountFactory.createNewAccount(true);
            Account updatedAccount = accountFactory.createNewAccount(true);
            loginSteps.login(USERNAME, PASSWORD);
            accountSteps
                    .openAccountListViewPage()
                    .create(account)
                    .validate(account);
        }

    //TODO fix
/*                .edit(updatedAccount)
                .validate(updatedAccount) */

    @Test(retryAnalyzer = Retry.class, description = "Edit new account created")
    public void editNewAccountRecord() {
        Account account = accountFactory.createNewAccount(true);
        Account updatedAccount = accountFactory.createNewAccount(true);
        loginSteps.login(USERNAME, PASSWORD);
        accountSteps
                .openAccountListViewPage()
                .create(account)
                .validate(account)
                .edit(account)
                .validate(account);
    }

    @Test(retryAnalyzer = Retry.class, description = "Delete new account created")
    public void deleteNewAccountRecord() {
        Account account = accountFactory.createNewAccount(true);
        Account updatedAccount = accountFactory.createNewAccount(true);
        loginSteps.login(USERNAME, PASSWORD);
        accountSteps
                .openAccountListViewPage()
                .create(account)
                .validate(account)
                .delete();
    }

    public void clickJS(By locator) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(locator));
    }
}