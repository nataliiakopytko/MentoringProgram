package com.hometask.hometask2;

import java.util.Map;
import java.util.Properties;
import java.util.Set;

public class TestClass {
    public static void main(String[] args) throws KeyAlreadyExistsException {
        Properties properties = PropertiesLoader.getPropertyFile(PropertiesLoader.pathToPropertyFile);
        Set<Integer> keys = Helper.getSetOfKeys(properties);

        Map<Integer, String> mapFromProperties = Helper.getMapFromProperties(keys);
        System.out.println(mapFromProperties);

        Map<String, Integer> reversedMap = Helper.getReversedMap(mapFromProperties);
        System.out.println(reversedMap);

        Helper.writeToFile(reversedMap);
    }
}