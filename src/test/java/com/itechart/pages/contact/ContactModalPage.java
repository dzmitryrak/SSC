package com.itechart.pages.contact;

import com.itechart.elements.LightDropDown;
import com.itechart.elements.LightInput;
import com.itechart.elements.LightLookup;
import com.itechart.models.Contact;
import com.itechart.pages.BasePage;
import io.qameta.allure.Step;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

@Log4j2
public class ContactModalPage extends BasePage {
    private static final By SAVE_BUTTON_LOCATOR = By.xpath("//button[@name='SaveEdit']");

    public ContactModalPage(WebDriver driver) {
        super(driver);
    }

    @Step("Enter data into fields")
    public ContactModalPage enterData(Contact contact) {
        log.info("Entering Contact Data: {}", contact);
        new LightDropDown(driver, "Salutation").selectOption(contact.getSalutation());
        new LightInput(driver, "First Name").write(contact.getFirstName());
        new LightInput(driver, "Last Name").write(contact.getLastName());
        new LightInput(driver, "Title").write(contact.getTitle());
        new LightInput(driver, "Email").write(contact.getEmail());
        new LightInput(driver, "Phone").write(contact.getPhone());
        new LightInput(driver, "Mobile").write(contact.getMobilePhone());
        new LightInput(driver, "Department").write(contact.getDepartment());
        new LightInput(driver, "Fax").write(contact.getFax());
        return this;
    }

    @Step("Clear data from fields")
    public ContactModalPage clearData() {
        new LightDropDown(driver, "Salutation").clear();
        new LightInput(driver, "First Name").clear();
        new LightInput(driver, "Last Name").clear();
        new LightInput(driver, "Title").clear();
        new LightInput(driver, "Email").clear();
        new LightInput(driver, "Phone").clear();
        new LightInput(driver, "Mobile").clear();
        new LightInput(driver, "Department").clear();
        new LightInput(driver, "Fax").clear();
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
