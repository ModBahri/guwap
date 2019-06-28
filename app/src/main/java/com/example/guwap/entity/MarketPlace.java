package com.example.guwap.entity;

//Everything in this class was made by jorge.
public class MarketPlace {
    private Region region;
    private Item[] marketPlaceItems;

    public void MarketPlace(Player player, Region region) {
        Item[] items = new Items(player, region).getItems();
        this.marketPlaceItems = items;
        this.region = region;
    }
}
