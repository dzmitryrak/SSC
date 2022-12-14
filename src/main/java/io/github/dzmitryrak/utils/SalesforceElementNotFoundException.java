package io.github.dzmitryrak.utils;

public class SalesforceElementNotFoundException extends RuntimeException {
    public SalesforceElementNotFoundException(String errorMessage) {
        super(errorMessage);
    }

    public SalesforceElementNotFoundException(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }
}