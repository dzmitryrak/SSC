package io.github.dzmitryrak.tests;

import io.github.dzmitryrak.constants.DetailsTabs;
import io.github.dzmitryrak.tests.base.BaseTest;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.open;

public class CaseTest extends BaseTest {

    //TODO replace by the real test Case CRUD + validation of panels
    @Test(description = "Check that panel could be validated")
    public void panelValidation() {
        loginPage.open().login(USERNAME, PASSWORD);
        open("https://tms41-dev-ed.lightning.force.com/lightning/r/Case/5005g00000UUxpWAAT/view");
        detailsPage
                .panels()
                .panel("Case Details")
                .validate("Subject", "Seeking guidance on electrical wiring installation for GC5060")
                .validate("Case Number", "00001002")
                .validate("Status", "New")
                .validate("Priority", "Low")
                .validate("Description", "");
        detailsPage
                .clickTab(DetailsTabs.Details)
                .waitTillOpened(DetailsTabs.Details)
                .validate("Subject", "Seeking guidance on electrical wiring installation for GC5060")
                .validate("Case Number", "00001002")
                .validate("Status", "New")
                .validate("Priority", "Low")
                .validate("Description", "");


    }
}
