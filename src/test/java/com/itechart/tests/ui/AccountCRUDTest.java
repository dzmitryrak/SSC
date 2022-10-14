package com.itechart.tests.ui;

import com.github.javafaker.Faker;
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

    Faker faker = new Faker();

    @Test(description = "Create Read Update Delete Account record")
    public void createNewAccountRecord() {
        Map<String, String> account = new HashMap<>() {{
            put("Account Name", faker.name().name());
            put("Type", "Prospect");
            put("Website", faker.internet().url());
            put("Phone", faker.phoneNumber().phoneNumber());
            put("Description", faker.lorem().sentence());
            put("Employees", faker.number().digit());
            put("Billing Street", faker.address().streetAddress());
            put("Billing City", faker.address().city());
            put("Billing State/Province", faker.address().state());
            put("Billing Zip/Postal Code", faker.address().zipCode());
            put("Billing Country", faker.address().country());
            put("Shipping Street", faker.address().streetAddress());
            put("Shipping City", faker.address().city());
            put("Shipping State/Province", faker.address().state());
            put("Shipping Zip/Postal Code", faker.address().zipCode());
            put("Shipping Country", faker.address().country());
        }};

        loginSteps.login(USERNAME, PASSWORD);
        accountSteps
                .openAccountListViewPage()
                .create(account)
                .validate(account);
    }

    @Test(description = "Edit new account created")
    public void editNewAccountRecord() {

        Account account = new Account(
                faker.name().name(),
                "Prospect",
                faker.internet().url(),
                "Apparel",
                faker.phoneNumber().phoneNumber(),
                faker.lorem().sentence(),
                faker.number().digit(),
                faker.address().streetAddress(),
                faker.address().city(),
                faker.address().zipCode(),
                faker.address().state(),
                faker.address().country(),
                faker.address().streetAddress(),
                faker.address().city(),
                faker.address().zipCode(),
                faker.address().state(),
                faker.address().country(),
                "Dmitry Rak");

        Account updatedAccount = new Account(
                faker.name().name(),
                "Prospect",
                faker.internet().url(),
                "Apparel",
                faker.phoneNumber().phoneNumber(),
                faker.lorem().sentence(),
                faker.number().digit(),
                faker.address().streetAddress(),
                faker.address().city(),
                faker.address().zipCode(),
                faker.address().state(),
                faker.address().country(),
                faker.address().streetAddress(),
                faker.address().city(),
                faker.address().zipCode(),
                faker.address().state(),
                faker.address().country(),
                "Dmitry Rak");

        loginPage.open();
        loginPage.login(USERNAME, PASSWORD);
        homePage.isPageOpened();
        accountListViewPage.openUrl();
        accountListViewPage
                .clickNewButton()
                .enterData(account)
                .clickSaveButton()
                .isPageOpened();
        accountDetailsPage
                .openDetails()
                .validate(account)
                .edit(account)
                .validate(account);
    }

    @Test(description = "Delete new account created")
    public void deleteNewAccountRecord() {

        Account account = new Account(
                faker.name().name(),
                "Prospect",
                faker.internet().url(),
                "Apparel",
                faker.phoneNumber().phoneNumber(),
                faker.lorem().sentence(),
                faker.number().digit(),
                faker.address().streetAddress(),
                faker.address().city(),
                faker.address().zipCode(),
                faker.address().state(),
                faker.address().country(),
                faker.address().streetAddress(),
                faker.address().city(),
                faker.address().zipCode(),
                faker.address().state(),
                faker.address().country(),
                "Dmitry Rak");

        loginPage.open();
        loginPage.login(USERNAME, PASSWORD);
        homePage.isPageOpened();
        accountListViewPage.openUrl();
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
}