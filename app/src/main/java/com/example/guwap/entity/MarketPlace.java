package com.example.guwap.entity;

public class MarketPlace {
    private TypeOfItems itemType;

    public MarketPlace(TypeOfItems itemType) {
        this.itemType = itemType;
    }

    public static int getNumberOfTypesOfItems() {
        return 12;
    }

    public TypeOfItems getItemType() {
        return itemType;
    }
}
