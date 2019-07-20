package com.example.guwap.view;

import android.os.Bundle;
import android.util.Log;

import com.example.guwap.R;
import com.example.guwap.entity.Player;
import com.example.guwap.model.PlayerInteractor;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

public class LoadActivity {
    private DatabaseReference database;
    private List<Player> playerList;
    private PlayerInteractor model;

    protected void onCreate(Bundle savedInstanceState) {
       /* super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load);
        model = PlayerInteractor.getInstance();

        playerList = model.getPlayerList();



*/

    }
}
