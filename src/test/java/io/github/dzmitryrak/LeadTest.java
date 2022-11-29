package io.github.dzmitryrak;

import com.github.javafaker.Faker;
import io.github.dzmitryrak.constants.DetailsTabs;
import io.github.dzmitryrak.testbase.BaseTest;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

public class LeadTest extends BaseTest {

    Faker faker = new Faker();

    @Test(description = "Create Lead record")
    public void createLead() {
        Map<String, String> lead = new HashMap<>() {{
            put("Salutation", "Mr.");
            put("First Name", faker.name().name());
            put("Last Name", faker.name().lastName());
            put("Phone", "+375232323");
            put("Mobile", "+1238238");
            put("Company", faker.company().name());
            put("Title", faker.company().profession());
            put("Lead Source", "Other");
            put("Industry", "Other");
            put("Annual Revenue", "200000000");
            put("Fax", faker.phoneNumber().cellPhone());
            put("Email", faker.internet().emailAddress());
            put("Website", faker.internet().url());
            put("Lead Status", "Open - Not Contacted");
            put("Rating", "Cold");
            put("Street", faker.address().streetAddress());
            put("City", faker.address().city());
            put("State/Province", "test");
            put("Product Interest", "GC3000 series");
            put("Primary", "Yes");
            put("Description", faker.lorem().sentence());
            put("SIC Code", "some code  ");
            put("Current Generator(s)", "3");
        }};

        loginPage.open();
        loginPage.login(USERNAME, PASSWORD);
        homePage.isPageOpened();
        listView
                .open("Lead")
                .clickNew()
                .enterData(lead)
                .save()
                .waitTillModalClosed()
                .waitTillOpened();
        lead.remove("Salutation");
        lead.remove("First Name");
        lead.remove("Last Name");
        lead.remove("State/Province");
        lead.remove("Street");
        lead.remove("City");
        lead.put("Annual Revenue", "$200,000,000");
        detailsPage.clickTab(DetailsTabs.Details);
        detailsPage.validate(lead);
    }
}