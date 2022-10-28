package com.itechart.tests;

import com.github.javafaker.Faker;
import com.itechart.tests.base.BaseTest;
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

        loginPage.open();
        loginPage.login(USERNAME, PASSWORD);
        homePage.isPageOpened();
        accountListViewPage.openUrl();
        accountListViewPage
                .clickNewButton();

        newObjectModal.enterData(account)
                .save();
        accountDetailsPage.isPageOpened();
        account.remove("Billing Street");
        account.remove("Billing City");
        account.remove("Billing State/Province");
        account.remove("Billing Zip/Postal Code");
        account.remove("Billing Country");
        account.remove("Shipping Street");
        account.remove("Shipping City");
        account.remove("Shipping State/Province");
        account.remove("Shipping State/Province");
        account.remove("Shipping Zip/Postal Code");
        account.remove("Shipping Country");

        accountDetailsPage
                .openDetails()
                .validate(account);
    }

    @Test(description = "Edit new account created")
    public void editNewAccountRecord() {
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

        Map<String, String> updatedAccount = new HashMap<>() {{
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

        loginPage.open();
        loginPage.login(USERNAME, PASSWORD);
        homePage.isPageOpened();
        accountListViewPage.openUrl();
        accountListViewPage
                .clickNewButton();
        newObjectModal
                .enterData(account)
                .save();
        accountDetailsPage.isPageOpened();
        account.remove("Billing Street");
        account.remove("Billing City");
        account.remove("Billing State/Province");
        account.remove("Billing Zip/Postal Code");
        account.remove("Billing Country");
        account.remove("Shipping Street");
        account.remove("Shipping City");
        account.remove("Shipping State/Province");
        account.remove("Shipping State/Province");
        account.remove("Shipping Zip/Postal Code");
        account.remove("Shipping Country");
        accountDetailsPage
                .openDetails()
                .validate(account);
        accountDetailsPage
                .clickIconDropdownMenu()
                .clickEditDetailsButton();
        newObjectModal
                .clearData(account)
                .enterData(updatedAccount)
                .save();
        accountDetailsPage.isPageOpened();

        updatedAccount.remove("Description");
        updatedAccount.remove("Billing Street");
        updatedAccount.remove("Billing City");
        updatedAccount.remove("Billing State/Province");
        updatedAccount.remove("Billing Zip/Postal Code");
        updatedAccount.remove("Billing Country");
        updatedAccount.remove("Shipping Street");
        updatedAccount.remove("Shipping City");
        updatedAccount.remove("Shipping State/Province");
        updatedAccount.remove("Shipping State/Province");
        updatedAccount.remove("Shipping Zip/Postal Code");
        updatedAccount.remove("Shipping Country");

        accountDetailsPage
                .openDetails()
                .validate(updatedAccount);
    }

    @Test(description = "Delete new account created")
    public void deleteNewAccountRecord() {
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

        loginPage.open();
        loginPage.login(USERNAME, PASSWORD);
        homePage.isPageOpened();
        accountListViewPage.openUrl();
        accountListViewPage
                .clickNewButton();
        newObjectModal
                .enterData(account)
                .save();
        accountDetailsPage.isPageOpened();
        account.remove("Billing Street");
        account.remove("Billing City");
        account.remove("Billing State/Province");
        account.remove("Billing Zip/Postal Code");
        account.remove("Billing Country");
        account.remove("Shipping Street");
        account.remove("Shipping City");
        account.remove("Shipping State/Province");
        account.remove("Shipping State/Province");
        account.remove("Shipping Zip/Postal Code");
        account.remove("Shipping Country");
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