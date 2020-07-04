package com.wikipedia.pages;

import com.hometask.hometask2.PropertiesLoader;
import com.wikipedia.core.browser.Browser;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.stream.Collectors;

import static com.wikipedia.core.TimeOutConstants.DEFAULT_TIMEOUT_5_000_MS;

public class EventsPage extends BasePage {
    public static final String PAGE_IDENTIFIER = "Events";
    private static final String PAGE_URL = PropertiesLoader.getBaseUrl() + "/%s";
    private static final Logger logger = LoggerFactory.getLogger(EventsPage.class);

    //<editor-fold desc="Locators">
    private static final String EVENTS_LINKS_XPATH = "//span[@id='Events']/ancestor::h2/following-sibling::ul/li/a";
    private static final String GEOLOCATIONS_XPATH = "//span[@id='Events']/ancestor::h2/following-sibling::ul//a[text()='%s']";
    //</editor-fold>

    //<editor-fold desc="Public Methods">
    @Override
    public boolean verify() {
        return Browser.getDriver().getCurrentUrl().equals(getTransformedUrlFromHeader(PAGE_URL));
    }

    @Override
    public void waitForPageLoaded() {
        Browser.waiter().waitForElementDisplayed(getPageHeader(), DEFAULT_TIMEOUT_5_000_MS());
    }

    public WebElement getGeolocation(String locationName) {
        try {
            return Browser.getDriver().findElement(getGeolocationsXpath(locationName));
        } catch (NoSuchElementException e) {
            return null;
        }
    }

    public List<String> getEventsList() {
        logger.info("Collecting events to list ...");
        List<WebElement> elements = Browser.getDriver().findElements(getLinksXpath());
        return elements.stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }
    //</editor-fold>

    //<editor-fold desc="Private Methods">
    private By getGeolocationsXpath(String locationName) {
        return By.xpath(String.format(GEOLOCATIONS_XPATH, locationName));
    }

    private By getLinksXpath() {
        return By.xpath(EVENTS_LINKS_XPATH);
    }
    //</editor-fold>
}