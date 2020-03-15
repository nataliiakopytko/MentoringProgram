package com.wikipedia.pages;

import com.wikipedia.core.Browser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public abstract class BasePage {
    private static final String HEADER_XPATH = "//h1[@id='firstHeading']";

    public abstract boolean verify();

    public abstract void waitForPageLoaded();

    public WebElement getPageHeader() {
        return Browser.getDriver().findElement(getHeaderXpath());
    }

    public String getTransformedUrlFromHeader(String pageUrl) {
        return String.format(pageUrl, getPageHeader().getText().replace(" ", "_"));
    }

    private By getHeaderXpath() {
        return By.xpath(HEADER_XPATH);
    }
}
