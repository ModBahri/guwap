package com.example.guwap.model;

import com.example.guwap.entity.Location;

import java.util.List;

public class LocationInteractor extends Interactor{
    public LocationInteractor(Repository repo) { super (repo);}

    public List<Location> getAllLocations() { return getRepository().getAllLocations(); }

    public void addLocation (Location p) { getRepository().addLocation(p); }

    public double distanceBetween (Location current, Location destination) {
        double curlat = current.getLattitude();
        double curlong = current.getLongitude();
        double destlat = destination.getLattitude();
        double destlong = destination.getLongitude();

        double latdif2 = Math.pow((destlat - curlat), 2);
        double longdif2 = Math.pow((destlong - curlong), 2);

        return Math.sqrt(latdif2 + longdif2);
    }
}
