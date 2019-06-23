package com.example.guwap.entity;

public class Location {
    private double lattitude;
    private double longitude;
    private String name;

    public void setLattitude(double lattitude) {
        this.lattitude = lattitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Location(double lattitude, double longitude, String name) {
        this.lattitude = lattitude;
        this.longitude = longitude;
        this.name = name;
    }

    public Location(String name) {
        this(Math.random()*(33.7493-33.7487)- 33.7493, Math.random()*(84.3877 - 84.3883) - 84.3877, name);
    }

    public double getLattitude() {
        return lattitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public String getName() {
        return name;
    }
}