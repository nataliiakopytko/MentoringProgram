package com.hometask.hometask2;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import static com.hometask.hometask2.PropertiesLoader.pathToPropertyFile;

class Helper {
    static Map<Integer, String> getMapFromProperties(Set<Integer> keys) {
        Map<Integer, String> map = new LinkedHashMap<>();
        keys.forEach(key -> {
            String value = PropertiesLoader.getProperty(pathToPropertyFile, key.toString());
            map.put(key, value);
        });
        return map;
    }

    static Map<String, Integer> getReversedMap(Map<Integer, String> map) throws KeyAlreadyExistsException {
        Map<String, Integer> reverseMap = new TreeMap<>();
        for (Map.Entry entry : map.entrySet()) {
            String value = entry.getValue().toString();
            if (reverseMap.containsKey(value)) {
                throw new KeyAlreadyExistsException(String.format("Key '%s' already exists", value));
            }
            reverseMap.put(value, Integer.parseInt(entry.getKey().toString()));
        }
        return reverseMap;
    }

    static Set<Integer> getSetOfKeys(Properties properties) {
        return properties.keySet().stream()
                .map(i -> Integer.valueOf(i.toString()))
                .collect(Collectors.toSet());
    }

    static void writeToFile(Map<String, Integer> map) {
        String fileToWritePath = "src/test/java/com.hometask.hometask2/files/file.txt";
        try (FileOutputStream fos = new FileOutputStream(fileToWritePath)) {
            byte[] buffer = map.toString().getBytes();
            fos.write(buffer, 0, buffer.length);
            System.out.println("File is ready");
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
