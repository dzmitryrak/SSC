package com.itechart.models.factory;

import com.github.javafaker.Faker;
import com.itechart.utils.PropertyReader;

public class BaseFactory {
    protected PropertyReader propertyReader = new PropertyReader("src/test/resources/configuration.properties");
    protected Faker faker = new Faker();
}
