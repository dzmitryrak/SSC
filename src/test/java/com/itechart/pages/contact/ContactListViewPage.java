package com.itechart.pages.contact;

import com.itechart.pages.BasePage;
import lombok.extern.log4j.Log4j2;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

@Log4j2
public class ContactListViewPage extends BasePage {
    private static final By NEW_BUTTON_LOCATOR = By.xpath("//a[@title='New']");
    private final By SUCCESS_DELETE_MESSAGE = By.xpath("//*[contains(@class, 'slds-theme--success')]");

    public ContactListViewPage(WebDriver driver) {
        super(driver);
    }

    @Step("Open List View for Contact")
    public ContactListViewPage open() {
        driver.get(baseUrl + "lightning/o/Contact/list?filterName=Recent");
        return this;
    }

    @Step("Click on New button")
    public ContactModalPage clickNewButton() {
        driver.findElement(NEW_BUTTON_LOCATOR).click();
        return new ContactModalPage(driver);
    }

    @Step("Check that Contact was deleted successfully")
    public boolean isSuccessDeleteMessageDisplayed() {
        boolean isSuccessMessageDisplayed;
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(SUCCESS_DELETE_MESSAGE));
            isSuccessMessageDisplayed = driver.findElement(SUCCESS_DELETE_MESSAGE).isDisplayed();
        } catch (StaleElementReferenceException e) {
            log.warn("Contact record successfully deleted message is not found");
            log.warn(e.getLocalizedMessage());
            isSuccessMessageDisplayed = driver.findElement(SUCCESS_DELETE_MESSAGE).isDisplayed();
        }
        return isSuccessMessageDisplayed;
    }
}
