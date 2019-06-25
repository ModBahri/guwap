package com.example.guwap.entity;
import java.util.ArrayList;

public class Items {
    private Item[] items;

    public Items() {

        items = new Item[MarketPlace.getnumberOfTypesOfItems()];

        Item exampleItem = new Item(Difficulty.NORMAL, Resources.NORESOURCES, PeopleType.TROGOLODYTE, TypeOfItems.STEERS);

        items[1] = new Item(Player.getDifficulty(), Region.resources, Region.peopleType, MarketPlace.itemType);
        items[2] = new Item(Player.difficulty, Region.resources, Region.peopleType, MarketPlace.itemType);
        items[3] = new Item(Player.difficulty, Region.resources, Region.peopleType, MarketPlace.itemType);
        items[4] = new Item(Player.difficulty, Region.resources, Region.peopleType, MarketPlace.itemType);
        items[5] = new Item(Player.difficulty, Region.resources, Region.peopleType, MarketPlace.itemType);
        items[6] = new Item(Player.difficulty, Region.resources, Region.peopleType, MarketPlace.itemType);
        items[7] = new Item(Player.difficulty, Region.resources, Region.peopleType, MarketPlace.itemType);
        items[8] = new Item(Player.difficulty, Region.resources, Region.peopleType, MarketPlace.itemType);
        items[9] = new Item(Player.difficulty, Region.resources, Region.peopleType, MarketPlace.itemType);
        items[10] = new Item(Player.difficulty, Region.resources, Region.peopleType, MarketPlace.itemType);
        items[11] = new Item(Player.difficulty, Region.resources, Region.peopleType, MarketPlace.itemType);
        items[12] = new Item(Player.difficulty, Region.resources, Region.peopleType, MarketPlace.itemType);

    }
    public Item[] getItems() {
        return items;
    }

}

