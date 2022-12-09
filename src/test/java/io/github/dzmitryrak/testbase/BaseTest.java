package io.github.dzmitryrak.testbase;

import com.codeborne.selenide.Configuration;
import io.github.dzmitryrak.pages.*;
import io.github.dzmitryrak.utils.PropertyReader;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

@Log4j2
@Listeners(TestListener.class)
public abstract class BaseTest {
    protected LoginPage loginPage;
    protected HomePage homePage;
    protected DetailsPage detailsPage;
    protected ListView listView;
    protected NewObjectModal newObjectModal;
    protected PropertyReader propertyReader = new PropertyReader("src/test/resources/configuration.properties");
    protected final String USERNAME = System.getProperty("username", propertyReader.getPropertyValueByKey("username"));
    protected final String PASSWORD = System.getProperty("password", propertyReader.getPropertyValueByKey("password"));

    @BeforeMethod(description = "Open browser")
    public void setUp() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide()
                .screenshots(true)
                .includeSelenideSteps(false)
                .savePageSource(false));
        Configuration.baseUrl = propertyReader.getPropertyValueByKey("base.url");
        Configuration.timeout = 5000;
        Configuration.browser = "chrome";

        var options = new ChromeOptions();
        options.addArguments("--disable-notifications");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        if (propertyReader.getPropertyValueByKey("headless").equals("true")) {
            options.addArguments("--headless");
        }

        Configuration.browserCapabilities = options;
        open();
        getWebDriver().manage().window().maximize();

        loginPage = new LoginPage();
        homePage = new HomePage();
        detailsPage = new DetailsPage();
        listView = new ListView();
        newObjectModal = new NewObjectModal();
    }

    @AfterMethod(alwaysRun = true, description = "Close browser")
    public void tearDown() {
        try {
            getWebDriver().quit();
        } catch (IllegalStateException ex) {
            log.warn("Unable to close WebDriver. Make sure that driver is initialized");
            log.warn(ex.getMessage());
            log.debug(ex.getStackTrace());
        }
    }
}