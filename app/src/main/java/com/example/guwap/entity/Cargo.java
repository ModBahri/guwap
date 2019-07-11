package com.example.guwap.entity;
import java.util.ArrayList;
import java.util.List;

public class Cargo {
    public List<Item> myCargo;

    //creating a list of items
    public Cargo() {
        myCargo = new ArrayList<Item>();
    }
}
