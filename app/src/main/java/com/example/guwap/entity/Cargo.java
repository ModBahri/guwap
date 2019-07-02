package com.example.guwap.entity;
import java.util.ArrayList;

public class Cargo{
    public Item[]  myCargo;
    private final int cargoCapacity = 12;

    //creating a list of items
    public Cargo() {
        myCargo = new Item[12];
    }
}
