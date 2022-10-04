package com.itechart.tests.ui;

import com.itechart.configurations.Retry;
import org.testng.annotations.Test;


public class LoginTest extends BaseTest {
    @Test(retryAnalyzer = Retry.class, description = "Login with correct credentials")
    public void loginTest() {
        loginSteps
                .login(USERNAME, PASSWORD)
                .checkUserCorrectLogin();
    }

    @Test(retryAnalyzer = Retry.class, description = "Login with incorrect username")
    public void usernameShouldBeRequired() {
        loginSteps
                .login("", PASSWORD)
                .checkUsernameIsRequired();
    }

    @Test(retryAnalyzer = Retry.class, description = "Login with incorrect password")
    public void passwordShouldBeRequired() {
        loginSteps
                .login(USERNAME, "")
                .checkPasswordIsRequired();
    }
}
