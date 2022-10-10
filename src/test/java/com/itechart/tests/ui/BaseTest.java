package com.itechart.tests.ui;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;
import com.itechart.pages.HomePage;
import com.itechart.pages.LoginPage;
import com.itechart.pages.account.AccountDetailsPage;
import com.itechart.pages.account.AccountListViewPage;
import com.itechart.pages.account.AccountModalPage;
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
    protected LeadSteps leadSteps;
    protected LoginPage loginPage;
    protected HomePage homePage;
    protected AccountListViewPage accountListViewPage;
    protected AccountModalPage accountModalPage;
    protected AccountDetailsPage accountDetailsPage;
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
        loginPage = new LoginPage();
        homePage = new HomePage();
        accountDetailsPage = new AccountDetailsPage(driver);
        accountListViewPage = new AccountListViewPage(driver);
        accountModalPage = new AccountModalPage();
        loginSteps = new LoginSteps();
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

/*
названия брачный по примеру: feature/ITA-1-removal-of-steps
названия комитов по примеру: ITA-1 removed utils
 */