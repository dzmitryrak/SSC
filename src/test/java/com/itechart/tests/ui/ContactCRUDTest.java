package com.itechart.tests.ui;

import com.itechart.models.Contact;
import com.itechart.configurations.Retry;
import org.testng.annotations.Test;

public class ContactCRUDTest extends BaseTest {
    @Test(retryAnalyzer = Retry.class, description = "Create Read Update Delete Contact record")
    public void testCreateNewContact() {
        Contact contact = contactFactory.createNewContact(true);
        Contact updatedContact = contactFactory.createNewContact(true);
        loginSteps.login(USERNAME, PASSWORD);
        contactSteps
                .openContactListViewPage()
                .create(contact)
                .validate(contact)
                .edit(updatedContact)
                .validate(updatedContact)
                .delete();
    }
}
