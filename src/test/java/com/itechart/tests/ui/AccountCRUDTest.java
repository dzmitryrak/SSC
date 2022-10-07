package com.itechart.tests.ui;

import com.itechart.models.Account;
import com.itechart.configurations.Retry;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

public class AccountCRUDTest extends BaseTest {

    @Test(description = "Create Read Update Delete Account record")
    public void createNewAccountRecord() {
        Map<String, String> account = new HashMap<>() {{
            put("Account Name", "Margart Rice MD");
            put("Type", "Prospect");
            put("Website", "www.herman-blick.co");
            put("Phone", "108-510-6061 x66310");
            put("Description", "Ut quis odio non temporibus corrupti laboriosam.");
            put("Employees", "9");
            put("Billing Street", "2599 Donella Overpass");
            put("Billing City", "Trantowmouth");
            put("Billing State/Province", "Hawaii");
            put("Billing Zip/Postal Code", "86600");
            put("Billing Country", "Niger");
            put("Shipping Street", "526 Rogahn Row");
            put("Shipping City", "Port Alvinbury");
            put("Shipping State/Province", "Minnesota");
            put("Shipping Zip/Postal Code", "28642");
            put("Shipping Country", "New Zealand");
        }};

        loginSteps.login(USERNAME, PASSWORD);
        accountSteps
                .openAccountListViewPage()
                .create(account);
        //.validate(account);
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