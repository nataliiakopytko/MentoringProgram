package com.wikipedia.pages.components.calendar;

import com.wikipedia.pages.EventsPage;
import com.wikipedia.pages.MainPage;

public class CalendarFactory {
    public static Calendar getCalendar(String pageIdentifier) {
        switch (pageIdentifier) {
            case EventsPage.PAGE_IDENTIFIER:
                return new WikiCalendar();
            case MainPage.PAGE_IDENTIFIER:
                return new MainPageCalendar();
            default:
                throw new IllegalArgumentException(String.format("Page '%s' doesn't have calendar", pageIdentifier));
        }
    }
}
