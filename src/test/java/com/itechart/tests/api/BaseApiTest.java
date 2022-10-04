package com.itechart.tests.api;

import com.itechart.models.factory.AccountFactory;
import com.itechart.models.factory.ContactFactory;
import com.itechart.models.factory.LeadFactory;
import com.itechart.adapters.AccountAdapter;
import com.itechart.adapters.ContactAdapter;
import com.itechart.adapters.LeadAdapter;
import com.itechart.configurations.TestListener;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Listeners;

@Log4j2
@Listeners(TestListener.class)
public class BaseApiTest {
    protected WebDriver driver;
    protected AccountAdapter accountAdapter = new AccountAdapter();
    protected ContactAdapter contactAdapter = new ContactAdapter();
    protected LeadAdapter leadAdapter = new LeadAdapter();
    protected AccountFactory accountFactory = new AccountFactory();
    protected ContactFactory contactFactory = new ContactFactory();
    protected LeadFactory leadFactory = new LeadFactory();
}
