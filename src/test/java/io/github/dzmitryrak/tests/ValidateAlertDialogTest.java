package io.github.dzmitryrak.tests;

import io.github.dzmitryrak.tests.base.BaseTest;
import org.testng.annotations.Test;
import static org.testng.Assert.assertTrue;

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
        assertTrue(alertMessage.contains("Success"));
    }
}
