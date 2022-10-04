package com.itechart.steps;

import com.itechart.models.Lead;
import com.itechart.pages.lead.LeadDetailsPage;
import com.itechart.pages.lead.LeadListViewPage;
import com.itechart.tests.ui.BaseTest;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

@Log4j2
public class LeadSteps extends BaseTest {
    private final LeadListViewPage leadListViewPage;
    private final LeadDetailsPage leadDetailsPage;

    public LeadSteps(WebDriver driver) {
        leadListViewPage = new LeadListViewPage(driver);
        leadDetailsPage = new LeadDetailsPage(driver);
    }

    @Step("Creating Lead: {lead.firstName} {lead.lastName}")
    public LeadSteps create(Lead lead) {
        log.info("Creating Lead: {}", lead);
        boolean isLeadCreated =
                leadListViewPage
                        .clickNewButton()
                        .enterData(lead)
                        .clickSaveButton()
                        .isPageOpened();
        Assert.assertTrue(isLeadCreated, "Lead is not created");
        return this;
    }

    @Step("Updating Lead: {lead.firstName} {lead.lastName}")
    public LeadSteps edit(Lead lead) {
        log.info("Editing Lead: {}", lead);
        leadDetailsPage
                .clickEditDetailsButton()
                .clearData()
                .enterData(lead)
                .clickSaveButton();
        return this;
    }

    @Step("Opening Lead List View Page")
    public LeadSteps openLeadListViewPage() {
        log.info("Opening Lead List View Page");
        leadListViewPage.open();
        return this;
    }

    @Step("Validating Lead: {lead.firstName} {lead.lastName}")
    public LeadSteps validate(Lead lead) {
        log.info("Validating Lead: {}", lead);
        leadDetailsPage
                .openDetails()
                .validate(lead);
        return this;
    }

    @Step("Deleting Lead")
    public LeadSteps delete() {
        log.info("Deleting Lead");
        boolean isRecordDeleted =
                leadDetailsPage
                        .clickDeleteButton()
                        .delete()
                        .isSuccessDeleteMessageDisplayed();
        Assert.assertTrue(isRecordDeleted, "Record deletion failed");
        return this;
    }
}
