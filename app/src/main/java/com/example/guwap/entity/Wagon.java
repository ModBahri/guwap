package com.example.guwap.entity;

public class Wagon {
    private Item[] cargo;

    public Wagon(Difficulty difficulty) {
        this.cargo = new Items(difficulty).getItems();
    }

    public Item[] getCargo() {
        return cargo;
    }

    public void setCargo(Item[] cargo) {
        this.cargo = cargo;
    }
}
