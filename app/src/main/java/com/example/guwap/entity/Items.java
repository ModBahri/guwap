package com.example.guwap.entity;

import java.util.ArrayList;
import java.util.List;

//Everything in this class was made by jorge.
public class Items {
    private List<Item> items;
    private String fid;

    public String getFid() {
        return fid;
    }

    public void setFid(String fid) {
        this.fid = fid;
    }

    public Items() {

    }

    public Items(Difficulty difficulty) {
        items = new ArrayList<>(12);

        for (TypeOfItem typeOfItem : TypeOfItem.values()) {
            items.add(new Item(difficulty, typeOfItem));
        }
    }
    public Items(Player player, Region region) {

        items = new ArrayList<>(12);

        for (TypeOfItem typeOfItem : TypeOfItem.values()) {
            items.add(new Item(player.getDifficulty(), region.getResources(),
                    region.getPeopleType(), typeOfItem));
        }
    }
    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
}
