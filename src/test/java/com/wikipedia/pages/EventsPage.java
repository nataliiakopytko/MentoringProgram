package com.wikipedia.pages;

import com.wikipedia.core.Browser;
import com.wikipedia.pages.components.WikiCalendar;
import hometask2.PropertiesLoader;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

@Getter
public class EventsPage extends BasePage {
    public static final String PAGE_IDENTIFIER = "Events";
    private static final String PAGE_URL = PropertiesLoader.getBaseUrl() + "/%s";
    private static final String GEOLOCATIONS_XPATH = "//span[@id='Events']/ancestor::h2/following-sibling::ul//a[text()='%s']";

    private WikiCalendar wikiCalendar = new WikiCalendar();

    @Override
    public boolean verify() {
        return Browser.getDriver().getCurrentUrl().equals(getTransformedUrlFromHeader(PAGE_URL));
    }

    @Override
    public void waitForPageLoaded() {
        getWikiCalendar().waitForDisplay();
    }

    public WebElement getGeolocation(String locationName) {
        try {
            return Browser.getDriver().findElement(getGeolocationsXpath(locationName));
        } catch (NoSuchElementException e) {
            return null;
        }
    }

    private By getGeolocationsXpath(String locationName) {
        return By.xpath(String.format(GEOLOCATIONS_XPATH, locationName));
    }
}