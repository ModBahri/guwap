package com.example.guwap.entity;
import java.util.ArrayList;

public class Items {
    private Item[] items;
    public Items(Player player, Region region, MarketPlace marketPlace) {

        items = new Item[MarketPlace.getNumberOfTypesOfItems()];

        Item exampleItem = new Item(Difficulty.NORMAL, Resources.NORESOURCES, PeopleType.TROGOLODYTE, TypeOfItem.STEERS);

        for (int i = 0; i < 11; i++) {
            items[i] = new Item(player.getDifficulty(), region.getResources(),
                    region.getPeopleType(), marketPlace.getItemType());
        }
    }
    public Item[] getItems() {
        return items;
    }

}

