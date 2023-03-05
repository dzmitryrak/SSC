package io.github.dzmitryrak.tests;

import com.github.javafaker.Faker;
import io.github.dzmitryrak.pages.NewObjectModal;
import io.github.dzmitryrak.tests.base.BaseTest;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

public class SpanishLanguageTest extends BaseTest {

    Faker faker = new Faker();

    @Test(description = "Account CRUD test in a different language (Spanish)")
    public void createAccountEditAndDeleteInSpanish() {
        Map<String, String> account = new HashMap<>() {{
            put("Nombre de la cuenta", faker.name().name());
            put("Cuenta principal", "Pablo Picasso");
            put("Tipo", "Prospect");
            put("Sitio Web", faker.internet().url());
            put("Teléfono", faker.phoneNumber().phoneNumber());
            put("Descripción", faker.lorem().sentence());
            put("Multiselect", "Yes;Probably;one more option");
            put("Empleados", faker.number().digit());
            put("Calle de facturación", faker.address().streetAddress());
            put("Ciudad de facturación", faker.address().city());
            put("Estado o provincia de facturación", faker.address().state());
            put("Código postal de facturación", faker.address().zipCode());
            put("País de facturación", faker.address().country());
            put("Calle de envío", faker.address().streetAddress());
            put("Ciudad de envío", faker.address().city());
            put("Estado o provincia de envío", faker.address().state());
            put("Código postal de envío", faker.address().zipCode());
            put("País de envío", faker.address().country());
        }};

        Map<String, String> updatedAccount = new HashMap<>() {{
            put("Nombre de la cuenta", faker.name().name());
            put("Cuenta principal", "Pablo Picasso");
            put("Tipo", "Prospect");
            put("Sitio Web", faker.internet().url());
            put("Teléfono", faker.phoneNumber().phoneNumber());
            put("Descripción", faker.lorem().sentence());
            put("Multiselect", "Yes;Probably;one more option");
            put("Empleados", faker.number().digit());
            put("Calle de facturación", faker.address().streetAddress());
            put("Ciudad de facturación", faker.address().city());
            put("Estado o provincia de facturación", faker.address().state());
            put("Código postal de facturación", faker.address().zipCode());
            put("País de facturación", faker.address().country());
            put("Calle de envío", faker.address().streetAddress());
            put("Ciudad de envío", faker.address().city());
            put("Estado o provincia de envío", faker.address().state());
            put("Código postal de envío", faker.address().zipCode());
            put("País de envío", faker.address().country());
        }};

        loginPage.open();
        loginPage.login(SPAIN_USER, PASSWORD);
        listView
                .open("Account")
                .actions().action("Nuevo");
        NewObjectModal editModal = new NewObjectModal();
        editModal.waitTillOpened();
        editModal
                .enterData(account)
                .save()
                .waitTillModalClosed()
                .waitTillOpened();
        account.remove("Calle de facturación");
        account.remove("Ciudad de facturación");
        account.remove("Estado o provincia de facturación");
        account.remove("Código postal de facturación");
        account.remove("País de facturación");
        account.remove("Calle de envío");
        account.remove("Ciudad de envío");
        account.remove("Estado o provincia de envío");
        account.remove("Código postal de envío");
        account.remove("País de envío");

        detailsPage
                .clickTab("Detalles")
                .validate(account);
        detailsPage
                .actions()
                .moreActions().action("Modificar");
        editModal.waitTillOpened()
                .clearData(updatedAccount)
                .enterData(updatedAccount)
                .save()
                .waitTillModalClosed()
                .waitTillOpened();

        updatedAccount.remove("Calle de facturación");
        updatedAccount.remove("Ciudad de facturación");
        updatedAccount.remove("Estado o provincia de facturación");
        updatedAccount.remove("Código postal de facturación");
        updatedAccount.remove("País de facturación");
        updatedAccount.remove("Calle de envío");
        updatedAccount.remove("Ciudad de envío");
        updatedAccount.remove("Estado o provincia de envío");
        updatedAccount.remove("Código postal de envío");
        updatedAccount.remove("País de envío");

        detailsPage
                .clickTab("Detalles")
                .validate(updatedAccount);
        detailsPage
                .actions()
                .moreActions()
                .action("Eliminar");
    }
}
