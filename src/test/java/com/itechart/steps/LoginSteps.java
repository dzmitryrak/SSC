package com.itechart.steps;

import com.itechart.pages.LoginPage;
import com.itechart.tests.ui.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class LoginSteps extends BaseTest {
    private final WebDriver driver;

    public LoginSteps(WebDriver driver) {
        this.driver = driver;
    }

    public LoginSteps login(String username, String password) {
        loginPage = new LoginPage(driver);
        homePage = loginPage
                .open()
                .login(username, password);
        homePage.isPageOpened();
        return this;
    }

    public LoginSteps checkUserCorrectLogin(){
        Assert.assertTrue(homePage.isPageOpened(), "Login is not completed");
        return this;
    }
    public LoginSteps checkUsernameIsRequired(){
        Assert.assertTrue(loginPage.isUsernameDisplayed(), "Username validation is not working.");
        return this;
    }
    public LoginSteps checkPasswordIsRequired(){
        Assert.assertEquals(loginPage.getErrorMessage(), "Please enter your password.",
                "Password validation is not working.");
        return this;
    }
}
