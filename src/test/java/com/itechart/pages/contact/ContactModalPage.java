package com.itechart.pages.contact;

import com.itechart.elements.LightDropDown;
import com.itechart.elements.LightInput;
import com.itechart.models.Contact;
import com.itechart.pages.BasePage;
import io.qameta.allure.Step;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;

@Log4j2
public class ContactModalPage extends BasePage {
    private static final By SAVE_BUTTON_LOCATOR = By.xpath("//button[@name='SaveEdit']");

    public ContactModalPage() {
    }

    @Step("Enter data into fields")
    public ContactModalPage enterData(Contact contact) {
        log.info("Entering Contact Data: {}", contact);
        new LightDropDown("Salutation").selectOption(contact.getSalutation());
        new LightInput("First Name").write(contact.getFirstName());
        new LightInput("Last Name").write(contact.getLastName());
        new LightInput("Title").write(contact.getTitle());
        new LightInput("Email").write(contact.getEmail());
        new LightInput("Phone").write(contact.getPhone());
        new LightInput("Mobile").write(contact.getMobilePhone());
        new LightInput("Department").write(contact.getDepartment());
        new LightInput("Fax").write(contact.getFax());
        return this;
    }

    @Step("Clear data from fields")
    public ContactModalPage clearData() {
        new LightDropDown("Salutation").clear();
        new LightInput("First Name").clear();
        new LightInput("Last Name").clear();
        new LightInput("Title").clear();
        new LightInput("Email").clear();
        new LightInput("Phone").clear();
        new LightInput("Mobile").clear();
        new LightInput("Department").clear();
        new LightInput("Fax").clear();
        return this;
    }

    @SneakyThrows
    @Step("Click on Save button")
    public ContactDetailsPage clickSaveButton() {
        driver.findElement(SAVE_BUTTON_LOCATOR).click();
        Thread.sleep(1000);
        return new ContactDetailsPage(driver);
    }
}
