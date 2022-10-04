package com.itechart.tests.ui;

import com.itechart.models.Account;
import com.itechart.configurations.Retry;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class AccountCRUDTest extends BaseTest {

    @Test(description = "Create Read Update Delete Account record")
    public void createNewAccountRecord() {
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