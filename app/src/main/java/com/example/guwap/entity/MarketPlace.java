package com.example.guwap.entity;

import android.content.Context;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

//Everything in this class was made by jorge.
public class MarketPlace {
    private Region region;
    private List<Item> marketPlaceItems;

    public MarketPlace(Player player, Region region) {
        this.marketPlaceItems = new Items(player, region).getItems();
        this.region = region;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    public List<Item> getMarketPlaceItems() {
        return marketPlaceItems;
    }

    public void setMarketPlaceItems(List<Item> marketPlaceItems) {
        this.marketPlaceItems = marketPlaceItems;
    }

    public boolean buyItem(Player player, int type, int quantity) {
        //player interactions:
        //playerInventory needs to be affected
        //player credits need to be affect
        if (player.getCredits() > (quantity * this.getMarketPlaceItems().get(type).getPrice())
                && quantity <= this.getMarketPlaceItems().get(type).getQuantity()) {
            player.getPlayerWagon().getCargo().get(type).setQuantity(player.getPlayerWagon().getCargo().get(type).getQuantity() + quantity);
            this.getMarketPlaceItems().get(type).setQuantity(this.getMarketPlaceItems().get(type).getQuantity() - quantity);
            player.setCredits(player.getCredits() - player.getPlayerWagon().getCargo().get(type).getPrice() * quantity);
            return true;
        } else {
            return false;
        }

    }

    public boolean sellItem(Player player, int type, int quantity) {
        //player interactions:
        //playerInventory needs to be affected
        //player credits need to be affect
        if (quantity <= player.getPlayerWagon().getCargo().get(type).getQuantity()) {
            player.getPlayerWagon().getCargo().get(type).setQuantity(player.getPlayerWagon().getCargo().get(type).getQuantity() - quantity);
            player.setCredits(player.getCredits() + player.getPlayerWagon().getCargo().get(type).getPrice() * quantity);
            return true;
        } else {
            return false;
        }

    }

}
