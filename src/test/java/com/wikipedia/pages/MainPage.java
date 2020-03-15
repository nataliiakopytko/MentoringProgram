package com.wikipedia.pages;

import com.wikipedia.core.Browser;
import com.wikipedia.core.TimeOutConstants;
import hometask2.PropertiesLoader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class MainPage extends BasePage {
    public static final String PAGE_IDENTIFIER = "Main";
    private static final String PAGE_URL = PropertiesLoader.getBaseUrl() + "/Main_Page";

    private static final String WELCOME_BANNER_XPATH = "//div[@id='mp-topbanner']//a[@title='Wikipedia']";
    private static final String SECTION_XPATH = "//span[@class='mw-headline'][text()='%s']/ancestor::h2/following-sibling::div/p[1]/b[1]/a";

    public void open() {
        Browser.getDriver().navigate().to(PAGE_URL);
    }

    @Override
    public boolean verify() {
        return getWelcomeBanner().isDisplayed()
                && Browser.getDriver().getCurrentUrl().equals(PAGE_URL);
    }

    @Override
    public void waitForPageLoaded() {
        Browser.waiter().waitForElementDisplayed(getWelcomeBanner(), TimeOutConstants.DEFAULT_TIMEOUT_5_000_MS);
    }

    public WebElement getWelcomeBanner() {
        return Browser.getDriver().findElement(getWelcomeBannerXpath());
    }

    public WebElement getSection(String sectionName) {
        return Browser.getDriver().findElement(getSectionXpath(sectionName));
    }

    //<editor-fold desc="Private Methods">
    private By getWelcomeBannerXpath() {
        return By.xpath(WELCOME_BANNER_XPATH);
    }

    private By getSectionXpath(String sectionName) {
        String xpath = String.format(SECTION_XPATH, sectionName);
        return By.xpath(xpath);
    }
    //</editor-fold>
}