package com.itechart.tests.api;

import com.itechart.models.Contact;
import com.itechart.models.ResponseStatus;
import com.itechart.configurations.Retry;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CrudApiContactTest extends BaseApiTest {

    @Test(retryAnalyzer = Retry.class, description = "CRUD API Contact")
    public void createGetUpdateDeleteContact() {
        Contact contact = contactFactory.createNewContact(false);
        ResponseStatus response = contactAdapter.create(contact);
        Assert.assertTrue(response.isSuccess(), "Response is not correct");
        Contact receivedContact = contactAdapter.get(response.getId());
        Assert.assertEquals(receivedContact.getName(), contact.getName(), "Response is not correct");
        contact = contactFactory.createNewContact(false);
        contactAdapter.update(contact, response.getId());
        receivedContact = contactAdapter.get(response.getId());
        Assert.assertEquals(receivedContact.getName(), contact.getName(), "Update request is not correct");
        contactAdapter.delete(response.getId());
    }
}
