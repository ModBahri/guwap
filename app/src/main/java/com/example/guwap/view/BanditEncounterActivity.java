package com.example.guwap.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.content.Intent;
import android.widget.RelativeLayout;

import com.example.guwap.R;
import com.example.guwap.entity.Player;
import com.example.guwap.model.EncounterInteractor;

//Gabi
public class BanditEncounterActivity extends AppCompatActivity {
    private Button tipHat;
    private Button shoot;
    private Button run;
    private ImageView bandit;
    private EncounterInteractor encounterInteractor;

    private Player player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bandit_encounter);
        encounterInteractor = new EncounterInteractor(player, 0.5);
        tipHat = findViewById(R.id.tip_hat);
        shoot = findViewById(R.id.shoot);
        run = findViewById(R.id.run);
        //RelativeLayout relative = (RelativeLayout) findViewById(R.id.sheriffIcon);
        //relative.setBackgroundResource(0);

        //ImageView myImage = (ImageView) findViewById(R.id.sheriffIcon);
        //myImage.setAlpha(0);
    }

    public void onTipPressed(View view) {
        encounterInteractor.playerTips();
        encounterInteractor.npcActs();
        if (encounterInteractor.getTip()) {
            Intent intent = new Intent(this, MapsActivity.class);
            startActivity(intent);
        }
    }

    public void onShootPressed(View view) {
        encounterInteractor.playerShoots();
        if (encounterInteractor.getNPCDead()) {
            Intent intent = new Intent(this, MapsActivity.class);
            startActivity(intent);
        } else {
            encounterInteractor.npcActs();
        }
    }

    public void onRunPressed(View view) {
        encounterInteractor.playerRuns();
        if (!encounterInteractor.getRun()) {
            encounterInteractor.npcActs();
        } else {
            Intent intent = new Intent(this, MapsActivity.class);
            startActivity(intent);
        }
    }
}
