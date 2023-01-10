package io.github.dzmitryrak.tests;

import io.github.dzmitryrak.tests.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ReadModalErrorTest extends BaseTest {
    @Test(description = "Check Error Message")
    public void testErrorMessage() {
        loginPage.open();
        loginPage.login(USERNAME, PASSWORD);
        homePage.isPageOpened();
        String errorMessage =
                listView
                        .open("Contact")
                        .actions()
                        .newObject()
                        .enterData("Birthdate", "tet")
                        .save()
                        .getError();
        Assert.assertEquals(errorMessage, "Review the following fields\n" + "Name\n" +
                "Birthdate");
    }
}

