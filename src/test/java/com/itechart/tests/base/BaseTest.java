package com.itechart.tests.base;

import com.codeborne.selenide.Configuration;
import com.itechart.pages.HomePage;
import com.itechart.pages.ListView;
import com.itechart.pages.LoginPage;
import com.itechart.pages.NewObjectModal;
import com.itechart.pages.account.AccountDetailsPage;
import com.itechart.pages.acierto.AciertoPage;
import com.itechart.pages.cases.CaseDetailsPage;
import com.itechart.utils.PropertyReader;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.*;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

@Log4j2
@Listeners(TestListener.class)
public abstract class BaseTest {
    protected LoginPage loginPage;
    protected HomePage homePage;
    protected CaseDetailsPage caseDetailsPage;
    protected ListView listView;
    protected NewObjectModal newObjectModal;
    protected AccountDetailsPage accountDetailsPage;
    protected AciertoPage aciertoPage;
    protected PropertyReader propertyReader = new PropertyReader("src/test/resources/configuration.properties");
    protected final String USERNAME = System.getProperty("username", propertyReader.getPropertyValueByKey("username"));
    protected final String PASSWORD = System.getProperty("password", propertyReader.getPropertyValueByKey("password"));

    @BeforeMethod(description = "Open browser")
    public void setUp() {
        Configuration.baseUrl = propertyReader.getPropertyValueByKey("base.url");
        Configuration.timeout = 5000;
        Configuration.browser = "chrome";
        var options = new ChromeOptions();
        options.addArguments("--disable-notifications");
        if(propertyReader.getPropertyValueByKey("headless").equals("true")) {
            options.addArguments("--headless");
        }
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        Configuration.browserCapabilities = options;
        open();
        getWebDriver().manage().window().maximize();

        loginPage = new LoginPage();
        homePage = new HomePage();
        accountDetailsPage = new AccountDetailsPage();
        listView = new ListView();
        newObjectModal = new NewObjectModal();
        caseDetailsPage = new CaseDetailsPage();
        aciertoPage = new AciertoPage();
    }

    @AfterMethod(alwaysRun = true, description = "Close browser")
    public void tearDown() {
        getWebDriver().quit();
    }
}