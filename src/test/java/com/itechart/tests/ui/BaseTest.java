package com.itechart.tests.ui;

import com.itechart.models.factory.AccountFactory;
import com.itechart.models.factory.ContactFactory;
import com.itechart.models.factory.LeadFactory;
import com.itechart.pages.HomePage;
import com.itechart.pages.LoginPage;
import com.itechart.steps.AccountSteps;
import com.itechart.steps.ContactSteps;
import com.itechart.steps.LeadSteps;
import com.itechart.steps.LoginSteps;
import com.itechart.configurations.TestListener;
import com.itechart.utils.PropertyReader;
import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import java.util.concurrent.TimeUnit;

@Log4j2
@Listeners(TestListener.class)
public abstract class BaseTest {
    protected WebDriver driver;
    protected LoginSteps loginSteps;
    protected AccountSteps accountSteps;
    protected ContactSteps contactSteps;
    protected LeadSteps leadSteps;
    protected LoginPage loginPage;
    protected HomePage homePage;
    protected AccountFactory accountFactory = new AccountFactory();
    protected ContactFactory contactFactory = new ContactFactory();
    protected LeadFactory leadFactory = new LeadFactory();
    protected PropertyReader propertyReader = new PropertyReader("src/test/resources/configuration.properties");
    protected final String USERNAME = propertyReader.getPropertyValueByKey("username");
    protected final String PASSWORD = propertyReader.getPropertyValueByKey("password");

    @BeforeClass(description = "Open browser")
    public void setUp(ITestContext iTestContext) {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        //TODO not working
        //options.addArguments("--start-maximized");
        options.addArguments("--disable-notifications");
        //options.addArguments("--headless");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        iTestContext.setAttribute("driver", driver);
        loginPage = new LoginPage(driver);
        loginSteps = new LoginSteps(driver);
        accountSteps = new AccountSteps(driver);
        contactSteps = new ContactSteps(driver);
        leadSteps = new LeadSteps(driver);
    }

    public void openHomePage() {
        homePage.open();
    }

    @AfterClass(alwaysRun = true, description = "Close browser")
    public void tearDown() {
        if (driver != null)
            driver.quit();
    }
}
