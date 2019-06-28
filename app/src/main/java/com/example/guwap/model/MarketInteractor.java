package com.example.guwap.model;
import com.example.guwap.entity.Cargo;
import com.example.guwap.entity.Item;

public class MarketInteractor {

    List<Item> myCargo = new Cargo();

    public void buyItem(Item item, int Quantity) {
        if (myCargo.contains(item)) {
            myCargo.getItem(item).setQuantity(item.getQuantity + Quantity);
        } else {
            item.setQuantity(Quantity);
            myCargo.add(item);
        }
    }

    public void sellItem(Item item, int Quantity) {
        myCargo.getItem(item).setQuantity(item.getQuantity - Quantity);
        if (myCargo.getItem(item).getQuantity() == 0);
            myCargo.remove(item);
    }

}
