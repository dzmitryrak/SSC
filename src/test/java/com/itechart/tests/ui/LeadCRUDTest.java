package com.itechart.tests.ui;

import com.itechart.models.Lead;
import com.itechart.configurations.Retry;
import org.testng.annotations.Test;

public class LeadCRUDTest extends BaseTest {
    @Test(retryAnalyzer = Retry.class, description = "Create Read Update Delete Lead record")
    public void createReadUpdateDeleteLeadRecord() {
        Lead lead = leadFactory.createNewLead();
        Lead updatedLead = leadFactory.createNewLead();
        loginSteps.login(USERNAME, PASSWORD);
        leadSteps
                .openLeadListViewPage()
                .create(lead)
                .validate(lead)
                .edit(updatedLead)
                .validate(updatedLead)
                .delete();
    }

    //TODO fix
/*                .edit(updatedAccount)
                .validate(updatedAccount)
                .delete();*/
}
