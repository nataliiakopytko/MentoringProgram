package com.wikipedia.tests.stepsdef;

import com.wikipedia.core.context.Context;
import com.wikipedia.core.context.ContextKeys;
import com.wikipedia.pages.EventsPage;
import com.wikipedia.pages.MainPage;
import com.wikipedia.pages.PageFactory;
import com.wikipedia.pages.PageWithCoordinates;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.testng.Assert;

import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@SuppressWarnings("unchecked")
public class WikipediaStepsDef {
    @When("^I open Wikipedia Main page$")
    public void iOpenWikipedia() {
        MainPage page = (MainPage) PageFactory.getPageByIdentifier(MainPage.PAGE_IDENTIFIER);
        page.open();
        page.waitForPageLoaded();
    }

    @When("^I click on \"?([^\"]*)\"? section on Wikipedia Main page and wait for \"?([^\"]*)\"? page loaded$")
    public void iClickOnSectionOnWikipediaMainPage(String sectionName, String identifier) {
        MainPage page = (MainPage) PageFactory.getPageByIdentifier(MainPage.PAGE_IDENTIFIER);
        page.getSection(sectionName).click();
        PageFactory.getPageByIdentifier(identifier).waitForPageLoaded();
    }

    @When("^I search for geolocations below on Events page:$")
    public void iSearchForGeolocationsBelowOnEventsPage(List<String> locationsList) {
        EventsPage page = (EventsPage) PageFactory.getPageByIdentifier(EventsPage.PAGE_IDENTIFIER);
        List<String> foundElements = locationsList.stream()
                .filter(i -> Objects.nonNull(page.getGeolocation(i)))
                .collect(Collectors.toList());
        Context.setValue(ContextKeys.GEOLOCATIONS_LIST, foundElements);
    }

    @When("^I select \"?([^\"]*)\"? day(?:s|) \"?(more|less)\"? than today on Calendar on Events page$")
    public void iSelectDayThanTodayOnCalendarOnEventsPage(int daysNumber, String condition) {
        EventsPage page = (EventsPage) PageFactory.getPageByIdentifier(EventsPage.PAGE_IDENTIFIER);
        int offset = "less".equals(condition) ? -daysNumber : daysNumber;
        page.getWikiCalendar().setDateWithOffset(offset);
    }

    @When("^I click on any displayed geolocation from the list above on Events page and wait for Coordinates page loaded$")
    public void iClickOnAnyGeolocationFromTheListAboveOnEventsPage() {
        List<String> displayedGeolocations = (List<String>) Context.getValue(ContextKeys.GEOLOCATIONS_LIST);
        EventsPage page = (EventsPage) PageFactory.getPageByIdentifier(EventsPage.PAGE_IDENTIFIER);
        page.getGeolocation(displayedGeolocations.get(0)).click();
        (PageFactory.getPageByIdentifier(PageWithCoordinates.PAGE_IDENTIFIER)).waitForPageLoaded();
    }

    @Then("^I check at least one geolocation from the list above is displayed on Events page$")
    public void iCheckAtLeastOneGeolocationFromTheListAboveIsDisplayedOnEventsPage() {
        List<String> displayedGeolocations = (List<String>) Context.getValue(ContextKeys.GEOLOCATIONS_LIST);
        Assert.assertTrue(displayedGeolocations.size() > 0, "No geolocations were found on Events page");
    }

    @Then("^I check coordinates are displayed according to pattern below on Coordinates page$")
    public void iCheckCoordinatesAreDisplayedAccordingToPatternBelowOnCoordinatesPage(String regex) {
        PageWithCoordinates page = (PageWithCoordinates) PageFactory.getPageByIdentifier(PageWithCoordinates.PAGE_IDENTIFIER);
        String fullCoordinates = page.getCoordinates().getFullCoordinates();
        boolean isMatched = Pattern.matches(regex, fullCoordinates);
        Assert.assertTrue(isMatched, String.format("Coordinates [%s] don't match pattern [%s]", fullCoordinates, regex));
    }
}