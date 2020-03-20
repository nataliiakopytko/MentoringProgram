package com.wikipedia.api.base;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class EventsResponse {
    private Coordinates coordinates;
}
