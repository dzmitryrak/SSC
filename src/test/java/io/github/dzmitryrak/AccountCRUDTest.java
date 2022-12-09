package io.github.dzmitryrak;

import com.github.javafaker.Faker;
import io.github.dzmitryrak.constants.DetailsTabs;
import io.github.dzmitryrak.testbase.BaseTest;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

public class AccountCRUDTest extends BaseTest {

    Faker faker = new Faker();

    @Test(description = "Create Account")
    public void createAccount() {
        Map<String, String> account = new HashMap<>() {{
            put("Account Name", faker.name().name());
            put("Parent Account", "Erica Larson");
            put("Type", "Prospect");
            put("Website", faker.internet().url());
            put("Phone", faker.phoneNumber().phoneNumber());
            put("Description", faker.lorem().sentence());
            put("Multiselect", "No;Probably;one more option");
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
        listView
                .open("Account")
                .clickNew()
                .enterData(account)
                .save()
                .waitTillModalClosed()
                .waitTillOpened();
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

        detailsPage
                .clickTab(DetailsTabs.Details)
                .validate(account);
    }

    @Test(description = "Edit Account")
    public void editAccount() {
        Map<String, String> account = new HashMap<>() {{
            put("Account Name", faker.name().name());
            put("Type", "Prospect");
            put("Website", faker.internet().url());
            put("Phone", faker.phoneNumber().phoneNumber());
            put("Description", faker.lorem().sentence());
            put("Employees", faker.number().digit());
            put("Multiselect", "No;Probably;one more option");
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
            put("Multiselect", "Yes;Probably;one more option");
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
        listView
                .open("Account")
                .clickNew()
                .enterData(account)
                .save()
                .waitTillModalClosed()
                .waitTillOpened();
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
        detailsPage
                .clickTab(DetailsTabs.Details)
                .validate(account);
        detailsPage
                .clickIconDropdownMenu()
                .editObject();
        newObjectModal
                .clearData(updatedAccount)
                .enterData(updatedAccount)
                .save()
                .waitTillModalClosed()
                .waitTillOpened();;

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

        detailsPage
                .clickTab(DetailsTabs.Details)
                .validate(updatedAccount);
    }

    @Test(description = "Delete Account")
    public void deleteAccount() {
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
        listView
                .open("Account")
                .clickNew()
                .enterData(account)
                .save()
                .waitTillModalClosed()
                .waitTillOpened();
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
        detailsPage
                .clickTab(DetailsTabs.Details)
                .validate(account);
        detailsPage
                .clickIconDropdownMenu()
                .clickDeleteButton()
                .delete()
                .isSuccessDeleteMessageDisplayed();
    }

    @Test(description = "Create account with several fields specified")
    public void createAccountSomeFields() {

        String accountName = faker.name().name();
        Map<String, String> relatedAccount = new HashMap<>() {{
            put("Account Name", faker.name().name());
            put("Type", "Prospect");
            put("Website", faker.internet().url());
            put("Phone", faker.phoneNumber().phoneNumber());
        }};

        loginPage.open();
        loginPage.login(USERNAME, PASSWORD);
        homePage.isPageOpened();
        listView
                .open("Account")
                .clickNew()
                .enterData("Account Name", accountName)
                //TODO fix issue with related object creation and uncomment
                //.createRelatedObject("Parent Account", relatedAccount)
                .save()
                .waitTillModalClosed()
                .waitTillOpened();

        detailsPage
                .clickTab(DetailsTabs.Details)
                .validate("Account Name", accountName);
                //.validate("Parent Account", relatedAccount.get("Account Name"));
    }
}