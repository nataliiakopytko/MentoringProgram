package com.wikipedia.core.context;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class Context {
    private static final Logger logger = LoggerFactory.getLogger(Context.class);
    private static InheritableThreadLocal<Map<String, Object>> projectContext = new InheritableThreadLocal<>();

    public static Object getValue(Enum<?> key) {
        return getValue(key.toString());
    }

    public static void setValue(Enum<?> key, Object value) {
        setValue(key.toString(), value);
    }

    public static void initContext() {
        projectContext.set(new HashMap<>());
    }

    public static void destroyContext() {
        projectContext.remove();
    }

    private static Object getValue(String key) {
        Object value = projectContext.get().get(key);
        logger.info(String.format("Retrieving value for key '%s': %n%s", key, value.toString()));
        return value;
    }

    private static void setValue(String key, Object value) {
        projectContext.get().put(key, value);
        logger.info(String.format("Saving value for key '%s': %n%s", key, value.toString()));
    }
}
