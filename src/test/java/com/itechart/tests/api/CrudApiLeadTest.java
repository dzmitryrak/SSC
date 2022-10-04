package com.itechart.tests.api;

import com.itechart.models.Lead;
import com.itechart.models.ResponseStatus;
import com.itechart.configurations.Retry;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CrudApiLeadTest extends BaseApiTest {

    @Test(retryAnalyzer = Retry.class, description = "CRUD API Lead")
    public void createGetUpdateDeleteLead() {
        Lead lead = leadFactory.createNewLead();
        ResponseStatus response = leadAdapter.create(lead);
        Assert.assertTrue(response.isSuccess(), "Response is not correct");
        Lead receivedLead = leadAdapter.get(response.getId());
        Assert.assertEquals(receivedLead.getName(), lead.getName(), "Response is not correct");
        lead = leadFactory.createNewLead();
        leadAdapter.update(lead, response.getId());
        receivedLead = leadAdapter.get(response.getId());
        Assert.assertEquals(receivedLead.getName(), lead.getName(), "Update request is not correct");
        leadAdapter.delete(response.getId());
    }
}
