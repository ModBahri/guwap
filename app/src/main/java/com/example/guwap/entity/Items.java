package com.example.guwap.entity;

//Everything in this class was made by jorge.
public class Items {
    private Item[] items;

    public Items(Difficulty difficulty) {
        items = new Item[12];

        int i = 0;
        for (TypeOfItem typeOfItem : TypeOfItem.values()) {
            items[i] = new Item(difficulty, typeOfItem);
            i++;
        }
    }
    public Items(Player player, Region region) {

        items = new Item[12];

        //Item exampleItem = new Item(Difficulty.NORMAL, Resources.NORESOURCES,
        //        PeopleType.TROGOLODYTE, TypeOfItem.STEERS);
        int i = 0;
        for (TypeOfItem typeOfItem : TypeOfItem.values()) {
            items[i] = new Item(player.getDifficulty(), region.getResources(),
                    region.getPeopleType(), typeOfItem);
            i++;
        }
    }
    public Item[] getItems() {
        return items;
    }

}

