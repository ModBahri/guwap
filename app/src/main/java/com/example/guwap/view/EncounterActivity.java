package com.example.guwap.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.guwap.R;
import com.example.guwap.entity.Player;
import com.example.guwap.entity.Sheriff;
import com.example.guwap.model.EncounterInteractor;
import com.example.guwap.viewmodel.PlayerViewModel;

/**
 * Intermediary screen that decides whether player encounters a bandit or a sheriff
 */
public class EncounterActivity extends AppCompatActivity {
    /**
     * button to confront NPC
     */
    private Button meet;
    /**
     * button to attempt to escape NPC
     */
    private Button run;
    /**
     * image of approaching NPC
     */
    private ImageView challenger;


    /**
     * player data
     */
    private Player player;
    private PlayerViewModel viewModel;
    private EncounterInteractor encounterInteractor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_encounter);
        player = viewModel.getPlayer();
        encounterInteractor = new EncounterInteractor(player);
        meet = findViewById(R.id.meet);
        meet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onMeetPressed();
            }
        });
        run = findViewById(R.id.run);
        run.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onRunPressed();
            }
        });
        challenger = findViewById(R.id.challenger);
    }

    protected void onRunPressed() {
        encounterInteractor.playerRuns();
        if (encounterInteractor.getRun()) {
            Intent intent = new Intent(this, ConflictResolved.class);
            startActivity(intent);
        } else {
            if (encounterInteractor.getNpc() instanceof Sheriff) {
                Intent intent = new Intent(this, SheriffEncounterActivity.class);
                startActivity(intent);
            } else {
                Intent intent = new Intent(this, BanditEncounterActivity.class);
                startActivity(intent);
            }
        }
    }

    protected void onMeetPressed() {
        if (encounterInteractor.getNpc() instanceof Sheriff) {
            Intent intent = new Intent(this, SheriffEncounterActivity.class);
            startActivity(intent);
        } else {
            Intent intent = new Intent(this, BanditEncounterActivity.class);
            startActivity(intent);
        }
    }
}
