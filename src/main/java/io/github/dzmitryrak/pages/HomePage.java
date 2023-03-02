package io.github.dzmitryrak.pages;

import com.codeborne.selenide.Selenide;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

@Log4j2
public class HomePage extends BasePage {
    private final By LOGO_LOCATOR = By.xpath("//*[contains(@class, 'slds-page-header')]//ancestor::lightning-primitive-icon/*[@data-key='home']");

    /**
     * Check if main logo is displayed.
     */
    public HomePage waitTillOpened() {
        //TODO method to check if user is logged in
        $(LOGO_LOCATOR).shouldBe(visible, timeout);
        return this;
    }

    /**
     * Open Salesforce home page.
     *
     * @return current instance of HomePage
     */
    public HomePage open() {
        Selenide.open("/");
        return this;
    }
}


