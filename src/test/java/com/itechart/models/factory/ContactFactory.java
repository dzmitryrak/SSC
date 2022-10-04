package com.itechart.models.factory;

import com.itechart.models.Contact;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class ContactFactory extends BaseFactory {
    public Contact createNewContact(boolean isUiTest) {
        if (isUiTest) {
            return new Contact("Mr.",
                    faker.name().firstName(),
                    faker.name().lastName(),
                    faker.gameOfThrones().character(),
                    faker.internet().emailAddress(),
                    faker.harryPotter().house(),
                    faker.number().digit(),
                    faker.phoneNumber().phoneNumber(),
                    faker.phoneNumber().phoneNumber());
        } else {
            return new Contact("Mr.",
                    faker.name().firstName(),
                    faker.name().lastName(),
                    faker.gameOfThrones().character(),
                    faker.internet().emailAddress(),
                    faker.harryPotter().house(),
                    faker.number().digit(),
                    faker.phoneNumber().phoneNumber(),
                    faker.phoneNumber().phoneNumber());
        }
    }
}
