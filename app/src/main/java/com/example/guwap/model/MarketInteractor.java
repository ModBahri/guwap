package com.example.guwap.model;
import com.example.guwap.entity.Item;
import com.example.guwap.entity.MarketPlace;
import com.example.guwap.entity.Player;

public class MarketInteractor {
    private Item[] myCargo;
    private Item[] marketPlaceItems;
    private int myCredits;

    /**
     * Set up interaction
     * @param mp marketplace to use
     * @param player player to use
     */
    public void Interaction(MarketPlace mp, Player player) {
        this.myCargo = player.getPlayerWagon().getCargo();
        this.marketPlaceItems = mp.getMarketPlaceItems();
        this.myCredits = player.getCredits();
    }

    /**
     * Buy item
     * @param mp marketplace to use
     * @param player player to use
     * @param type type of thing
     * @param quantity quantity to buy or sell
     */
    public void buyItem(MarketPlace mp, Player player, int type, int quantity) {
        //player interactions:
        //playerInventory needs to be affected
        //player credits need to be affect
        if (player.getCredits() > quantity * mp.getMarketPlaceItems()[type].getPrice()
                && quantity >= mp.getMarketPlaceItems()[type].getQuantity()) {
            player.getPlayerWagon().getCargo()[type].setQuantity(player.getPlayerWagon().getCargo()[type].getQuantity() + quantity);
            mp.getMarketPlaceItems()[type].setQuantity(mp.getMarketPlaceItems()[type].getQuantity() - quantity);
            player.setCredits(player.getCredits() - player.getPlayerWagon().getCargo()[type].getPrice() * quantity);
        } /*else {
            deal with case where the player tries to buy more than the marketplace has in stock
        }*/

    }

    /**
     * sell item
     * @param mp marketplace to use
     * @param player player to use
     * @param type type to sell
     * @param quantity quantity to buy
     */
    public void sellItem(MarketPlace mp, Player player, int type, int quantity) {
        //player interactions:
        //playerInventory needs to be affected
        //player credits need to be affect
        if (quantity >= player.getPlayerWagon().getCargo()[type].getQuantity()) {
            player.getPlayerWagon().getCargo()[type].setQuantity(player.getPlayerWagon().getCargo()[type].getQuantity() - quantity);
            player.setCredits(player.getCredits() + player.getPlayerWagon().getCargo()[type].getPrice() * quantity);
        } /*else {
            deal with the case where the person is trying to sell more than they have in their cargo
        }*/

    }

}
