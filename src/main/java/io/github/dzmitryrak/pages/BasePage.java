package io.github.dzmitryrak.pages;

import com.codeborne.selenide.Selenide;
import io.github.dzmitryrak.utils.ElementHelper;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

@Log4j2
public abstract class BasePage {
    /**
     * Timeout for pages' waitTillOpened methods.
     * The default is 20 seconds.
     *
     */
    public static Duration timeout = Duration.ofSeconds(20);
    private final By ALERT_DIALOG = By.xpath("//div[@role='alertdialog']");

    protected final String ACTIVE_TAB_LOCATOR = "//*[contains(@class,'windowViewMode') and contains(@class,'active')]";
    protected ElementHelper sfHelper;

    public BasePage() {
        sfHelper = new ElementHelper();
    }

    /**
     * Perform a js click on element.
     *
     * @param locator
     */
    @Step("Click on the element")
    protected void clickJS(By locator) {
        log.debug("JS click to element using locator {}", locator);
        Selenide.executeJavaScript("arguments[0].click();", $(locator));
    }

    /**
     * Get all alert messages on NewObjectModal.
     * log alert message
     * @return current instance of NewObjectModal
     */
    @Step("Get Alert Messages")
    public String getAlert() {
        $(ALERT_DIALOG).shouldBe(visible);
        String alertMessage = $(ALERT_DIALOG).shouldBe(visible).getText();
        log.info("Popup Alert Message: {}", alertMessage);
        return alertMessage;
    }
}