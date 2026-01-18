package com.booker.api.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Address model - nested inside User
 * 
 * JSON:
 * {
 * "street": "Kulas Light",
 * "suite": "Apt. 556",
 * "city": "Gwenborough",
 * "zipcode": "92998-3874",
 * "geo": { "lat": "-37.3159", "lng": "81.1496" }
 * }
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Address {

    @JsonProperty("street")
    private String street;

    @JsonProperty("suite")
    private String suite;

    @JsonProperty("city")
    private String city;

    @JsonProperty("zipcode")
    private String zipcode;

    @JsonProperty("geo")
    private Geo geo; // Nested object!

    public Address() {
    }

    // Builder pattern
    public static AddressBuilder builder() {
        return new AddressBuilder();
    }

    public static class AddressBuilder {
        private String street;
        private String suite;
        private String city;
        private String zipcode;
        private Geo geo;

        public AddressBuilder street(String street) {
            this.street = street;
            return this;
        }

        public AddressBuilder suite(String suite) {
            this.suite = suite;
            return this;
        }

        public AddressBuilder city(String city) {
            this.city = city;
            return this;
        }

        public AddressBuilder zipcode(String zipcode) {
            this.zipcode = zipcode;
            return this;
        }

        public AddressBuilder geo(Geo geo) {
            this.geo = geo;
            return this;
        }

        public Address build() {
            Address address = new Address();
            address.street = this.street;
            address.suite = this.suite;
            address.city = this.city;
            address.zipcode = this.zipcode;
            address.geo = this.geo;
            return address;
        }
    }

    // Getters
    public String getStreet() {
        return street;
    }

    public String getSuite() {
        return suite;
    }

    public String getCity() {
        return city;
    }

    public String getZipcode() {
        return zipcode;
    }

    public Geo getGeo() {
        return geo;
    }

    // Setters
    public void setStreet(String street) {
        this.street = street;
    }

    public void setSuite(String suite) {
        this.suite = suite;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public void setGeo(Geo geo) {
        this.geo = geo;
    }

    @Override
    public String toString() {
        return "Address{" +
                "street='" + street + '\'' +
                ", suite='" + suite + '\'' +
                ", city='" + city + '\'' +
                ", zipcode='" + zipcode + '\'' +
                ", geo=" + geo +
                '}';
    }
}
