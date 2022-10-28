package com.itechart.tests.base;

import com.codeborne.selenide.Configuration;
import com.itechart.pages.HomePage;
import com.itechart.pages.LoginPage;
import com.itechart.pages.account.AccountDetailsPage;
import com.itechart.pages.account.AccountListViewPage;
import com.itechart.pages.account.AccountModalPage;
import com.itechart.utils.PropertyReader;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;


@Log4j2
@Listeners(TestListener.class)
public abstract class BaseTest {
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
        Configuration.browser = "chrome";
        var options = new ChromeOptions();

        //TODO похоже, отключение нотификаций в браузере не работает
        // https://github.com/selenide/selenide/issues/903

//        options.addArguments("--disable-features=site-per-process");
//        options.addArguments("--disable-infobars");
        options.addArguments("--disable-notifications");
        Configuration.browserCapabilities = options;
        open();
        getWebDriver().manage().window().maximize();

        loginPage = new LoginPage();
        homePage = new HomePage();
        accountDetailsPage = new AccountDetailsPage();
        accountListViewPage = new AccountListViewPage();
        accountModalPage = new AccountModalPage();
    }

    @AfterClass(alwaysRun = true, description = "Close browser")
    public void tearDown() {
        getWebDriver().quit();
    }
}