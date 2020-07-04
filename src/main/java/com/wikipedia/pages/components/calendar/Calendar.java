package com.wikipedia.pages.components.calendar;

import com.wikipedia.pages.components.BaseElement;
import org.openqa.selenium.WebElement;

import java.util.List;

public interface Calendar extends BaseElement {
    WebElement getCalendarBase();

    WebElement getButtonChangeMonth();

    List<WebElement> getDaysOfMonth();

    WebElement getSelectedDayOfMonth();

    void setDateWithOffset(int offsetWithSign);
}
