package com.example.guwap.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import android.widget.Button;

import com.example.guwap.entity.Player;
import com.example.guwap.viewmodel.PlayerViewModel;


public class EncounterActivity extends AppCompatActivity {
    /**
     * widgets used
     */
    private Button tipHat;
    private Button shoot;
    private Button run;

    /**
     * player data
     */
    private Player player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

}
