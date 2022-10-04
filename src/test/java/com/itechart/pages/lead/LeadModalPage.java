package com.itechart.pages.lead;

import com.itechart.elements.LightDropDown;
import com.itechart.elements.LightInput;
import com.itechart.elements.TextArea;
import com.itechart.models.Lead;
import com.itechart.pages.BasePage;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

@Log4j2
public class LeadModalPage extends BasePage {
    private final By SAVE_BUTTON_LOCATOR = By.xpath("//*[@title='Save']");

    public LeadModalPage(WebDriver driver) {
        super(driver);
    }

    @Step("Enter data into fields")
    public LeadModalPage enterData(Lead lead) {
        log.info("Entering Lead Data: {}", lead);
        new LightDropDown(driver, "Lead Status").selectOption(lead.getStatus());
        new LightDropDown(driver, "Salutation").selectOption(lead.getSalutation());
        new LightInput(driver, "First Name").write(lead.getFirstName());
        new LightInput(driver, "Last Name").write(lead.getLastName());
        new LightInput(driver, "Title").write(lead.getTitle());
        new LightInput(driver, "Email").write(lead.getEmail());
        new LightInput(driver, "Phone").write(lead.getPhone());
        new LightInput(driver, "Mobile").write(lead.getMobilePhone());
        new LightDropDown(driver, "Rating").selectOption(lead.getRating());
        new LightInput(driver, "Website").write(lead.getWebsite());
        new LightInput(driver, "Company").write(lead.getCompany());
        new LightDropDown(driver, "Industry").selectOption(lead.getIndustry());
        new LightInput(driver, "No. of Employees").write(lead.getNumberOfEmployees());
        new LightDropDown(driver, "Lead Source").selectOption(lead.getLeadSource());
        new TextArea(driver, "Street").write(lead.getStreet());
        new LightInput(driver, "City").write(lead.getCity());
        new LightInput(driver, "Country").write(lead.getCountry());
        return new LeadModalPage(driver);
    }

    @Step("Clear data from fields")
    public LeadModalPage clearData() {
        new LightInput(driver, "First Name").clear();
        new LightDropDown(driver, "Salutation").clear();
        new LightDropDown(driver, "Lead Status").clear();
        new LightInput(driver, "Last Name").clear();
        new LightInput(driver, "Title").clear();
        new LightInput(driver, "Email").clear();
        new LightInput(driver, "Phone").clear();
        new LightInput(driver, "Mobile").clear();
        new LightDropDown(driver, "Rating").clear();
        new LightInput(driver, "Website").clear();
        new LightInput(driver, "Company").clear();
        new LightDropDown(driver, "Industry").clear();
        new LightInput(driver, "No. of Employees").clear();
        new LightDropDown(driver, "Lead Source").clear();
        new TextArea(driver, "Street").clear();
        new LightInput(driver, "City").clear();
        new LightInput(driver, "Country").clear();
        return this;
    }

    @Step("Click on Save button")
    public LeadDetailsPage clickSaveButton() {
        driver.findElement(SAVE_BUTTON_LOCATOR).click();
        return new LeadDetailsPage(driver);
    }
}
