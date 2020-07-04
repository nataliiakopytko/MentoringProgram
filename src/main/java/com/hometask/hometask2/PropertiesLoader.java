package com.hometask.hometask2;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesLoader {
    public static final String pathToPropertyFile = "src/test/resources/properties/task.properties";
    public static final String pathToTestrailPropertyFile = "src/test/resources/properties/testrail.properties";
    public static final String pathToTimeoutPropertyFile = "src/main/resources/properties/timeout.properties";
    private static final String pathToBrowserPropertyFile = "src/test/resources/properties/browser.properties";
    private static final String pathToWikipediaPropertyFile = "src/main/resources/properties/wikipedia.properties";

    public static String getBaseUrl() {
        return getProperty(pathToWikipediaPropertyFile, "baseUrl");
    }

    public static String getBrowserName() {
        return getProperty(pathToBrowserPropertyFile, "browserName");
    }

    public static String getBrowserWidth() {
        return getProperty(pathToBrowserPropertyFile, "resolutionWidth");
    }

    public static String getBrowserHeight() {
        return getProperty(pathToBrowserPropertyFile, "resolutionHeight");
    }

    public static String getProperty(String pathToPropertyFile, String key) {
        return getValuePipeline(pathToPropertyFile, key);
    }

    public static Properties getPropertyFile(String pathToPropertyFile) {
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream(pathToPropertyFile));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }

    private static String getPropertyValueFromFile(String pathToPropertyFile, String key) {
        return getPropertyFile(pathToPropertyFile).getProperty(key);
    }

    private static String getValuePipeline(String pathToPropertyFile, String key) {
        String localProperty = getPropertyValueFromFile(pathToPropertyFile, key);
        if (localProperty != null) {
            return localProperty;
        }
        return "Property Value is not defined";
    }
}
