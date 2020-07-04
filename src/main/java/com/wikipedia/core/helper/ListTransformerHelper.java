package com.wikipedia.core.helper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.stream.Collectors;

public class ListTransformerHelper {
    private static final Logger logger = LoggerFactory.getLogger(ListTransformerHelper.class);

    //<editor-fold desc="Public Methods">
    public static List<String> getUiToApiTransformedEventsList(List<String> list) {
        logger.info("Transforming UI events list to API view ...");
        List<String> lOut = list.stream()
                .map(element -> element.replace(" ", "_")
                        .replace(",", "%2C"))
                .collect(Collectors.toList());
        logger.info(String.format("Transformed list: %s", lOut));
        return lOut;
    }

    public static List<String> getApiToUiTransformedEventsList(List<String> list) {
        logger.info("Transforming API events list to UI view ...");
        List<String> lOut = list.stream()
                .map(element -> element.replace("_", " ")
                        .replace("%2C", ","))
                .collect(Collectors.toList());
        logger.info(String.format("Transformed list: %s", lOut));
        return lOut;
    }
    //</editor-fold>
}
