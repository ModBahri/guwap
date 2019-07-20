package com.example.guwap.entity;

public class Region{
    private double lattitude;
    private double longitude;
    private String name;
    private String peopleType;
    private String resources;
    private String fid;

    public void setResources(String resources) {
        this.resources = resources;
    }

    public String getFid() {
        return fid;
    }

    public void setFid(String fid) {
        this.fid = fid;
    }

    public void setLattitude(double lattitude) {
        this.lattitude = lattitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Region() {

    }


    public Region(double lattitude, double longitude, String name) {
        this.lattitude = lattitude;
        this.longitude = longitude;
        this.name = name;
        this.peopleType = new PeopleType(1).getType();
        this.resources = new Resources(1).getType();
    }


    public double getLattitude() { return lattitude; }

    public double getLongitude() {
        return longitude;
    }

    public String getName() {
        return name;
    }

    public String getPeopleType() {
        return peopleType;
    }

    public String getResources() {
        return resources;
    }

    public double distanceTo(Region region) {
        if (region == null) {
            throw new IllegalArgumentException("Region cannot be null");
        }

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

    public void setPeopleType(String peopleType) {
        this.peopleType = peopleType;
    }
}