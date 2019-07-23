package com.example.guwap.entity;

import java.util.List;

public class Wagon {
    private List<Item> cargo;
    private double distance;
    private double maxDistance;
    private String fid;

    public String getFid() {
        return fid;
    }

    public void setFid(String fid) {
        this.fid = fid;
    }

    public Wagon(String difficulty) {
        this.cargo = (new Items(difficulty)).getItems();
        this.maxDistance = 1000000000;
        this.distance = maxDistance;

        this.cargo.get(4).setQuantity(10);
    }

    public Wagon() {

    }

    public List<Item> getCargo() {
        return cargo;
    }

    public void setCargo(List<Item> cargo) {
        this.cargo = cargo;
    }

    public double getDistance() { return this.distance;}

    public double getMaxDistance() {
        return maxDistance;
    }

    public void setMaxDistance(double maxDistance) {
        this.maxDistance = maxDistance;
    }

    public void setDistance(double distance) { this.distance = distance; }
}
