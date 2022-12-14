package io.github.dzmitryrak.utils;

import lombok.extern.log4j.Log4j2;

import static com.codeborne.selenide.Selenide.screenshot;

@Log4j2
public class SalesforceElementNotFoundException extends RuntimeException {
    public SalesforceElementNotFoundException(String errorMessage) {
        super(errorMessage);
        log.warn("Unable to find element.");
        log.debug("Error message: {}", errorMessage);
        screenshot("SF Exception State " + errorMessage);
    }

    public SalesforceElementNotFoundException(String errorMessage, Throwable err) {
        super(errorMessage, err);
        log.warn("Unable to find element.");
        log.debug("Custom error message: {}", errorMessage);
        log.debug("System error message: {}", err.getMessage());
        log.debug(err.getStackTrace());
        screenshot("SF Exception State " + errorMessage);
    }

    public SalesforceElementNotFoundException(Throwable err) {
        super(err);
        log.warn("Unable to find element.");
        log.debug("System error message: {}", err.getMessage());
        log.debug(err.getStackTrace());
        screenshot("SF Exception State " + err.getMessage());
    }
}