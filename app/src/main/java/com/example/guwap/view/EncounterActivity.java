package com.example.guwap.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import android.widget.Button;
import android.widget.ImageView;

import com.example.guwap.R;
import com.example.guwap.entity.Player;
import com.example.guwap.entity.Sheriff;
import com.example.guwap.model.EncounterInteractor;
import com.example.guwap.viewmodel.PlayerViewModel;


public class EncounterActivity extends AppCompatActivity {
    /**
     * widgets used
     */
    private Button meet;
    private Button run;
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
        run = findViewById(R.id.run);
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
