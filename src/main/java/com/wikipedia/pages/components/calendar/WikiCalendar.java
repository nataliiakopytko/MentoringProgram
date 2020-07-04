package com.wikipedia.pages.components.calendar;

import com.wikipedia.core.browser.Browser;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;

import java.util.List;

import static com.wikipedia.core.TimeOutConstants.DEFAULT_TIMEOUT_5_000_MS;

public class WikiCalendar implements Calendar {
    private static final String CALENDAR_XPATH = "//table[contains(@class,'floatright')]/tbody";
    private static final String MONTH_XPATH = CALENDAR_XPATH + "/tr[@class='navbox-title']/th/a";
    private static final String DAY_OF_WEEK_XPATH = CALENDAR_XPATH + "/tr[@class='header navbox-title']/th";
    private static final String DAY_XPATH = CALENDAR_XPATH + "/tr[not(@*)]//a";
    private static final String SELECTED_DAY_XPATH = CALENDAR_XPATH + "/tr[not(@*)]//a[contains(@class,'selflink')]";
    private static final String YEAR_XPATH = CALENDAR_XPATH + "/tr[@class='navbox-title']//b/a";
    private static final String CHANGE_MONTH_RIGHT_XPATH = CALENDAR_XPATH + "/tr[@class='navbox-title']//a[text()='>>']";

    @Override
    public WebElement getCalendarBase() {
        return Browser.getDriver().findElement(getCalendarXpath());
    }

    @Override
    public WebElement getButtonChangeMonth() {
        return Browser.getDriver().findElement(getChangeMonthXpath());
    }

    @Override
    public List<WebElement> getDaysOfMonth() {
        return Browser.getDriver().findElements(getDayXpath());
    }

    @Override
    public WebElement getSelectedDayOfMonth() {
        return Browser.getDriver().findElement(getSelectedDayXpath());
    }

    @Override
    public void waitForDisplay() {
        Browser.waiter().waitForElementDisplayed(getCalendarBase(), DEFAULT_TIMEOUT_5_000_MS());
    }

    @Override
    public void setDateWithOffset(int offsetWithSign) {
        DateTime dateTime = new DateTime().plusDays(offsetWithSign);
        setDate(dateTime);
    }

    private int getMonth() {
        WebElement element = Browser.getDriver().findElement(getMonthXpath());
        DateTime dateTime = DateTime.parse(element.getText(), DateTimeFormat.forPattern("MMMM"));
        return dateTime.getMonthOfYear();
    }

    private void setDate(DateTime dateTime) {
        selectMonth(dateTime);
        selectDay(dateTime);
    }

    private void selectDay(DateTime dateTime) {
        int day = dateTime.getDayOfMonth();
        getDaysOfMonth().stream()
                .filter(i -> i.getText().equals(String.valueOf(day)))
                .findFirst()
                .orElseThrow(() -> new WebDriverException(String.format("There is no '%s' day of month", day)))
                .click();
        Browser.waiter().waitForElementDisplayed(getCalendarBase());
    }

    private void selectMonth(DateTime dateTime) {
        int month = dateTime.getMonthOfYear();
        if (month != getMonth()) {
            int tries = 11;
            do {
                getButtonChangeMonth().click();
                Browser.waiter().waitForElementDisplayed(getCalendarBase());
                if (month == getMonth()) break;
            } while (tries-- > 0);
        }
    }

    private By getCalendarXpath() {
        return By.xpath(CALENDAR_XPATH);
    }

    private By getMonthXpath() {
        return By.xpath(MONTH_XPATH);
    }

    private By getChangeMonthXpath() {
        return By.xpath(CHANGE_MONTH_RIGHT_XPATH);
    }

    private By getDayXpath() {
        return By.xpath(DAY_XPATH);
    }

    private By getSelectedDayXpath() {
        return By.xpath(SELECTED_DAY_XPATH);
    }
}
