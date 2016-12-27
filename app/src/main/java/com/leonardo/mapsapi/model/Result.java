package com.leonardo.mapsapi.model;

import java.util.List;

public class Result {

    public static final int STREET_INDEX = 1;
    public static final int DISTRICT_INDEX = 2;
    public static final int CITY_INDEX = 4;
    public static final int STATE_INDEX = 5;
    public static final int COUNTRY_INDEX = 6;

    private List<AddressComponent> address_components;
    private Geometry geometry;
    private String formatted_address;

    public List<AddressComponent> getAddress_components() {
        return address_components;
    }

    public String getFormatted_address() {
        return formatted_address;
    }

    public String getStreet() {
        return getAddress_components().get(STREET_INDEX).getLong_name();
    }

    public String getDistrict() {
        return getAddress_components().get(DISTRICT_INDEX).getLong_name();
    }

    public String getCity() {
        return getAddress_components().get(CITY_INDEX).getLong_name();
    }

    public String getState() {
        return getAddress_components().get(STATE_INDEX).getLong_name();
    }

    public String getCountry() {
        return getAddress_components().get(COUNTRY_INDEX).getLong_name();
    }

    public Adress getAdress() {
        return new Adress(this);
    }

    public double getLongitude() {
        return geometry.getLocation().getLng();
    }

    public double getLatitude() {
        return geometry.getLocation().getLat();
    }

}
