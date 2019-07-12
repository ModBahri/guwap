package com.example.guwap.entity;

import android.content.Context;
import android.widget.Toast;

//Everything in this class was made by jorge.
public class MarketPlace {
    private Region region;
    private Item[] marketPlaceItems;

    public MarketPlace(Player player, Region region) {
        Item[] items = new Items(player, region).getItems();
        this.marketPlaceItems = items;
        this.region = region;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    public Item[] getMarketPlaceItems() {
        return marketPlaceItems;
    }

    public void setMarketPlaceItems(Item[] marketPlaceItems) {
        this.marketPlaceItems = marketPlaceItems;
    }

    public boolean buyItem(Player player, int type, int quantity) {
        //player interactions:
        //playerInventory needs to be affected
        //player credits need to be affect
        if (player.getCredits() > (quantity * this.getMarketPlaceItems()[type].getPrice())
                && quantity <= this.getMarketPlaceItems()[type].getQuantity()) {
            player.getPlayerWagon().getCargo()[type].setQuantity(player.getPlayerWagon().getCargo()[type].getQuantity() + quantity);
            this.getMarketPlaceItems()[type].setQuantity(this.getMarketPlaceItems()[type].getQuantity() - quantity);
            player.setCredits(player.getCredits() - player.getPlayerWagon().getCargo()[type].getPrice() * quantity);
            return true;
        } else {
            return false;
        }

    }

    public boolean sellItem(Player player, int type, int quantity) {
        //player interactions:
        //playerInventory needs to be affected
        //player credits need to be affect
        if (quantity <= player.getPlayerWagon().getCargo()[type].getQuantity()) {
            player.getPlayerWagon().getCargo()[type].setQuantity(player.getPlayerWagon().getCargo()[type].getQuantity() - quantity);
            player.setCredits(player.getCredits() + player.getPlayerWagon().getCargo()[type].getPrice() * quantity);
            return true;
        } else {
            return false;
        }

    }

}
