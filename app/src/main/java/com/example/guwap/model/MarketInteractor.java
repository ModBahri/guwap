package com.example.guwap.model;
import com.example.guwap.entity.Item;
import com.example.guwap.entity.MarketPlace;
import com.example.guwap.entity.Player;

public class MarketInteractor {
    private Item[] myCargo;
    private Item[] marketPlaceItems;
    private int myCredits;

    public void Interaction(MarketPlace mp, Player player) {
        this.myCargo = player.getPlayerWagon().getCargo();
        this.marketPlaceItems = mp.getMarketPlaceItems();
        this.myCredits = player.getCredits();
    }

    public void buyItem(int type, int quantity) {
        //player interactions:
        //playerInventory needs to be affected
        //player credits need to be affect
        if (myCredits > marketPlaceItems[type].getQuantity() * marketPlaceItems[type].getPrice()
                && quantity >= marketPlaceItems[type].getQuantity()) {
            myCargo[type].setQuantity(myCargo[type].getQuantity() + quantity);
            marketPlaceItems[type] //need to affect the marketplaces quantity
            myCredits = myCredits - myCargo[type].getPrice() * quantity;
        } else {

        }

    }

    public void sellItem(Item item, int Quantity) {
        //player interactions:
        //playerInventory needs to be affected
        //player credits need to be affect
        myCargo.getItem(item).setQuantity(item.getQuantity - Quantity);
        if (myCargo.getItem(item).getQuantity() == 0);
            myCargo.remove(item);


    }

}
