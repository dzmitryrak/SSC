package com.itechart.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.itechart.pages.account.AccountListViewPage;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.testng.Assert;

import java.time.Duration;
import java.util.Map;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

@Log4j2
public class DetailsPage extends BasePage {
    private final String DETAILS_TAB_FIELD_LOCATOR = "//*[text()='%s']/../..//*[contains(@class, 'slds-form-element__control')]";
    private final String DETAILS_TAB_OPPORTUNITY = "//*[text()='%s']/../..//*[contains(@class, 'slds-input')]";
    private final By DETAILS_TAB = By.xpath("//a[@data-label='Details']");
    private String COMMON_TAB = "//a[@data-label='%s']";
    private final By CHANGE_OWNER_BUTTON = By.xpath("//*[@name='ChangeOwnerOne']");
    private final By CHECK_FOR_NEW_DATA_BUTTON = By.xpath("//*[@name='XClean']");
    private final By CLONE_BUTTON = By.xpath("//*[@name='Clone']");
    private final By SHARE_BUTTON = By.xpath("//*[@name='Share']");
    private final By CONVERT_BUTTON = By.xpath("//*[@name='Convert']");
    private final By INPUT_BUTTON = By.xpath("//input[@name='fileInput']");
    private final By SUBMIT_FOR_APPROVAL_BUTTON = By.xpath("//a[@name='Submit']");
    private final By FOLLOW_BUTTON = By.xpath("//span[@title='Follow']//ancestor::button");
    private final By FOLLOWING_BUTTON = By.xpath("//span[@title='Following']//ancestor::button");
    private final By NEW_CONTACT_BUTTON = By.xpath("//button[@name='Global.NewContact']");
    private final By NEW_CASE_BUTTON = By.xpath("//button[@name='Global.NewCase']");
    private final By NEW_NOTE_BUTTON = By.xpath("//button[@name='Global.NewNote']");
    private final By NEW_OPPORTUNITY_BUTTON = By.xpath("//a[@name='Global.NewOpportunity']");
    private final By VIEW_ACCOUNT_HIERARCHY_BUTTON = By.xpath("//a[@name='AccountHierarchy']");
    private final By VIEW_WEBSITE_BUTTON = By.xpath("//a[@name='WebsiteHighlightAction']");
    private final By MARK_STATUS_AS_COMPLETE_BUTTON = By.xpath("//span[text()='Mark Status as Complete']//ancestor::button");
    private final By EDIT_DETAILS_BUTTON_LOCATOR = By.xpath("//*[@name='Edit']");
    private final By ICON_DROPDOWN_MENU = By.xpath("//*[contains(@class, 'slds-dropdown-trigger slds-dropdown-trigger_click slds-button_last overflow')]//button");
    private final By DELETE_BUTTON = By.xpath("//*[@name='Delete']");
    private final By SUCCESS_MESSAGE = By.xpath("//*[text() ='Are you sure you want to delete this account?']");
    private final By DELETE_MODAL_TITLE = By.xpath("//*[text() ='Delete Account']");
    private final By DELETE_MODAL_BUTTON = By.xpath("//div[@class='modal-container slds-modal__container']//button[@title= 'Delete']");
    private final By DETAILS_FIELDS = By.cssSelector(".slds-form-element_readonly");
    private final By ALERT_MESSAGE = By.xpath("//*[@role = 'alertdialog']");

    public DetailsPage() { }

    @Step("Check that Account Details page was opened")
    @Override
    public boolean isPageOpened() {
        $(DETAILS_TAB).shouldBe(visible, Duration.ofSeconds(20));

        return true;
    }

    @Step("Open Details tab")
    public DetailsPage openDetails() {
        log.info("Clicking on the first case");

        $(DETAILS_TAB).shouldBe(Condition.visible, Duration.ofSeconds(10));
        clickJS(DETAILS_TAB);
        waitForPageLoaded();

        return this;
    }

