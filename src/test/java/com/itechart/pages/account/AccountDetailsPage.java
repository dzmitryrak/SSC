package com.itechart.pages.account;

import com.itechart.models.Account;
import com.itechart.pages.BasePage;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

@Log4j2
public class AccountDetailsPage extends BasePage {

    private final By DETAILS_TAB = By.xpath("//a[@data-label='Details']");
    private final By EDIT_DETAILS_BUTTON_LOCATOR = By.xpath("//*[@name='Edit']");
    private final By ICON_DROPDOWN_MENU = By.xpath("//*[contains(@class, 'slds-button slds-button_icon-border-filled')]");
    private final By DELETE_BUTTON = By.xpath("//*[@name ='Delete']");
    private final By SUCCESS_MESSAGE = By.xpath("//*[contains(@class, 'slds-theme--success')]");
    private final By DELETE_MODAL_TITLE = By.xpath("//div[@class='modal-container slds-modal__container']//h2");
    private final By DELETE_MODAL_BUTTON = By.xpath("//div[@class='modal-container slds-modal__container']//button[@title= 'Delete']");

    public AccountDetailsPage(WebDriver driver) {
        super(driver);
    }

    @Step("Check that Account Details page was opened")
    @Override
    public boolean isPageOpened() {
        wait.until(ExpectedConditions.presenceOfElementLocated(DETAILS_TAB));
        return true;
    }

    @Step("Open Details tab")
    public AccountDetailsPage openDetails() {
        clickJS(DETAILS_TAB);
        return this;
    }

    @Step("Click Edit button")
    public AccountModalPage clickEditDetailsButton() {
        driver.findElement(EDIT_DETAILS_BUTTON_LOCATOR).click();
        return new AccountModalPage(driver);
    }

    @Step("Validation of entered data")
    public AccountDetailsPage validate(Account account) {
        log.info("Validating Account Data: {}", account);
        validateInput("Account Name", account.getName());
        validateInput("Type", account.getType());
        validateInput("Description", account.getDescription());
        validateInput("Industry", account.getIndustry());
        validateInput("Website", account.getWebsite());
        validateInput("Phone", account.getPhone());
        validateInput("Employees", account.getNumberOfEmployees());
        validateInput("Account Owner", account.getAccountOwner());
        return this;
    }

    @Step("Click on Dropdown icon menu")
    public AccountDetailsPage clickIconDropdownMenu(){
        try {
            driver.findElement(ICON_DROPDOWN_MENU).click();
        } catch (StaleElementReferenceException e){
                log.warn("Cannot find Icon Dropdown menu icon");
                log.warn(e.getLocalizedMessage());
           //     driver.findElement(ICON_DROPDOWN_MENU).click;
            }
        return this;
    }


    @Step("Click on Delete button")
    public AccountDetailsPage clickDeleteButton() {
        try {
            driver.findElement(DELETE_BUTTON).click();
        } catch (StaleElementReferenceException e) {
            log.warn("Cannot find Delete button");
            log.warn(e.getLocalizedMessage());
            driver.findElement(DELETE_BUTTON).click();
        }
        return this;
    }

    public boolean isModalOpened() {
        wait.until(ExpectedConditions.presenceOfElementLocated(DELETE_MODAL_TITLE));
        wait.until(ExpectedConditions.elementToBeClickable(DELETE_MODAL_BUTTON));
        return driver.findElement(DELETE_MODAL_TITLE).getText().contains("Delete");
    }

    @Step("Confirm deletion of an account")
    public AccountListViewPage delete() {
        if (!isModalOpened()) throw new RuntimeException("Delete modal is not opened");
        wait.until(ExpectedConditions.invisibilityOfElementLocated(SUCCESS_MESSAGE));
        driver.findElement(DELETE_MODAL_BUTTON).click();
        return new AccountListViewPage(driver);
    }
}
