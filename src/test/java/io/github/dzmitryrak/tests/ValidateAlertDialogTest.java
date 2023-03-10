package io.github.dzmitryrak.tests;

import io.github.dzmitryrak.pages.BasePage;
import io.github.dzmitryrak.tests.base.BaseTest;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.StringContains.containsString;

public class ValidateAlertDialogTest extends BaseTest {
    @Test(description = "Check Success alert")
    public void tableValidation() {
        loginPage.open().login(USERNAME, PASSWORD);
        String alertMessage = listView
                .open("Case")
                .actions()
                .newObject()
                .enterData("Case Origin", "Phone")
                .save()
                .getAlert();
        assertThat(alertMessage, containsString("Success"));
    }
}