    @Step("Open {tabName} tab")
    public DetailsPage clickTab(String tabName) {
        log.info("Opening {} tab", tabName);

        clickJS(By.xpath(String.format(COMMON_TAB, tabName)));

        return this;
    }

    @Step("Click Edit button")
    public NewObjectModal clickEditDetailsButton() {
        $(EDIT_DETAILS_BUTTON_LOCATOR).click();
        NewObjectModal accountModalPage = new NewObjectModal();
        accountModalPage.isPageOpened();

        return accountModalPage;
    }

    @Step("Validation of entered data")
    public DetailsPage validate(Map<String, String> data) {
        log.info("Validating Details Data: {}", data);

        prepareForValidation();

        for (Map.Entry<String, String> entry : data.entrySet()) {
            String fieldLabel = entry.getKey();
            String value = entry.getValue();

            sfHelper.validate(fieldLabel, value);
        }

        return this;
    }

    public DetailsPage validateInputField(String locator, String expectedInput) {
        String actualInput = $(By.xpath(String.format(DETAILS_TAB_FIELD_LOCATOR, locator))).getText();
        Assert.assertTrue(actualInput.contains(expectedInput),
            String.format("%s input is not correct.Expected: '%s' Actual: '%s'", locator, expectedInput, actualInput));

        log.debug("Validating {} input.Expected: '{}' Actual: '{}'", locator, expectedInput, actualInput);

        return this;
    }

    public DetailsPage validateInputOpportunity(String locator, String expectedInput) {
        String actualInput = $(By.xpath(String.format(DETAILS_TAB_OPPORTUNITY, locator))).getValue();
        Assert.assertTrue(actualInput.contains(expectedInput),
                String.format("%s input is not correct.Expected: '%s' Actual: '%s'", locator, expectedInput, actualInput));

        log.debug("Validating {} input.Expected: '{}' Actual: {}", locator, expectedInput, actualInput);

        return this;
    }


    @Step("Click on Dropdown icon menu")
    public DetailsPage clickIconDropdownMenu() {
        try {
            clickJS(ICON_DROPDOWN_MENU);
        } catch (StaleElementReferenceException e) {
            log.warn("Cannot find Icon Dropdown menu icon");
            log.warn(e.getLocalizedMessage());
        }

        return this;
    }

    @Step("Click on Delete button")
    public DetailsPage clickDeleteButton() {
        try {
            $(DELETE_BUTTON).click();
        } catch (StaleElementReferenceException e) {
            log.warn("Cannot find Delete button");
            log.warn(e.getLocalizedMessage());
            $(DELETE_BUTTON).click();
        }

        return this;
    }

    public boolean isModalOpened() {
        $(DELETE_MODAL_TITLE).shouldBe(visible);
        $(DELETE_MODAL_BUTTON).shouldBe(visible);

        return $(DELETE_MODAL_TITLE).getText().contains("Delete Account");
    }

    @Step("Confirm deletion of an account")
    public AccountListViewPage delete() {
        if (!isModalOpened()) {
            throw new RuntimeException("Delete modal is not opened");
        }

        $(SUCCESS_MESSAGE).should(exist);
        $(DELETE_MODAL_BUTTON).click();

        return new AccountListViewPage();
    }

    @Step("Click Follow button")
    public DetailsPage clickFollowButton() {
        log.info("Clicking Follow button");

        clickJS(FOLLOW_BUTTON);
        $(FOLLOWING_BUTTON).shouldBe(visible);

        return this;
    }

    @Step("Click New Contact button")
    public NewObjectModal clickNewContactButton() {
        log.info("Clicking New Contact button");

        clickJS(NEW_CONTACT_BUTTON);
        NewObjectModal contactModal = new NewObjectModal();
        contactModal.isPageOpened();

        return contactModal;
    }

    @Step("Click New Case button")
    public NewObjectModal clickNewCaseButton() {
        log.info("Clicking New Case button");

        clickJS(NEW_CASE_BUTTON);
        NewObjectModal caseModal = new NewObjectModal();
        caseModal.isPageOpened();

        return caseModal;
    }

