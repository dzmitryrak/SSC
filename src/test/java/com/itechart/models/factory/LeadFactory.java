package com.itechart.models.factory;

import com.itechart.models.Lead;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class LeadFactory extends BaseFactory {
    public Lead createNewLead() {
        return new Lead("Open - Not Contacted",
                "Mr.",
                faker.name().firstName(),
                faker.name().lastName(),
                faker.harryPotter().character(),
                faker.internet().emailAddress(),
                faker.phoneNumber().phoneNumber(),
                faker.phoneNumber().phoneNumber(),
                "Cold",
                faker.internet().url(),
                faker.company().name(),
                "Banking",
                faker.number().digit(),
                "Web",
                faker.address().streetAddress(),
                faker.address().city(),
                faker.address().country());
    }
}
