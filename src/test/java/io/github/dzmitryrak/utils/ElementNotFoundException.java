package com.itechart.utils;

public class ElementNotFoundException extends RuntimeException {
    public ElementNotFoundException(String errorMessage) {
        super(errorMessage);
    }
    public ElementNotFoundException(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }
}
