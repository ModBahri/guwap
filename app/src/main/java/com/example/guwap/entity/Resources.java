package com.example.guwap.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
public class Resources{

    private List<String> types;
    private String type;

    public Resources(int went) {
        types = new ArrayList<>(12);

        types.add("NoResources");
        types.add("Grassland");
        types.add("Desert");
        types.add("Forest");
        types.add("Oasis");
        types.add("Cave");
        types.add("Mountains");
        types.add("AbandonedTrainCar");
        types.add("City");
        types.add("Tribal");
        types.add("RichFauna");
        types.add("Herbs");

        Random random = new Random();
        type = types.get(random.nextInt(types.size()));
    }

    public Resources(){}

    public List<String> getTypes() {
        return types;
    }

    public void setTypes(List<String> types) {
        this.types = types;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
