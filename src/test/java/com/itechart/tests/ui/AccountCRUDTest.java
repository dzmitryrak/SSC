package com.itechart.tests.ui;
import com.github.javafaker.Faker;
import com.itechart.models.Account;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.Test;

public class AccountCRUDTest extends BaseTest {

    Faker faker = new Faker();

    @Test(description = "Create Read Update Delete Account record")
    public void createNewAccountRecord() {
        Account account = new Account(
                faker.name().name(),
                "Prospect",
                faker.internet().url(),
                "Apparel",
                faker.phoneNumber().phoneNumber(),
                faker.lorem().sentence(),
                faker.number().digit(),
                faker.address().streetAddress(),
                faker.address().city(),
                faker.address().zipCode(),
                faker.address().state(),
                faker.address().country(),
                faker.address().streetAddress(),
                faker.address().city(),
                faker.address().zipCode(),
                faker.address().state(),
                faker.address().country(),
                "Dmitry Rak");

        loginPage.open();
        loginPage.login(USERNAME, PASSWORD);
        homePage.isPageOpened();
        accountListViewPage.openUrl();
        accountListViewPage
                .clickNewButton()
                .enterData(account)
                .clickSaveButton()
                .isPageOpened();
        accountDetailsPage
                .openDetails()
                .validate(account);
    }

    @Test(description = "Edit new account created")
    public void editNewAccountRecord() {

        Account account = new Account(
                faker.name().name(),
                "Prospect",
                faker.internet().url(),
                "Apparel",
                faker.phoneNumber().phoneNumber(),
                faker.lorem().sentence(),
                faker.number().digit(),
                faker.address().streetAddress(),
                faker.address().city(),
                faker.address().zipCode(),
                faker.address().state(),
                faker.address().country(),
                faker.address().streetAddress(),
                faker.address().city(),
                faker.address().zipCode(),
                faker.address().state(),
                faker.address().country(),
                "Dmitry Rak");

        Account updatedAccount = new Account(
                faker.name().name(),
                "Prospect",
                faker.internet().url(),
                "Apparel",
                faker.phoneNumber().phoneNumber(),
                faker.lorem().sentence(),
                faker.number().digit(),
                faker.address().streetAddress(),
                faker.address().city(),
                faker.address().zipCode(),
                faker.address().state(),
                faker.address().country(),
                faker.address().streetAddress(),
                faker.address().city(),
                faker.address().zipCode(),
                faker.address().state(),
                faker.address().country(),
                "Dmitry Rak");

        loginPage.open();
        loginPage.login(USERNAME, PASSWORD);
        homePage.isPageOpened();
        accountListViewPage.openUrl();
        accountListViewPage
                .clickNewButton()
                .enterData(account)
                .clickSaveButton()
                .isPageOpened();
        accountDetailsPage
                .openDetails()
                .validate(account)
                .clickIconDropdownMenu()
                .clickEditDetailsButton()
                .clearData()
                .enterData(updatedAccount)
                .clickSaveButton();
        accountDetailsPage.openDetails();
        accountDetailsPage.validate(updatedAccount);
    }

    @Test(description = "Delete new account created")
    public void deleteNewAccountRecord() {

        Account account = new Account(
                faker.name().name(),
                "Prospect",
                faker.internet().url(),
                "Apparel",
                faker.phoneNumber().phoneNumber(),
                faker.lorem().sentence(),
                faker.number().digit(),
                faker.address().streetAddress(),
                faker.address().city(),
                faker.address().zipCode(),
                faker.address().state(),
                faker.address().country(),
                faker.address().streetAddress(),
                faker.address().city(),
                faker.address().zipCode(),
                faker.address().state(),
                faker.address().country(),
                "Dmitry Rak");

        loginPage.open();
        loginPage.login(USERNAME, PASSWORD);
        homePage.isPageOpened();
        accountListViewPage.openUrl();
        accountListViewPage
                .clickNewButton()
                .enterData(account)
                .clickSaveButton()
                .isPageOpened();
        accountDetailsPage
                .openDetails()
                .validate(account);
        accountDetailsPage
                .clickIconDropdownMenu()
                .clickDeleteButton()
                .delete()
                .isSuccessDeleteMessageDisplayed();
    }

    public void clickJS(By locator) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(locator));
    }
}