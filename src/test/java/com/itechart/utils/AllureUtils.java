package com.itechart.utils;

import io.qameta.allure.Attachment;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

@Log4j2
public class AllureUtils {
    @Attachment(value = "screenshot", type = "image/png")
    public static byte[] takeScreenshot() {
        try {
            return ((TakesScreenshot) getWebDriver()).getScreenshotAs(OutputType.BYTES);
        } catch (IllegalStateException ex) {
            log.warn("Unable to take screenshot. Make sure that driver is initialized");
            log.warn(ex.getMessage());
            log.debug(ex.getStackTrace());
            return null;
        }
    }
}