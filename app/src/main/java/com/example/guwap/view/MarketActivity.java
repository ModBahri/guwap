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
import com.example.guwap.entity.Difficulty;
import com.example.guwap.entity.Item;
import com.example.guwap.entity.MarketPlace;
import com.example.guwap.entity.Player;
import com.example.guwap.entity.Region;
import com.example.guwap.entity.Universe;
import com.example.guwap.entity.Wagon;
import com.example.guwap.viewmodel.PlayerViewModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.List;
import java.util.concurrent.CountDownLatch;

import static android.content.ContentValues.TAG;

public class MarketActivity extends FragmentActivity {
    private Player player;
    private PlayerViewModel viewModel;
    private EditText quantity;
    private List<Item> marketplaceItems;
    private TextView nameText;
    private TextView availText;
    private TextView invText;
    private TextView priceText;
    private TextView yourGoldText;
    private int selectedItem;
    private MarketPlace marketPlace;

    /**
     * Method that runs when activity is started
     * @param savedInstanceState current state
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        final DatabaseReference database = FirebaseDatabase.getInstance().getReference();
        //final DatabaseReference playersRef = database.child("players");
        //DatabaseReference playerRef = playersRef.child(id);


        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                CountDownLatch done = new CountDownLatch(1);
                String id = getIntent().getStringExtra("PLAYER_ID");
                player = dataSnapshot.child("players").child(id).getValue(Player.class);
                player.setRegion(dataSnapshot.child("regions").child(id).getValue(Region.class));
                player.setPlayerWagon(dataSnapshot.child("wagons").child(id).getValue(Wagon.class));
                player.setUniverse(dataSnapshot.child("universes").child(id).getValue(Universe.class));
                done.countDown();
                render();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException());
                // ...
            }
        });


    }

    public void render() {
        setContentView(R.layout.activity_market);
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
        yourGoldText.setText(Integer.toString(player.getCredits()));

        Log.i("Player name", player.getName());
    }

    /**
     * Buy button click handler
     * @param view selected object
     */
    public void onBuyClick (View view) {
        int quantint = Integer.parseInt(quantity.getText().toString());
        if(marketPlace.buyItem(player, selectedItem , quantint)){
            availText.setText(Integer.toString(marketplaceItems.get(selectedItem).getQuantity()));
            invText.setText(Integer.toString(player.getPlayerWagon().getCargo().get(selectedItem).getQuantity()));
            quantity.setText("");
            yourGoldText.setText(Integer.toString(player.getCredits()));
        } else {
            Context context = getApplicationContext();
            int duration = Toast.LENGTH_LONG;
            Toast toast = Toast.makeText(context, "Hey! What do you think you are trying to pull? I'll shoot ya if you walk in here without enough money or try to run off with all our stock!", duration);
            toast.show();
        }

    }

    /**
     * Sell button click handler
     * @param view selected object
     */
    public void onSellClick (View view) {
        int quantint = Integer.parseInt(quantity.getText().toString());
        if(marketPlace.sellItem(player, selectedItem , quantint)){
            availText.setText(Integer.toString(marketplaceItems.get(selectedItem).getQuantity()));
            invText.setText(Integer.toString(player.getPlayerWagon().getCargo().get(selectedItem).getQuantity()));
            quantity.setText("");
            yourGoldText.setText(Integer.toString(player.getCredits()));
        } else {
            Context context = getApplicationContext();
            int duration = Toast.LENGTH_LONG;
            Toast toast = Toast.makeText(context, "You aren't too bright are ya? Trying to sell me more than you have..", duration);
            toast.show();
        }

    }

    /**
     * click handler for travel
     * @param item selected menu item
     */
    public void onClickTravel (MenuItem item) {
        Intent intent = new Intent(this, MapsActivity.class);
        intent.putExtra("PLAYER_ID", player.getId());
        startActivity(intent);
    }

    /**
     * click handler for market menu item
     * @param item selected menu item
     */
    public void onClickMarket (MenuItem item) {
        Intent intent = new Intent(this, MarketActivity.class);
        intent.putExtra("PLAYER_ID", player.getId());
        startActivity(intent);
    }

    /**
     * Click handler for store item
     * @param view selected store item
     */
    public void onWhiskeyClick(View view) {
        Item item = marketplaceItems.get(3);

        nameText.setText("Whiskey");
        availText.setText(Integer.toString(item.getQuantity()));
        invText.setText(Integer.toString(player.getPlayerWagon().getCargo().get(3).getQuantity()));
        priceText.setText(Integer.toString(item.getPrice()));

        selectedItem = 3;
    }

