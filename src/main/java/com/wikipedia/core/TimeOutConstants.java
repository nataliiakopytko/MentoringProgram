package com.wikipedia.core;

import static com.hometask.hometask2.PropertiesLoader.getProperty;
import static com.hometask.hometask2.PropertiesLoader.pathToTimeoutPropertyFile;

public final class TimeOutConstants {
    private TimeOutConstants() {
    }

    public static int DEFAULT_TIMEOUT_5_000_MS() {
        return getTimeoutFromProperty("default.timeout.5000.ms");
    }

    public static int DEFAULT_TIMEOUT_10_000_MS() {
        return getTimeoutFromProperty("default.timeout.10000.ms");
    }

    public static int DEFAULT_POLLING_INTERVAL_500_MS() {
        return getTimeoutFromProperty("default.timeout.500.ms");
    }

    private static int getTimeoutFromProperty(String propertyName) {
        return Integer.parseInt(getProperty(pathToTimeoutPropertyFile, propertyName));
    }
}
