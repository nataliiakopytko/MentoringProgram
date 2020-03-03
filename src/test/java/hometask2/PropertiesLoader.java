package hometask2;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesLoader {
    private static final String pathToPropertyFile = "src/test/resources/task.properties";

    public static String getProperty(String key) {
        return getValuePipeline(key);
    }

    public static Properties getPropertyFile() {
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream(pathToPropertyFile));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }

    private static String getPropertyValueFromFile(String key) {
        return getPropertyFile().getProperty(key);
    }

    private static String getValuePipeline(String key) {
        String localProperty = getPropertyValueFromFile(key);
        if (localProperty != null) {
            return localProperty;
        }
        return "Property Value is not defined";
    }
}
