package com.wikipedia.api.services;

import com.wikipedia.api.base.EventsResponse;
import com.wikipedia.core.browser.Browser;
import io.restassured.http.ContentType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static io.restassured.RestAssured.given;

public class EventsPageService {
    private static final Logger logger = LoggerFactory.getLogger(Browser.class);

    //<editor-fold desc="Public Methods">
    public EventsResponse getEventsPageResponse(String locationName) {
        EventsResponse response = null;
        try {
            response = given()
                    .contentType(ContentType.JSON)
                    .when()
                    .get(String.format("https://en.wikipedia.org/api/rest_v1/page/summary/%s", locationName))
                    .then()
                    .statusCode(200)
                    .extract()
                    .as(EventsResponse.class);
        } catch (Throwable e) {
            logger.error(String.format("Unexpected error occured: %s", e));
        }
        logger.info(String.format("Got the response of '%s' event: %s", locationName, response));
        return response;
    }
    //</editor-fold>
}