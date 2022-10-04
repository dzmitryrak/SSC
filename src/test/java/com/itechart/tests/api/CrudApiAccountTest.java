package com.itechart.tests.api;

import com.itechart.models.Account;
import com.itechart.models.ResponseStatus;
import com.itechart.configurations.Retry;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CrudApiAccountTest extends BaseApiTest {

    @Test(retryAnalyzer = Retry.class, description = "CRUD API Account")
    public void createGetUpdateDeleteAccount() {
        Account account = accountFactory.createNewAccount(false);
        ResponseStatus response = accountAdapter.create(account);
        Assert.assertTrue(response.isSuccess(), "Create Response is not correct");
        Account receivedAccount = accountAdapter.get(response.getId());
        Assert.assertEquals(receivedAccount.getAccountName(), account.getAccountName(), "Get Response is not correct");
        account = accountFactory.createNewAccount(false);
        accountAdapter.update(account, response.getId());
        receivedAccount = accountAdapter.get(response.getId());
        Assert.assertEquals(receivedAccount.getAccountName(), account.getAccountName(), "Update request is not correct");
        accountAdapter.delete(response.getId());
    }
}
