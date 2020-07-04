package com.wikipedia.pages.components;

import com.wikipedia.core.browser.Browser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static com.wikipedia.core.TimeOutConstants.DEFAULT_TIMEOUT_5_000_MS;

public class Coordinates implements BaseElement {

    //<editor-fold desc="Locators">
    private static final String COORDINATES_XPATH = "//span[@id='coordinates']//a[@class='external text']";
    //</editor-fold>

    //<editor-fold desc="Public Methods">
    public String getFullCoordinates() {
        return getCoordinates().getText();
    }

    private WebElement getCoordinates() {
        return Browser.getDriver().findElement(geCoordinatesXpath());
    }

    @Override
    public void waitForDisplay() {
        Browser.waiter().waitForElementDisplayed(getCoordinates(), DEFAULT_TIMEOUT_5_000_MS());
    }
    //</editor-fold>

    //<editor-fold desc="Private Methods">
    private By geCoordinatesXpath() {
        return By.xpath(COORDINATES_XPATH);
    }
    //</editor-fold>
}