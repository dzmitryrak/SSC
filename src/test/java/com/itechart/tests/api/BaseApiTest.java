package com.itechart.tests.api;

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
}
