package com.itechart.tests.ui;

import com.codeborne.selenide.Configuration;
import com.itechart.configurations.TestListener;
import com.itechart.pages.HomePage;
import com.itechart.pages.LoginPage;
import com.itechart.pages.account.AccountDetailsPage;
import com.itechart.pages.account.AccountListViewPage;
import com.itechart.pages.account.AccountModalPage;
import com.itechart.utils.PropertyReader;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;

@Log4j2
@Listeners(TestListener.class)
public abstract class BaseTest {
    protected WebDriver driver;
    protected LoginPage loginPage;
    protected HomePage homePage;
    protected AccountListViewPage accountListViewPage;
    protected AccountModalPage accountModalPage;
    protected AccountDetailsPage accountDetailsPage;
    protected PropertyReader propertyReader = new PropertyReader("src/test/resources/configuration.properties");
    protected final String USERNAME = propertyReader.getPropertyValueByKey("username");
    protected final String PASSWORD = propertyReader.getPropertyValueByKey("password");

    @BeforeClass(description = "Open browser")
    public void setUp() {
        Configuration.baseUrl = propertyReader.getPropertyValueByKey("base.url");
        Configuration.timeout = 5000;

        loginPage = new LoginPage();
        homePage = new HomePage();
        accountDetailsPage = new AccountDetailsPage();
        accountListViewPage = new AccountListViewPage();
        accountModalPage = new AccountModalPage();
    }

    @AfterClass(alwaysRun = true, description = "Close browser")
    public void tearDown() {
        if (driver != null)
            driver.quit();
    }
}

/*
названия брачный по примеру: feature/ITA-1-removal-of-steps
названия комитов по примеру: ITA-1 removed utils
 */