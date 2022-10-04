package com.itechart.steps;

import com.itechart.models.Contact;
import com.itechart.pages.contact.ContactDetailsPage;
import com.itechart.pages.contact.ContactListViewPage;
import com.itechart.tests.ui.BaseTest;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

@Log4j2
public class ContactSteps extends BaseTest {
    private final ContactListViewPage contactListViewPage;
    private final ContactDetailsPage contactDetailsPage;

    public ContactSteps(WebDriver driver) {
        contactListViewPage = new ContactListViewPage(driver);
        contactDetailsPage = new ContactDetailsPage(driver);
    }

    @Step("Creating Contact: {contact.firstName} {contact.lastName}")
    public ContactSteps create(Contact contact) {
        log.info("Creating Contact: {}", contact);
        boolean isContactCreated =
                contactListViewPage
                        .clickNewButton()
                        .enterData(contact)
                        .clickSaveButton()
                        .isPageOpened();
        Assert.assertTrue(isContactCreated, "Contact is not created");
        return this;
    }

    @Step("Updating Contact: {contact.firstName} {contact.lastName}")
    public ContactSteps edit(Contact contact) {
        log.info("Editing Contact: {}", contact);
        contactDetailsPage
                .clickEditDetailsButton()
                .clearData()
                .enterData(contact)
                .clickSaveButton();
        return this;
    }

    @Step("Opening Contact List View Page")
    public ContactSteps openContactListViewPage() {
        log.info("Opening Contact List View Page");
        contactListViewPage.open();
        return this;
    }

    @Step("Validating Contact: {contact.firstName} {contact.lastName}")
    public ContactSteps validate(Contact contact) {
        log.info("Validating Contact: {}", contact);
        contactDetailsPage
                .openDetails()
                .validate(contact);
        return this;
    }

    @Step("Deleting Contact")
    public ContactSteps delete() {
        log.info("Deleting Contact");
        boolean isRecordDeleted =
                contactDetailsPage
                        .clickDeleteButton()
                        .delete()
                        .isSuccessDeleteMessageDisplayed();
        Assert.assertTrue(isRecordDeleted, "Record deletion failed");
        return this;
    }
}
