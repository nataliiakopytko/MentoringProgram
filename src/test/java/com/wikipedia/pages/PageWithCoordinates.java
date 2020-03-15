package com.wikipedia.pages;

import com.wikipedia.pages.components.Coordinates;
import lombok.Getter;

@Getter
public class PageWithCoordinates extends BasePage {
    public static final String PAGE_IDENTIFIER = "Coordinates";
    private Coordinates coordinates = new Coordinates();

    @Override
    public boolean verify() {
        return getPageHeader().isDisplayed();
    }

    @Override
    public void waitForPageLoaded() {
        getCoordinates().waitForDisplay();
    }
}
