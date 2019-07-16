package com.example.guwap.view;

import android.content.Intent;
import android.os.Bundle;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.guwap.R;

/**
 * Main activity
 */
public class MainActivity extends AppCompatActivity {
    private TextView mTextMessage;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        /**
         * Click handler for nav item
         * @param item specific item
         * @return successful?
         */
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_market:
                    mTextMessage.setText("Market");
                    return true;
                case R.id.navigation_map:
                    mTextMessage.setText("Map");
                    return true;
                case R.id.navigation_player:
                    mTextMessage.setText("Player");
                    return true;
            }
            return false;
        }
    };

    /**
     *
     * @param savedInstanceState current state
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        mTextMessage = findViewById(R.id.message);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    public void onClickTravel () {
        Intent intent = new Intent(this, MapsActivity.class);
        startActivity(intent);
    }

    public void onClickMarket() {
        Intent intent = new Intent(this, MarketActivity.class);
        startActivity(intent);
    }

}
