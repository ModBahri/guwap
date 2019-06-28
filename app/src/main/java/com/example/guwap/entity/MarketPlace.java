package com.example.guwap.entity;

public class MarketPlace {
    private Region region;
    private Item[] itemsForSale;

    public void MarketPlace(Player player, Region region) {
        Item[] items = new Items(player, region).getItems();
        this.itemsForSale = items;
        this.region = region;
    }
}
