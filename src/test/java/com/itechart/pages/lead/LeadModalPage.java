package com.itechart.pages.lead;

import com.itechart.elements.LightDropDown;
import com.itechart.elements.LightInput;
import com.itechart.elements.TextArea;
import com.itechart.models.Lead;
import com.itechart.pages.BasePage;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;

import static org.apache.logging.log4j.MarkerManager.clear;

@Log4j2
public class LeadModalPage extends BasePage {
    private final By SAVE_BUTTON_LOCATOR = By.xpath("//*[@title='Save']");

    public LeadModalPage() {
    }

    @Step("Enter data into fields")
    public LeadModalPage enterData(Lead lead) {
        log.info("Entering Lead Data: {}", lead);
        new LightDropDown( "Lead Status").selectOption(lead.getStatus());
        new LightDropDown( "Salutation").selectOption(lead.getSalutation());
        new LightInput("First Name").write(lead.getFirstName());
        new LightInput("Last Name").write(lead.getLastName());
        new LightInput( "Title").write(lead.getTitle());
        new LightInput( "Email").write(lead.getEmail());
        new LightInput("Phone").write(lead.getPhone());
        new LightInput("Mobile").write(lead.getMobilePhone());
        new LightDropDown( "Rating").selectOption(lead.getRating());
        new LightInput("Website").write(lead.getWebsite());
        new LightInput( "Company").write(lead.getCompany());
        new LightDropDown( "Industry").selectOption(lead.getIndustry());
        new LightInput( "No. of Employees").write(lead.getNumberOfEmployees());
        new LightDropDown( "Lead Source").selectOption(lead.getLeadSource());
        new TextArea("Street").write(lead.getStreet());
        new LightInput( "City").write(lead.getCity());
        new LightInput( "Country").write(lead.getCountry());
        return new LeadModalPage();
    }

    @Step("Clear data from fields")
    public LeadModalPage clearData() {
        new LightInput( "First Name").clear();
        new LightDropDown("Salutation").clear();
        new LightDropDown( "Lead Status").clear();
        new LightInput( "Last Name").clear();
        new LightInput ("Title").clear();
        new LightInput("Email").clear();
        new LightInput( "Phone").clear();
        new LightInput("Mobile").clear();
        new LightDropDown("Rating").clear();
        new LightInput("Website").clear();
        new LightInput("Company").clear();
        new LightDropDown( "Industry").clear();
        new LightInput( "No. of Employees").clear();
        new LightDropDown( "Lead Source").clear();
        new TextArea("Street").clear();
        new LightInput( "City").clear();
        new LightInput( "Country").clear();
        return this;
    }

    @Step("Click on Save button")
    public LeadDetailsPage clickSaveButton() {
        driver.findElement(SAVE_BUTTON_LOCATOR).click();
        return new LeadDetailsPage();
    }
}
