package com.wikipedia.pages;

import java.util.HashMap;
import java.util.Map;

public class PageFactory {
    private static final ThreadLocal<Map<String, Object>> pageMapper = new ThreadLocal<>();

    //<editor-fold desc="Public Methods">
    public static BasePage getPageByIdentifier(String identifier) {
        Object obj = getObjectByClassIdentifier(identifier);
        if (obj instanceof BasePage) {
            return (BasePage) obj;
        } else {
            throw new IllegalArgumentException(String.format("Page '%s' does not extend class BasePage", obj.getClass().getName()));
        }
    }
    //</editor-fold>

    //<editor-fold desc="Private Methods">
    private static Object getObjectByClassIdentifier(String classIdentifier) {
        if (pageMapper.get() == null) {
            pageMapper.set(new HashMap<>());
        }
        return pageMapper.get().computeIfAbsent(classIdentifier, PageFactory::initPage);
    }

    private static Object initPage(String identifier) {
        switch (identifier) {
            case MainPage.PAGE_IDENTIFIER:
                return new MainPage();
            case EventsPage.PAGE_IDENTIFIER:
                return new EventsPage();
            case PageWithCoordinates.PAGE_IDENTIFIER:
                return new PageWithCoordinates();
            default:
                throw new IllegalArgumentException(String.format("Page identifier '%s' is not found", identifier));
        }
    }
    //</editor-fold>
}