    @Step("Click New Note button")
    public NewObjectModal clickNewNoteButton() {
        log.info("Clicking New Note button");

        clickJS(NEW_NOTE_BUTTON);
        NewObjectModal noteModal = new NewObjectModal();
        noteModal.isPageOpened();

        return noteModal;
    }

    @Step("Click New Opportunity button")
    public NewObjectModal clickNewOpportunityButton() {
        log.info("Clicking New Opportunity button");

        clickJS(NEW_OPPORTUNITY_BUTTON);
        NewObjectModal opportunityModal = new NewObjectModal();
        opportunityModal.isPageOpened();

        return opportunityModal;
    }

    @Step("Click Clone button")
    public NewObjectModal clickCloneButton() {
        log.info("Clicking Clone button");

        clickJS(CLONE_BUTTON);
        NewObjectModal newObjectModal = new NewObjectModal();
        newObjectModal.isPageOpened();

        return newObjectModal;
    }

    @Step("Click Submit For Approval button")
    public NewObjectModal clickSubmitForApprovalButton() {
        log.info("Clicking Submit For Approval button");

        clickJS(SUBMIT_FOR_APPROVAL_BUTTON);
        NewObjectModal newObjectModal = new NewObjectModal();
        newObjectModal.isPageOpened();

        return newObjectModal;
    }

    @Step("Click Sharing button")
    public NewObjectModal clickSharingButton() {
        log.info("Clicking Sharing button");

        clickJS(SHARE_BUTTON);
        NewObjectModal newObjectModal = new NewObjectModal();
        newObjectModal.isPageOpened();

        return newObjectModal;
    }

    @Step("Click Change Owner button")
    public NewObjectModal clickChangeOwnerButton() {
        log.info("Clicking Change Owner button");

        clickJS(CHANGE_OWNER_BUTTON);
        NewObjectModal newObjectModal = new NewObjectModal();
        newObjectModal.isPageOpened();

        return newObjectModal;
    }

    @Step("Click Check for New Data button")
    public NewObjectModal clickCheckForNewDataButton() {
        log.info("Clicking Check for New Data button");

        clickJS(CHECK_FOR_NEW_DATA_BUTTON);
        NewObjectModal newObjectModal = new NewObjectModal();
        newObjectModal.isPageOpened();

        return newObjectModal;
    }

    @Step("Click Convert button")
    public NewObjectModal clickConvertButton() {
        log.info("Clicking Convert button");

        clickJS(CONVERT_BUTTON);
        NewObjectModal newObjectModal = new NewObjectModal();
        newObjectModal.isPageOpened();

        return newObjectModal;
    }

    @Step("Click View Account Hierarchy button")
    public AccountListViewPage clickAccountHierarchyButton() {
        log.info("Clicking View Account Hierarchy button");

        clickJS(VIEW_ACCOUNT_HIERARCHY_BUTTON);
        AccountListViewPage accountListViewPage = new AccountListViewPage();
        accountListViewPage.isPageOpened();

        return accountListViewPage;
    }

    @Step("Click View Website button")
    public void clickViewWebsiteButton() {
        log.info("Clicking View Website button");

        String websiteUrl =
            $(By.xpath("//p[@title='Website']//ancestor::div[@records-highlightsdetailsitem_highlightsdetailsitem]//descendant::a"))
                .getText();

        clickJS(VIEW_WEBSITE_BUTTON);
        waitForPageLoaded();

        if (!switchTo().window(1).getTitle().equals(websiteUrl)) {
            throw new RuntimeException("Website is not opened");
        }
    }

    private void waitTillDetailsFieldsAreLoaded() {
        for (SelenideElement field : $$(DETAILS_FIELDS)) {
            field.shouldBe(visible);
        }
    }

    private void prepareForValidation() {
        Selenide.refresh();
        openDetails();
        $(ALERT_MESSAGE).shouldBe(not(visible));
        waitTillDetailsFieldsAreLoaded();
    }
}
