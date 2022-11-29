package com.itechart.tests;

import com.github.javafaker.Faker;
import com.itechart.constants.DetailsTabs;
import com.itechart.tests.base.BaseTest;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

public class ContactCRUDTest extends BaseTest {

    Faker faker = new Faker();

    @Test(description = "Create Contact")
    public void createContact() {
        Map<String, String> contact = new HashMap<>() {{
            put("Last Name", faker.name().lastName());
            put("Salutation", "Prof.");
            put("First Name", faker.name().name());
            put("Phone", faker.phoneNumber().phoneNumber());
            put("Home Phone", faker.phoneNumber().phoneNumber());
            put("BVT check", "true");
            put("Account Name", "Emory Harber");
            put("Mobile", faker.phoneNumber().phoneNumber());
            put("Title", "any title");
            put("Other Phone", faker.phoneNumber().phoneNumber());
            put("Department", "test");
            put("Fax", "test");
            put("Birthdate", "9/27/2022");
            put("Email", faker.internet().emailAddress());
            put("Reports To", "Berry Reilly Shields");
            put("Assistant", "test");
            put("Lead Source", "Other");
            put("Asst. Phone", faker.phoneNumber().phoneNumber());
            put("Mailing Street", faker.address().streetAddress());
            put("Other Street", faker.address().streetAddress());
            put("Mailing City", faker.address().city());
            put("Mailing State/Province", "test");
            put("Other City", faker.address().city());
            put("Other State/Province", "test");
            put("Mailing Zip/Postal Code", "test");
            put("Mailing Country", faker.address().country());
            put("Other Zip/Postal Code", "test");
            put("Other Country", faker.address().country());
            put("Languages", "test");
            put("Level", "Primary");
            put("Description", faker.lorem().sentence());
            put("Multiselect", "Yes;Maybe");
        }};

        loginPage.open();
        loginPage.login(USERNAME, PASSWORD);
        homePage.isPageOpened();
        listView
                .open("Contact")
                .clickNew()
                .enterData(contact)
                .save()
                .waitTillModalClosed()
                .waitTillOpened();;
        contact.remove("First Name");
        contact.remove("Last Name");
        contact.remove("Salutation");
        contact.remove("Mailing Street");
        contact.remove("Other Street");
        contact.remove("Mailing City");
        contact.remove("Mailing State/Province");
        contact.remove("Other City");
        contact.remove("Other State/Province");
        contact.remove("Mailing Zip/Postal Code");
        contact.remove("Mailing Country");
        contact.remove("Other Zip/Postal Code");
        contact.remove("Other Country");

        detailsPage.clickTab(DetailsTabs.Details)
                .validate(contact);
    }

