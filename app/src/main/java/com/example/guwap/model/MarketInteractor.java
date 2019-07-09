package com.example.guwap.model;
import com.example.guwap.entity.Cargo;
import com.example.guwap.entity.Item;

import java.util.List;

public class MarketInteractor {


    private Item[] myCargo = player.getWagon().getCargo();

    public void buyItem(int item, int quantity) {
        //player interactions:
        //playerInventory needs to be affected
        //player credits need to be affect
        if (myCargo[item].getQuantity() > 0) {
            myCargo[item].setQuantity(myCargo[item].getQuantity() + quantity);
        } else {
            item.setQuantity(quantity);
            myCargo.add(item);
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
