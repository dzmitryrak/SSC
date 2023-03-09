package io.github.dzmitryrak.pages;

import com.codeborne.selenide.Selenide;
import io.github.dzmitryrak.utils.ElementHelper;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$;

@Log4j2
public abstract class BasePage {
    /**
     * Timeout for pages' waitTillOpened methods.
     * The default is 20 seconds.
     *
     */
    public static Duration timeout = Duration.ofSeconds(20);

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
     * Wait for "document.readyState" to be "complete".
     */
    public void waitForPageLoaded() {
        new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                return Selenide.executeJavaScript("return document.readyState").toString().equals("complete");
            }
        };
    }
}