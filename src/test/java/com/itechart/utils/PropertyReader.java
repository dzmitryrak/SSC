package com.itechart.utils;

import lombok.extern.log4j.Log4j2;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

@Log4j2
public class PropertyReader {

    private final Properties properties = new Properties();
    String propertyFile;

    public PropertyReader(String filepath) {
        try {
            log.info("Reading property from file: {}", filepath);
            FileInputStream fileInputStream = new FileInputStream(filepath);
            properties.load(fileInputStream);
            propertyFile = filepath;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getPropertyValueByKey(String key) {
        log.info("Reading property by key: {}", key);
        if (properties.getProperty(key) != null) {
            return properties.getProperty(key);
        } else {
            String errorMessage = String.format("Cannot find property by key: %s. Make sure that it exists inside %s", key, propertyFile);
            log.error(errorMessage);
            throw new RuntimeException(errorMessage);
        }
    }
}