    /**
     * Click handler for store item
     * @param view selected store item
     */
    public void onWoodClick(View view) {
        Item item = marketplaceItems.get(6);

        nameText.setText("Wood");
        availText.setText(Integer.toString(item.getQuantity()));
        invText.setText(Integer.toString(player.getPlayerWagon().getCargo().get(6).getQuantity()));
        priceText.setText(Integer.toString(item.getPrice()));

        selectedItem = 6;
    }

    /**
     * Click handler for store item
     * @param view selected store item
     */
    public void onChewClick(View view) {
        Item item = marketplaceItems.get(7);

        nameText.setText("Chew");
        availText.setText(Integer.toString(item.getQuantity()));
        invText.setText(Integer.toString(player.getPlayerWagon().getCargo().get(7).getQuantity()));
        priceText.setText(Integer.toString(item.getPrice()));

        selectedItem = 7;
    }

    /**
     * Click handler for store item
     * @param view selected store item
     */
    public void onMushroomClick(View view) {
        Item item = marketplaceItems.get(11);

        nameText.setText("Strange Mushrooms");
        availText.setText(Integer.toString(item.getQuantity()));
        invText.setText(Integer.toString(player.getPlayerWagon().getCargo().get(11).getQuantity()));
        priceText.setText(Integer.toString(item.getPrice()));

        selectedItem = 11;
    }

    /**
     * Click handler for store item
     * @param view selected store item
     */
    public void onPeyoteClick(View view) {
        Item item = marketplaceItems.get(8);

        nameText.setText("Peyote");
        availText.setText(Integer.toString(item.getQuantity()));
        invText.setText(Integer.toString(player.getPlayerWagon().getCargo().get(8).getQuantity()));
        priceText.setText(Integer.toString(item.getPrice()));

        selectedItem = 8;
    }

    /**
     * Click handler for store item
     * @param view selected store item
     */
    public void onGoldClick(View view) {
        Item item = marketplaceItems.get(5);

        nameText.setText("Gold");
        availText.setText(Integer.toString(item.getQuantity()));
        invText.setText(Integer.toString(player.getPlayerWagon().getCargo().get(5).getQuantity()));
        priceText.setText(Integer.toString(item.getPrice()));

        selectedItem = 5;
    }

    /**
     * Click handler for store item
     * @param view selected store item
     */
    public void onChickenClick(View view) {
        Item item = marketplaceItems.get(4);

        nameText.setText("Chicken Wing");
        availText.setText(Integer.toString(item.getQuantity()));
        invText.setText(Integer.toString(player.getPlayerWagon().getCargo().get(4).getQuantity()));
        priceText.setText(Integer.toString(item.getPrice()));

        selectedItem = 4;
    }

    /**
     * Click handler for store item
     * @param view selected store item
     */
    public void onHorseClick(View view) {
        Item item = marketplaceItems.get(1);

        nameText.setText("Horse");
        availText.setText(Integer.toString(item.getQuantity()));
        invText.setText(Integer.toString(player.getPlayerWagon().getCargo().get(1).getQuantity()));
        priceText.setText(Integer.toString(item.getPrice()));

        selectedItem = 1;
    }

    /**
     * Click handler for store item
     * @param view selected store item
     */
    public void onSteerClick(View view) {
        Item item = marketplaceItems.get(0);

        nameText.setText("Steer");
        availText.setText(Integer.toString(item.getQuantity()));
        invText.setText(Integer.toString(player.getPlayerWagon().getCargo().get(0).getQuantity()));
        priceText.setText(Integer.toString(item.getPrice()));

        selectedItem = 0;
    }

    /**
     * Click handler for store item
     * @param view selected store item
     */
    public void onCoalClick(View view) {
        Item item = marketplaceItems.get(9);

        nameText.setText("Coal");
        availText.setText(Integer.toString(item.getQuantity()));
        invText.setText(Integer.toString(player.getPlayerWagon().getCargo().get(9).getQuantity()));
        priceText.setText(Integer.toString(item.getPrice()));

        selectedItem = 9;
    }

    /**
     * Click handler for store item
     * @param view selected store item
     */
    public void onSnakeOilClick( View view) {
        Item item = marketplaceItems.get(2);

        nameText.setText("Snake Oil");
        availText.setText(Integer.toString(item.getQuantity()));
        invText.setText(Integer.toString(player.getPlayerWagon().getCargo().get(2).getQuantity()));
        priceText.setText(Integer.toString(item.getPrice()));

        selectedItem = 2;
    }



}
