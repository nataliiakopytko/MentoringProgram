package com.wikipedia.pages.components;

import com.wikipedia.core.Browser;
import com.wikipedia.core.TimeOutConstants;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class Coordinates implements BaseElement {
    private static final String LATITUDE_XPATH = "//span[@id='coordinates']//span[@class='latitude']";
    private static final String LONGITUDE_XPATH = "//span[@id='coordinates']//span[@class='longitude']";

    public WebElement getLatitude() {
        return Browser.getDriver().findElement(getLatitudeXpath());
    }

    public WebElement getLongitude() {
        return Browser.getDriver().findElement(getLongitudeXpath());
    }

    public String getFullCoordinates() {
        return getLatitude().getText() + getLongitude().getText();
    }

    @Override
    public void waitForDisplay() {
        Browser.waiter().waitForElementDisplayed(getLatitude(), TimeOutConstants.DEFAULT_TIMEOUT_5_000_MS);
        Browser.waiter().waitForElementDisplayed(getLongitude(), TimeOutConstants.DEFAULT_TIMEOUT_5_000_MS);
    }

    private By getLatitudeXpath() {
        return By.xpath(LATITUDE_XPATH);
    }

    private By getLongitudeXpath() {
        return By.xpath(LONGITUDE_XPATH);
    }
}
