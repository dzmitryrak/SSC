package com.itechart.pages;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

@Log4j2
public class HomePage extends BasePage {
    private final By LOGO_LOCATOR = By.xpath("//*[contains(@class, 'slds-page-header')]//ancestor::lightning-primitive-icon/*[@data-key='home']");

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public HomePage open() {
        driver.get(baseUrl);
        return this;
    }

    @Override
    public boolean isPageOpened() {
        wait.until(ExpectedConditions.presenceOfElementLocated(LOGO_LOCATOR));
        waitForPageLoaded();
        return driver.findElement(LOGO_LOCATOR).isDisplayed();
    }
}


