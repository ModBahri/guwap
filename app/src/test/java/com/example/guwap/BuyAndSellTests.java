package com.example.guwap;

import com.example.guwap.entity.MarketPlace;
import com.example.guwap.entity.Player;
import com.example.guwap.entity.Region;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

//Gabi
public class BuyAndSellTests {
    //@Before
    //    public void setUp() {
    //        Region tech = new Region("Georgia Tech");
    //        Player yukt = new Player();
    //        MarketPlace buzzShop = new MarketPlace(yukt, tech);
    //        int type = 1; //horses
    //        int quantity = 3;
    //    }

    @Test
    public void buyTest() {
        //If enough credits and quantity in stock:
        //Should increase player's quantity of item
        //Should decrease market's quantity of item
        //Should decrease player's credits

        Region tech = new Region("Georgia Tech");
        Player defaultPlayer = new Player();
        MarketPlace buzzShop = new MarketPlace(defaultPlayer, tech);
        int type = 1; //horses
        int quantity = 3;
        int origQuantity = buzzShop.getMarketPlaceItems()[type].getQuantity();
        //defaultPlayer's credits: 1000

        //Each marketplace generates a random quantity for each item
        //Has enough money and quantity is correct
        if (buzzShop.buyItem(defaultPlayer, type, quantity)) {
            //increase player's quantity of item
            assertEquals(3, defaultPlayer.getPlayerWagon().getCargo()[type].getQuantity());
            //decrease market's quantity of item
            assertEquals((origQuantity - 3), buzzShop.getMarketPlaceItems()[type].getQuantity());
            //decrease player's credits
            assertEquals(400, defaultPlayer.getCredits());
        } else {
            //All stats should remain the same
            assertEquals(0, defaultPlayer.getPlayerWagon().getCargo()[type].getQuantity());
            assertEquals(origQuantity, buzzShop.getMarketPlaceItems()[type].getQuantity());
            assertEquals(1000, defaultPlayer.getCredits());
        }
    }

    @Test
    public void sellTest() {
        Region tech = new Region("Georgia Tech");
        Player defaultPlayer = new Player();
        MarketPlace buzzShop = new MarketPlace(defaultPlayer, tech);
        int type = 1; //horses
        int quantity = 1;
        int origQuantity = buzzShop.getMarketPlaceItems()[type].getQuantity();

        //Player has nothing to sell so shouldn't be able to sell anything yet
        assertEquals(false, buzzShop.sellItem(defaultPlayer, type, quantity));

        //Buying things that player can sell
        int currQuantity = defaultPlayer.getPlayerWagon().getCargo()[type].getQuantity(); //Should be 0
        //buy until player no longer has 0 horses - due to each market's random quantity of items
        while (currQuantity == 0) {
            buzzShop.buyItem(defaultPlayer, type, quantity);
            currQuantity = defaultPlayer.getPlayerWagon().getCargo()[type].getQuantity();
        }

        //Now try to sell that horse
        assertEquals(true, buzzShop.sellItem(defaultPlayer, type, quantity));
        //decrease player's quantity of item
        assertEquals(0, defaultPlayer.getPlayerWagon().getCargo()[type].getQuantity());
        //increase market's quantity of item
        assertEquals((origQuantity + 1), buzzShop.getMarketPlaceItems()[type].getQuantity());
        //increase player's credits
        assertEquals(1000, defaultPlayer.getCredits());
    }
}