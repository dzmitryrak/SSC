package io.github.dzmitryrak.tests;

import com.github.javafaker.Faker;
import io.github.dzmitryrak.tests.base.BaseTest;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

public class TestClass extends BaseTest {
    @Test(description = "Check Error Message")
    public void testErrorMessage() {
        Faker faker = new Faker();
        Map<String, String> contact = new HashMap<>() {{
//            put("Last Name", faker.name().lastName());
//            put("Salutation", "Prof.");
//            put("First Name", faker.name().name());
            put("Birthdate", "tet");
        }};
        loginPage.open();
        loginPage.login(USERNAME, PASSWORD);
        homePage.isPageOpened();
        listView
                .open("Contact")
                .actions()
                .newObject()
                .enterData(contact)
                .save()
                .readErrorMessage();
    }
}
