package com.wikipedia.pages;

import com.wikipedia.core.browser.Browser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public abstract class BasePage {
    //<editor-fold desc="Locators">
    private static final String HEADER_XPATH = "//h1[@id='firstHeading']";
    //</editor-fold>

    //<editor-fold desc="Public Methods">
    public abstract boolean verify();

    public abstract void waitForPageLoaded();

    public WebElement getPageHeader() {
        return Browser.getDriver().findElement(getHeaderXpath());
    }

    public String getTransformedUrlFromHeader(String pageUrl) {
        return String.format(pageUrl, getPageHeader().getText().replace(" ", "_"));
    }
    //</editor-fold>

    //<editor-fold desc="Private Methods">
    private By getHeaderXpath() {
        return By.xpath(HEADER_XPATH);
    }
    //</editor-fold>
}
