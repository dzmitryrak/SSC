package io.github.dzmitryrak.tests;

import io.github.dzmitryrak.tests.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

public class ReadModalErrorTest extends BaseTest {
    @Test(description = "Check Error Message")
    public void testErrorMessage() {
        Map<String, String> contact = new HashMap<>() {{
            put("Birthdate", "tet");
        }};
        loginPage.open();
        loginPage.login(USERNAME, PASSWORD);
        homePage.isPageOpened();
        String errorMessage =
                listView
                        .open("Contact")
                        .actions()
                        .newObject()
                        .enterData(contact)
                        .save()
                        .readErrorMessage();
        Assert.assertEquals(errorMessage, "Review the following fields\n" + "Name\n" +
                "Birthdate");
    }
}

