package com.example.guwap.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProviders;

import com.example.guwap.R;
import com.example.guwap.entity.Item;
import com.example.guwap.entity.MarketPlace;
import com.example.guwap.entity.Player;
import com.example.guwap.viewmodel.PlayerViewModel;

public class MarketActivity extends FragmentActivity {
    private Player player;
    private PlayerViewModel viewModel;
    private EditText quantity;
    private Item[] marketplaceItems;
    private TextView nameText;
    private TextView availText;
    private TextView invText;
    private TextView priceText;
    private TextView yourGoldText;
    private int selectedItem;
    private MarketPlace marketPlace;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_market);
        viewModel = ViewModelProviders.of(this).get(PlayerViewModel.class);
        player = viewModel.getPlayer();
        quantity = findViewById(R.id.quantity);
        nameText = findViewById(R.id.nameText);
        availText = findViewById(R.id.availText);
        invText = findViewById(R.id.invText);
        priceText = findViewById(R.id.priceText);
        yourGoldText = findViewById(R.id.yourGoldText);

        marketPlace = new MarketPlace(player, player.getRegion());
        marketplaceItems = marketPlace.getMarketPlaceItems();

        nameText.setText("");
        availText.setText("");
        invText.setText("");
        priceText.setText("");
        int cred = player.getCredits();
        yourGoldText.setText(Integer.toString(player.getCredits()));

        Log.i("Player name", player.getName());
    }

    public void onBuyClick (View view) {
        int quantint = Integer.parseInt(quantity.getText().toString());
        if(marketPlace.buyItem(player, selectedItem , quantint)){
            availText.setText(Integer.toString(marketplaceItems[selectedItem].getQuantity()));
            invText.setText(Integer.toString(player.getPlayerWagon().getCargo()[selectedItem].getQuantity()));
            quantity.setText("");
            yourGoldText.setText(Integer.toString(player.getCredits()));
        } else {
            Context context = getApplicationContext();
            int duration = Toast.LENGTH_LONG;
            Toast toast = Toast.makeText(context, "Hey! What do you think you are trying to pull? I'll shoot ya if you walk in here without enough money or try to run off with all our stock!", duration);
            toast.show();
        }

    }

    public void onSellClick (View view) {
        int quantint = Integer.parseInt(quantity.getText().toString());
        if(marketPlace.sellItem(player, selectedItem , quantint)){
            availText.setText(Integer.toString(marketplaceItems[selectedItem].getQuantity()));
            invText.setText(Integer.toString(player.getPlayerWagon().getCargo()[selectedItem].getQuantity()));
            quantity.setText("");
            yourGoldText.setText(Integer.toString(player.getCredits()));
        } else {
            Context context = getApplicationContext();
            int duration = Toast.LENGTH_LONG;
            Toast toast = Toast.makeText(context, "You aren't too bright are ya? Trying to sell me more than you have..", duration);
            toast.show();
        }

    }

    public boolean onClickTravel (MenuItem item) {
        Intent intent = new Intent(this, MapsActivity.class);
        startActivity(intent);
        return true;
    }

    public boolean onClickMarket(MenuItem item) {
        Intent intent = new Intent(this, MarketActivity.class);
        startActivity(intent);
        return true;
    }

    public void onWhiskeyClick(View view) {
        Item item = marketplaceItems[3];

        nameText.setText("Whiskey");
        availText.setText(Integer.toString(item.getQuantity()));
        invText.setText(Integer.toString(player.getPlayerWagon().getCargo()[3].getQuantity()));
        priceText.setText(Integer.toString(item.getPrice()));

        selectedItem = 3;
    }

    public void onWoodClick(View view) {
        Item item = marketplaceItems[6];

        nameText.setText("Wood");
        availText.setText(Integer.toString(item.getQuantity()));
        invText.setText(Integer.toString(player.getPlayerWagon().getCargo()[6].getQuantity()));
        priceText.setText(Integer.toString(item.getPrice()));

        selectedItem = 6;
    }

    public void onChewClick(View view) {
        Item item = marketplaceItems[7];

        nameText.setText("Chew");
        availText.setText(Integer.toString(item.getQuantity()));
        invText.setText(Integer.toString(player.getPlayerWagon().getCargo()[7].getQuantity()));
        priceText.setText(Integer.toString(item.getPrice()));

        selectedItem = 7;
    }

    public void onMushroomClick(View view) {
        Item item = marketplaceItems[11];

        nameText.setText("Strange Mushrooms");
        availText.setText(Integer.toString(item.getQuantity()));
        invText.setText(Integer.toString(player.getPlayerWagon().getCargo()[11].getQuantity()));
        priceText.setText(Integer.toString(item.getPrice()));

        selectedItem = 11;
    }

    public void onPeyoteClick(View view) {
        Item item = marketplaceItems[8];

        nameText.setText("Peyote");
        availText.setText(Integer.toString(item.getQuantity()));
        invText.setText(Integer.toString(player.getPlayerWagon().getCargo()[8].getQuantity()));
        priceText.setText(Integer.toString(item.getPrice()));

        selectedItem = 8;
    }

    public void onGoldClick(View view) {
        Item item = marketplaceItems[5];

        nameText.setText("Gold");
        availText.setText(Integer.toString(item.getQuantity()));
        invText.setText(Integer.toString(player.getPlayerWagon().getCargo()[5].getQuantity()));
        priceText.setText(Integer.toString(item.getPrice()));

        selectedItem = 5;
    }

    public void onChickenClick(View view) {
        Item item = marketplaceItems[4];

        nameText.setText("Chicken Wing");
        availText.setText(Integer.toString(item.getQuantity()));
        invText.setText(Integer.toString(player.getPlayerWagon().getCargo()[4].getQuantity()));
        priceText.setText(Integer.toString(item.getPrice()));

        selectedItem = 4;
    }

    public void onHorseClick(View view) {
        Item item = marketplaceItems[1];

        nameText.setText("Horse");
        availText.setText(Integer.toString(item.getQuantity()));
        invText.setText(Integer.toString(player.getPlayerWagon().getCargo()[1].getQuantity()));
        priceText.setText(Integer.toString(item.getPrice()));

        selectedItem = 1;
    }

    public void onSteerClick(View view) {
        Item item = marketplaceItems[0];

        nameText.setText("Steer");
        availText.setText(Integer.toString(item.getQuantity()));
        invText.setText(Integer.toString(player.getPlayerWagon().getCargo()[0].getQuantity()));
        priceText.setText(Integer.toString(item.getPrice()));

        selectedItem = 0;
    }

    public void onCoalClick(View view) {
        Item item = marketplaceItems[9];

        nameText.setText("Coal");
        availText.setText(Integer.toString(item.getQuantity()));
        invText.setText(Integer.toString(player.getPlayerWagon().getCargo()[9].getQuantity()));
        priceText.setText(Integer.toString(item.getPrice()));

        selectedItem = 9;
    }

    public void onSnakeOilClick( View view) {
        Item item = marketplaceItems[2];

        nameText.setText("Snake Oil");
        availText.setText(Integer.toString(item.getQuantity()));
        invText.setText(Integer.toString(player.getPlayerWagon().getCargo()[2].getQuantity()));
        priceText.setText(Integer.toString(item.getPrice()));

        selectedItem = 2;
    }



}
