package com.example.guwap.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.content.Intent;
import android.os.Handler;
import android.widget.TextView;

import com.example.guwap.R;
import com.example.guwap.entity.Player;
import com.example.guwap.entity.Region;
import com.example.guwap.entity.Universe;
import com.example.guwap.entity.Wagon;
import com.example.guwap.model.EncounterInteractor;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.concurrent.CountDownLatch;

import static android.content.ContentValues.TAG;

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
    private DatabaseReference database;

    private Player player;

    /**
     * Initializes values on activity opened
     * @param savedInstanceState current state
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        database = FirebaseDatabase.getInstance().getReference();
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



        //RelativeLayout relative = (RelativeLayout) findViewById(R.id.sheriffIcon);
        //relative.setBackgroundResource(0);

        //ImageView myImage = (ImageView) findViewById(R.id.sheriffIcon);
        //myImage.setAlpha(0);
    }

    public void render() {
        setContentView(R.layout.activity_bandit_encounter);
        encounterInteractor = new EncounterInteractor(player, 0.5);
        tipHat = findViewById(R.id.tip_hat);
        shoot = findViewById(R.id.shoot);
        run = findViewById(R.id.run);
        bandit = findViewById(R.id.bandit);
        health = findViewById(R.id.health);
        health.setText(player.getHealth());
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
                    intent.putExtra("PLAYER_ID", player.getId());
                    database.child("players").child(player.getId()).setValue(player);
                    database.child("wagons").child(player.getId()).setValue(player.getPlayerWagon());
                    database.child("universes").child(player.getId()).setValue(player.getUniverse());
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
                    intent.putExtra("PLAYER_ID", player.getId());
                    database.child("players").child(player.getId()).setValue(player);
                    database.child("wagons").child(player.getId()).setValue(player.getPlayerWagon());
                    database.child("universes").child(player.getId()).setValue(player.getUniverse());
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
                        intent.putExtra("PLAYER_ID", player.getId());
                        database.child("players").child(player.getId()).setValue(player);
                        database.child("wagons").child(player.getId()).setValue(player.getPlayerWagon());
                        database.child("universes").child(player.getId()).setValue(player.getUniverse());
                        startActivity(intent);
                    }
                }, 2000);
            }
        }
    }

    @Override
    public void onDestroy()
    {
        super.onDestroy();
        Fragment fragment = getSupportFragmentManager().getPrimaryNavigationFragment();//.findFragmentById(R.id.nmap);//getFragmentManager().findFragmentById(R.id.nav_view);
        FragmentTransaction ft = this.getSupportFragmentManager().beginTransaction();
        ft.remove(fragment);
        ft.commit();
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
                    intent.putExtra("PLAYER_ID", player.getId());
                    database.child("players").child(player.getId()).setValue(player);
                    database.child("wagons").child(player.getId()).setValue(player.getPlayerWagon());
                    database.child("universes").child(player.getId()).setValue(player.getUniverse());
                    startActivity(intent);
                }
            }, 2000);

        }
    }
}
