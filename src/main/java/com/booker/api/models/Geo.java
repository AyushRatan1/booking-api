package com.booker.api.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Geo coordinates - nested inside Address
 * 
 * JSON: { "lat": "-37.3159", "lng": "81.1496" }
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Geo {

    @JsonProperty("lat")
    private String lat;

    @JsonProperty("lng")
    private String lng;

    public Geo() {
    }

    public Geo(String lat, String lng) {
        this.lat = lat;
        this.lng = lng;
    }

    // Getters
    public String getLat() {
        return lat;
    }

    public String getLng() {
        return lng;
    }

    // Setters
    public void setLat(String lat) {
        this.lat = lat;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    @Override
    public String toString() {
        return "Geo{lat='" + lat + "', lng='" + lng + "'}";
    }
}