    @Test(description = "Edit Contact")
    public void editContact() {
        Map<String, String> contact = new HashMap<>() {{
            put("Last Name", faker.name().lastName());
            put("Salutation", "Prof.");
            put("First Name", faker.name().name());
            put("Phone", faker.phoneNumber().phoneNumber());
            put("Home Phone", faker.phoneNumber().phoneNumber());
            put("BVT check", "false");
            put("Account Name", "Emory Harber");
            put("Mobile", faker.phoneNumber().phoneNumber());
            put("Title", "any title");
            put("Other Phone", faker.phoneNumber().phoneNumber());
            put("Department", "test");
            put("Fax", "test");
            put("Birthdate", "9/27/2022");
            put("Email", faker.internet().emailAddress());
            put("Reports To", "Berry Reilly Shields");
            put("Assistant", "test");
            put("Lead Source", "Other");
            put("Asst. Phone", faker.phoneNumber().phoneNumber());
            put("Mailing Street", faker.address().streetAddress());
            put("Other Street", faker.address().streetAddress());
            put("Mailing City", faker.address().city());
            put("Mailing State/Province", "test");
            put("Other City", faker.address().city());
            put("Other State/Province", "test");
            put("Mailing Zip/Postal Code", "test");
            put("Mailing Country", faker.address().country());
            put("Other Zip/Postal Code", "test");
            put("Other Country", faker.address().country());
            put("Languages", "test");
            put("Level", "Primary");
            put("Description", faker.lorem().sentence());
            put("Multiselect", "Yes;Maybe");
        }};

        Map<String, String> updatedContact = new HashMap<>() {{
            put("Last Name", faker.name().lastName());
            put("Salutation", "Prof.");
            put("First Name", faker.name().name());
            put("Phone", faker.phoneNumber().phoneNumber());
            put("Home Phone", faker.phoneNumber().phoneNumber());
            put("BVT check", "false");
            put("Account Name", "Desire Barton");
            put("Mobile", faker.phoneNumber().phoneNumber());
            put("Title", "any title");
            put("Other Phone", faker.phoneNumber().phoneNumber());
            put("Department", "test");
            put("Fax", "test");
            put("Birthdate", "9/27/2022");
            put("Email", faker.internet().emailAddress());
            put("Reports To", "Reid Mueller Wilkinson");
            put("Assistant", "test");
            put("Lead Source", "Other");
            put("Asst. Phone", faker.phoneNumber().phoneNumber());
            put("Mailing Street", faker.address().streetAddress());
            put("Other Street", faker.address().streetAddress());
            put("Mailing City", faker.address().city());
            put("Mailing State/Province", "test");
            put("Other City", faker.address().city());
            put("Other State/Province", "test");
            put("Mailing Zip/Postal Code", "test");
            put("Mailing Country", faker.address().country());
            put("Other Zip/Postal Code", "test");
            put("Other Country", faker.address().country());
            put("Languages", "test");
            put("Level", "Primary");
            put("Description", faker.lorem().sentence());
            put("Multiselect", "Yes;Maybe");
        }};

        loginPage.open();
        loginPage.login(USERNAME, PASSWORD);
        homePage.isPageOpened();
        listView
                .open("Contact")
                .clickNew()
                .enterData(contact)
                .save()
                .waitTillModalClosed()
                .waitTillOpened();
        contact.remove("First Name");
        contact.remove("Last Name");
        contact.remove("Salutation");
        contact.remove("Mailing Street");
        contact.remove("Other Street");
        contact.remove("Mailing City");
        contact.remove("Mailing State/Province");
        contact.remove("Other City");
        contact.remove("Other State/Province");
        contact.remove("Mailing Zip/Postal Code");
        contact.remove("Mailing Country");
        contact.remove("Other Zip/Postal Code");
        contact.remove("Other Country");
        detailsPage
                .clickTab(DetailsTabs.Details)
                .validate(contact);
        detailsPage
                .clickIconDropdownMenu()
                .editObject();
        newObjectModal
                .clearData(updatedContact)
                .enterData(updatedContact)
                .save()
                .waitTillModalClosed()
                .waitTillOpened();;

        updatedContact.remove("First Name");
        updatedContact.remove("Last Name");
        updatedContact.remove("Salutation");
        updatedContact.remove("Mailing Street");
        updatedContact.remove("Other Street");
        updatedContact.remove("Mailing City");
        updatedContact.remove("Mailing State/Province");
        updatedContact.remove("Other City");
        updatedContact.remove("Other State/Province");
        updatedContact.remove("Mailing Zip/Postal Code");
        updatedContact.remove("Mailing Country");
        updatedContact.remove("Other Zip/Postal Code");
        updatedContact.remove("Other Country");

        detailsPage
                .clickTab(DetailsTabs.Details)
                .validate(updatedContact);
    }

    @Test(description = "Delete Contact")
    public void deleteContact() {
        Map<String, String> contact = new HashMap<>() {{
            put("Last Name", faker.name().lastName());
            put("Salutation", "Prof.");
            put("First Name", faker.name().name());
            put("Phone", faker.phoneNumber().phoneNumber());
            put("Home Phone", faker.phoneNumber().phoneNumber());
            put("BVT check", "true");
            put("Account Name", "Emory Harber");
            put("Mobile", faker.phoneNumber().phoneNumber());
            put("Title", "any title");
            put("Other Phone", faker.phoneNumber().phoneNumber());
            put("Department", "test");
            put("Fax", "test");
            put("Birthdate", "9/27/2022");
            put("Email", faker.internet().emailAddress());
            put("Reports To", "Berry Reilly Shields");
            put("Assistant", "test");
            put("Lead Source", "Other");
            put("Asst. Phone", faker.phoneNumber().phoneNumber());
            put("Mailing Street", faker.address().streetAddress());
            put("Other Street", faker.address().streetAddress());
            put("Mailing City", faker.address().city());
            put("Mailing State/Province", "test");
            put("Other City", faker.address().city());
            put("Other State/Province", "test");
            put("Mailing Zip/Postal Code", "test");
            put("Mailing Country", faker.address().country());
            put("Other Zip/Postal Code", "test");
            put("Other Country", faker.address().country());
            put("Languages", "test");
            put("Level", "Primary");
            put("Description", faker.lorem().sentence());
            put("Multiselect", "Yes;Maybe");
        }};

        loginPage.open();
        loginPage.login(USERNAME, PASSWORD);
        homePage.isPageOpened();
        listView
                .open("Contact")
                .clickNew()
                .enterData(contact)
                .save()
                .waitTillModalClosed()
                .waitTillOpened();
        contact.remove("First Name");
        contact.remove("Last Name");
        contact.remove("Salutation");
        contact.remove("Mailing Street");
        contact.remove("Other Street");
        contact.remove("Mailing City");
        contact.remove("Mailing State/Province");
        contact.remove("Other City");
        contact.remove("Other State/Province");
        contact.remove("Mailing Zip/Postal Code");
        contact.remove("Mailing Country");
        contact.remove("Other Zip/Postal Code");
        contact.remove("Other Country");
        detailsPage
                .clickTab(DetailsTabs.Details)
                .validate(contact);
        detailsPage
                .clickIconDropdownMenu()
                .clickDeleteButton()
                .delete()
                .isSuccessDeleteMessageDisplayed();
    }
}