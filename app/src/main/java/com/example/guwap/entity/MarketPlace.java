package com.example.guwap.entity;

public class MarketPlace {
    private TypeOfItem itemType;

    public MarketPlace(TypeOfItem itemType) {
        this.itemType = itemType;
    }

    public static int getNumberOfTypesOfItems() {
        return 12;
    }

    public TypeOfItem getItemType() {
        return itemType;
    }
}
