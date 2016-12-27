package com.leonardo.mapsapi.model;

public class Adress {

    private final double EARTH_RADIUS = 6371000; // m

    private double latitude;
    private double longitude;

    private String state;
    private String city;
    private String district;
    private String street;

    public Adress() {

    }

    public Adress(Result result) {
        this.latitude = result.getLatitude();
        this.longitude = result.getLongitude();
        this.street = result.getStreet();
        this.district = result.getDistrict();
        this.city = result.getCity();
        this.state = result.getState();
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public Location calculateDerivedPosition(double range, double bearing) {

        double latA = Math.toRadians(getLatitude());
        double lonA = Math.toRadians(getLongitude());
        double angularDistance = range / EARTH_RADIUS;
        double trueCourse = Math.toRadians(bearing);

        double lat = Math.asin(
                Math.sin(latA) * Math.cos(angularDistance) +
                        Math.cos(latA) * Math.sin(angularDistance)
                                * Math.cos(trueCourse));

        double dlon = Math.atan2(
                Math.sin(trueCourse) * Math.sin(angularDistance)
                        * Math.cos(latA),
                Math.cos(angularDistance) - Math.sin(latA) * Math.sin(lat));

        double lon = ((lonA + dlon + Math.PI) % (Math.PI * 2)) - Math.PI;

        lat = Math.toDegrees(lat);
        lon = Math.toDegrees(lon);

        Location derivedPosition = new Location(lat, lon);
        return derivedPosition;
    }

}
