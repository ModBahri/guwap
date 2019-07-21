package com.example.guwap.entity;


import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PeopleType {

    private List<String> types;
    private String type;

    public PeopleType(int went) {
        types = new ArrayList<>(8);

        types.add("Troglodyte");
        types.add("Bandit");
        types.add("American");
        types.add("Mexican");
        types.add("Cheyenne");
        types.add("Apache");
        types.add("Comanche");
        types.add("Navajo");

        Random random = new Random();
        type = types.get(random.nextInt(types.size()));
    }

    public PeopleType() {

    }

    public List<String> getTypes() {
        return types;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setTypes(List<String> types) {
        this.types = types;
    }
}
