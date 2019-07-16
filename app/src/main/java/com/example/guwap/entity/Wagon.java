package com.example.guwap.entity;

public class Wagon {
    private Item[] cargo;
    private double distance;
    private double maxDistance;

    public Wagon(Difficulty difficulty) {
        this.cargo = (new Items(difficulty)).getItems();
        this.maxDistance = 1000000000;
        this.distance = maxDistance;

        this.cargo[4].setQuantity(10);
    }

    public Item[] getCargo() {
        return cargo;
    }

    public void setCargo(Item[] cargo) {
        this.cargo = cargo;
    }

    public double getDistance() { return this.distance;}

    public void setDistance(double distance) { this.distance = distance; }
}
