Feature: Wikipedia Events testing

  Background:
    Given I open Wikipedia Main page
    Then I check Wikipedia "Main" page is "opened"

  Scenario: Check that wikipedia shows today's events
    Given I click on "On this day" section on Wikipedia Main page and wait for "Events" page loaded
    Then I check Wikipedia "Events" page is "opened"
    When I search for geolocations below on Events page:
      | Philippines |
      | Belgium     |
      | France      |
      | Netherlands |
      | Bulgaria    |
      | Israel      |
      | Jericho     |
      | Ukraine     |
      | Japan       |
    Then I check at least one geolocation from the list above is displayed on Events page

  Scenario: Check that wikipedia shows tomorrow's events
    Given I click on "On this day" section on Wikipedia Main page and wait for "Events" page loaded
    Then I check Wikipedia "Events" page is "opened"
    When I select "1" day "more" than today on Calendar on Events page
    And I search for geolocations below on Events page:
      | Philippines |
      | Belgium     |
      | France      |
      | Netherlands |
      | Bulgaria    |
      | Israel      |
      | Jericho     |
      | Ukraine     |
      | Japan       |
    Then I check at least one geolocation from the list above is displayed on Events page

  Scenario: Check that wikipedia shows coordinates
    Given I click on "On this day" section on Wikipedia Main page and wait for "Events" page loaded
    Then I check Wikipedia "Events" page is "opened"
    When I select "1" day "more" than today on Calendar on Events page
    And I search for geolocations below on Events page:
      | Philippines |
      | Belgium     |
      | France      |
      | Netherlands |
      | Bulgaria    |
      | Israel      |
      | Jericho     |
      | Ukraine     |
      | Japan       |
    Then I check at least one geolocation from the list above is displayed on Events page
    When I click on any displayed geolocation from the list above on Events page and wait for Coordinates page loaded
    Then I check Wikipedia "Coordinates" page is "opened"
    Then I check coordinates are displayed according to pattern below on Coordinates page
      """
      ^(([0-9]{1,3})(°|′|″)){1,3}(N|S)(([0-9]{1,3})(°|′|″)){1,3}(W|E)$
      """