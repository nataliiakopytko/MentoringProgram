package com.wikipedia.tests.stepsdef;

import com.wikipedia.pages.BasePage;
import com.wikipedia.pages.PageFactory;
import com.wikipedia.pages.components.calendar.CalendarFactory;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.testng.Assert;

public class CommonPagesStepsDef {

    //<editor-fold desc="When">
    @When("^I select \"?([^\"]*)\"? day(?:s|) \"?(more|less)\"? than today on Calendar on \"?([^\"]*)\"? page$")
    public void iSelectDayThanTodayOnCalendarOnEventsPage(int daysNumber, String condition, String identifier) {
        int offset = "less".equals(condition) ? -daysNumber : daysNumber;
        CalendarFactory.getCalendar(identifier).setDateWithOffset(offset);
    }
    //</editor-fold>

    //<editor-fold desc="Then">
    @Then("^I check Wikipedia \"?([^\"]*)\"? page is \"?(opened|closed)\"?$")
    public void iCheckWikipediaPageIs(String identifier, String state) {
        BasePage page = PageFactory.getPageByIdentifier(identifier);
        boolean isOpened = "opened".equals(state);
        Assert.assertEquals(page.verify(), isOpened, String.format("Page '%s' is not '%s'", identifier, state));
    }
    //</editor-fold>
}
