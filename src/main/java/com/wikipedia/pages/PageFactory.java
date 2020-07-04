package com.wikipedia.pages;

import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("unchecked")
public class PageFactory {
    private static final ThreadLocal<Map<String, Object>> pageMapper = new ThreadLocal<>();

    //<editor-fold desc="Public Methods">
    public static <T extends BasePage> T getPageByIdentifier(String identifier) {
        try {
            return (T) getObjectByClassIdentifier(identifier);
        } catch (Exception e) {
            throw new UnsupportedOperationException(String.format("Can't initialize page '%s' because of uncaught exception '%s'", identifier, e));
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