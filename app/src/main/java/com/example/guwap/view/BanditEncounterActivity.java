package com.example.guwap.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Intent;
import android.os.Handler;
import android.widget.TextView;

import com.example.guwap.R;
import com.example.guwap.entity.Player;
import com.example.guwap.model.EncounterInteractor;

/**
 * Bandit encounter
 */
public class BanditEncounterActivity extends AppCompatActivity {
    private Button tipHat;
    private Button shoot;
    private Button run;
    private TextView bandit;
    private TextView health;
    private EncounterInteractor encounterInteractor;

    private Player player;

    /**
     * Initializes values on activity opened
     * @param savedInstanceState current state
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bandit_encounter);
        encounterInteractor = new EncounterInteractor(player, 0.5);
        tipHat = findViewById(R.id.tip_hat);
        shoot = findViewById(R.id.shoot);
        run = findViewById(R.id.run);
        bandit = findViewById(R.id.bandit);
        health = findViewById(R.id.health);
        health.setText(player.getHealth());
        //RelativeLayout relative = (RelativeLayout) findViewById(R.id.sheriffIcon);
        //relative.setBackgroundResource(0);

        //ImageView myImage = (ImageView) findViewById(R.id.sheriffIcon);
        //myImage.setAlpha(0);
    }

    public void onTipPressed(View view) {
        encounterInteractor.playerTips();
        encounterInteractor.npcActs();
        if (encounterInteractor.getTip()) {
            bandit.setText("You tipped your hat, and the bandit tipped back!");
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(BanditEncounterActivity.this,
                            ConflictResolvedActivity.class);
                    startActivity(intent);
                }
            }, 2000);
        } else {
            bandit.setText("You tipped your hat, but the bandit ain't having it");
            if (encounterInteractor.getNPCHits()) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        bandit.setText("The bandit takes a shot and hits you!");
                        health.setText(player.getHealth());
                    }
                }, 2000);
            } else {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        bandit.setText("The bandit takes a shot but misses!");
                    }
                }, 2000);
            }
        }
    }

    public void onShootPressed(View view) {
        boolean hit = encounterInteractor.playerShoots();
        if (hit) {
            bandit.setText("You took a shot, and you hit!");
        } else {
            bandit.setText("You took a shot, but you missed!");
        }
        if (encounterInteractor.getNPCDead()) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    bandit.setText("The bandit is dead as a door nail!");
                }
            }, 2000);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(BanditEncounterActivity.this,
                            ConflictResolvedActivity.class);
                    startActivity(intent);
                }
            }, 2000);
        } else {
            encounterInteractor.npcActs();
            if (encounterInteractor.getShoot()){
                if (encounterInteractor.getNPCHits()) {
                    bandit.setText("The bandit takes a shot at you, and hits you!");
                    health.setText(player.getHealth());
                } else {
                    bandit.setText("The bandit takes a shot at you, but misses!");
                }
            }
            if (encounterInteractor.getPlayerDead()) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        bandit.setText("You've become dead meat!");
                    }
                }, 2000);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(BanditEncounterActivity.this,
                                GameOver.class);
                        startActivity(intent);
                    }
                }, 2000);
            }
        }
    }

    public void onRunPressed(View view) {
        encounterInteractor.playerRuns();
        if (!encounterInteractor.getRun()) {
            encounterInteractor.npcActs();
            if (encounterInteractor.getShoot()) {
                bandit.setText("The bandit ain't lettin' you leave his sight");
                if (encounterInteractor.getNPCHits()) {
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            bandit.setText("The bandit takes a shot and hits you!");
                            health.setText(player.getHealth());
                        }
                    }, 2000);
                } else {
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            bandit.setText("The bandit takes a shot but misses!");
                        }
                    }, 2000);
                }
            } else {
                bandit.setText("The bandit stopped you, but let you go!");
            }
        } else {
            bandit.setText("You were able to make yourself scarce!");
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(BanditEncounterActivity.this,
                            MapsActivity.class);
                    startActivity(intent);
                }
            }, 2000);

        }
    }
}
