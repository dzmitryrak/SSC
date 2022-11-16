package com.itechart.tests;

import com.github.javafaker.Faker;
import com.itechart.tests.base.BaseTest;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

public class ContactCRUDTest extends BaseTest {

    Faker faker = new Faker();

    //TODO add CRUD
    @Test(description = "Create Read Update Delete Contact record")
    public void createNewContactRecord() {
        Map<String, String> contact = new HashMap<>() {{
            put("Last Name", faker.name().lastName());
            put("Salutation", "Prof.");
            put("First Name", faker.name().name());
            put("Phone", "+375232323");
            put("Home Phone", "+238282");
            put("BVT check", "true");
            //put("Account Name", "Emory Harber");
            put("Mobile", "+1238238");
            put("Title", "any title");
            put("Other Phone", "test");
            put("Department", "test");
            put("Fax", "test");
            put("Birthdate", "9/27/2022");
            put("Email", "asdasd@aasdasd.com");
            //put("Reports To", "Emory Harber");
            put("Assistant", "test");
            put("Lead Source", "Other");
            put("Asst. Phone", "+912389293");
            put("Mailing Street", "test");
            put("Other Street", "test");
            put("Mailing City", "test");
            put("Mailing State/Province", "test");
            put("Other City", "test");
            put("Other State/Province", "test");
            put("Mailing Zip/Postal Code", "test");
            put("Mailing Country", "test");
            put("Other Zip/Postal Code", "test");
            put("Other Country", "test");
            put("Languages", "test");
            put("Level", "Primary");
            put("Description", "test");
            put("Multiselect", "Yes;Maybe");
        }};

        loginPage.open();
        loginPage.login(USERNAME, PASSWORD);
        homePage.isPageOpened();
        listView
                .openUrl("Contact")
                .clickNew()
                .enterData(contact)
                .save();
        detailsPage.isPageOpened();
    }
}