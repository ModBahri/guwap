package com.example.guwap.entity;

public class Region{
    private double lattitude;
    private double longitude;
    private String name;
    private PeopleType peopleType;
    private Resources resources;


    public void setLattitude(double lattitude) {
        this.lattitude = lattitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Region(String name) {
        this(Math.random()*(33.7493-33.7487)- 33.7493, Math.random()*(84.3877 - 84.3883) - 84.3877, name, PeopleType.randPeopleType(), Resources.randResources());
    }

    public Region(double lattitude, double longitude, String name) {
        this(lattitude, longitude, name, PeopleType.randPeopleType(), Resources.randResources());
    }

    public Region(double lattitude, double longitude, String name, PeopleType peopleType, Resources resources) {
        this.lattitude = lattitude;
        this.longitude = longitude;
        this.name = name;
        this.peopleType = peopleType;
        this.resources = resources;
    }

    public double getLattitude() { return lattitude; }

    public double getLongitude() {
        return longitude;
    }

    public String getName() {
        return name;
    }

    public PeopleType getPeopleType() {
        return peopleType;
    }

    public Resources getResources() {
        return resources;
    }

    public double distanceTo(Region region) {
        double lat1 = this.lattitude;
        double lon1 = this.longitude;

        double lat2 = region.lattitude;
        double lon2 = region.longitude;

        double r = 6371e3; // metres
        double φ1 = Math.toRadians(lat1);
        double φ2 = Math.toRadians(lat2);
        double Δφ = Math.toRadians(lat2-lat1);
        double Δλ = Math.toRadians(lon2-lon1);

        double a = Math.sin(Δφ/2) * Math.sin(Δφ/2) +
                Math.cos(φ1) * Math.cos(φ2) *
                        Math.sin(Δλ/2) * Math.sin(Δλ/2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));

        return r * c;
    }

}