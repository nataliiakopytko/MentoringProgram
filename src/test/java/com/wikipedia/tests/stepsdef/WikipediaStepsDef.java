package com.wikipedia.tests.stepsdef;

import com.wikipedia.api.base.EventsResponse;
import com.wikipedia.api.services.EventsPageService;
import com.wikipedia.core.context.Context;
import com.wikipedia.core.context.ContextKeys;
import com.wikipedia.core.helper.ListTransformerHelper;
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
    private final MainPage mainPage = PageFactory.getPageByIdentifier(MainPage.PAGE_IDENTIFIER);
    private final EventsPage eventsPage = PageFactory.getPageByIdentifier(EventsPage.PAGE_IDENTIFIER);
    private final PageWithCoordinates pageWithCoordinates = PageFactory.getPageByIdentifier(PageWithCoordinates.PAGE_IDENTIFIER);

    //<editor-fold desc="Private Methods">
    private List<String> getEventsWithCoordinates() {
        EventsPageService eventsPageService = new EventsPageService();
        List<String> eventsList = ListTransformerHelper.getUiToApiTransformedEventsList(eventsPage.getEventsList());
        return eventsList.parallelStream()
                .filter(event -> {
                    EventsResponse eventsPageResponse = eventsPageService.getEventsPageResponse(event);
                    return Objects.nonNull(eventsPageResponse) && Objects.nonNull(eventsPageResponse.getCoordinates());
                })
                .collect(Collectors.toList());
    }
    //</editor-fold>

    //<editor-fold desc="When">
    @When("^I open Wikipedia Main page$")
    public void iOpenWikipedia() {
        mainPage.open();
        mainPage.waitForPageLoaded();
    }

    @When("^I click on \"?([^\"]*)\"? section on Wikipedia Main page and wait for \"?([^\"]*)\"? page loaded$")
    public void iClickOnSectionOnWikipediaMainPage(String sectionName, String identifier) {
        mainPage.getSection(sectionName).click();
        PageFactory.getPageByIdentifier(identifier).waitForPageLoaded();
    }

    @When("^I click on any displayed geolocation on Events page and wait for Coordinates page loaded$")
    public void iClickOnAnyGeolocationFromTheListAboveOnEventsPage() {
        List<String> displayedGeolocations = ListTransformerHelper.getApiToUiTransformedEventsList(getEventsWithCoordinates());
        eventsPage.getGeolocation(displayedGeolocations.get(0)).click();
        pageWithCoordinates.waitForPageLoaded();
    }

    @When("^I save list of geolocations on Events page$")
    public void iSaveListOfGeolocationsOnEventsPage() {
        List<String> eventsWithCoordinates = getEventsWithCoordinates();
        Context.setValue(ContextKeys.GEOLOCATIONS_LIST, eventsWithCoordinates);
    }
    //</editor-fold>

    //<editor-fold desc="Then">
    @Then("^I check coordinates are displayed according to pattern below on Coordinates page$")
    public void iCheckCoordinatesAreDisplayedAccordingToPatternBelowOnCoordinatesPage(String regex) {
        String fullCoordinates = pageWithCoordinates.getCoordinates().getFullCoordinates();
        boolean isMatched = Pattern.matches(regex, fullCoordinates);
        Assert.assertTrue(isMatched, String.format("Coordinates '%s' don't match pattern '%s'", fullCoordinates, regex));
    }

    @Then("^I check geolocations are present on Events page$")
    public void iCheckGeolocationsArePresentOnEventsPage() {
        List<String> eventsWithCoordinates = getEventsWithCoordinates();
        Assert.assertFalse(eventsWithCoordinates.isEmpty(), "There are no geolocations on Events mainPage");
    }

    @Then("^I check current geolocations are not equal to previous on Events page$")
    public void iCheckCurrentGeolocationsAreNotEqualToPreviousOnEventsPage() {
        List<String> previousGeolocations = (List<String>) Context.getValue(ContextKeys.GEOLOCATIONS_LIST);
        List<String> currentGeolocations = getEventsWithCoordinates();
        Assert.assertNotEquals(currentGeolocations, previousGeolocations,
                String.format("Current geolocations list '%s' is equal to previous '%s'", currentGeolocations, previousGeolocations));
    }
    //</editor-fold>
}