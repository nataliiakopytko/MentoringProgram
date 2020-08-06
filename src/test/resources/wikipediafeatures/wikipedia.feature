Feature: Wikipedia Events testing

  Background:
    Given I open Wikipedia Main page
    Then I check Wikipedia "Main" page is "opened"

  Scenario: Check that wikipedia shows today's events
    Given I click on "On this day" section on Wikipedia Main page and wait for "Events" page loaded
    Then I check Wikipedia "Events" page is "opened"
    Then I check geolocations are present on Events page
#
#  Scenario: Check that wikipedia shows tomorrow's events
#    Given I click on "On this day" section on Wikipedia Main page and wait for "Events" page loaded
#    Then I check Wikipedia "Events" page is "opened"
#    When I save list of geolocations on Events page
#    When I select "1" day "more" than today on Calendar on "Events" page
#    Then I check current geolocations are not equal to previous on Events page
#
#  Scenario: Check that wikipedia shows coordinates
#    Given I click on "On this day" section on Wikipedia Main page and wait for "Events" page loaded
#    Then I check Wikipedia "Events" page is "opened"
#    When I select "2" days "more" than today on Calendar on "Events" page
#    When I click on any displayed geolocation on Events page and wait for Coordinates page loaded
#    Then I check Wikipedia "Coordinates" page is "opened"
#    Then I check coordinates are displayed according to pattern below on Coordinates page
#      """
#      ^((([0-9]{1,3})|([0-9]{1,3}.[0-9]{1,4}))(°|′|″)){1,3}(N|S)\s((([0-9]{1,3})|([0-9]{1,3}.[0-9]{1,4}))(°|′|″)){1,3}(W|E)$
#      """