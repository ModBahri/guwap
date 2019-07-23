package com.example.guwap.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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

//Gabi
public class SheriffEncounterActivity extends FragmentActivity {
    private Button tipHat;
    private Button shoot;
    private Button run;
    private TextView sheriff;
    private TextView health;
    private EncounterInteractor encounterInteractor;
    private DatabaseReference database;

    private Player player;

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
        sheriff = findViewById(R.id.sheriff);
        health = findViewById(R.id.health);
        health.setText(player.getHealth());
    }

    public void onTipPressed(View view) {
        encounterInteractor.playerTips();
        encounterInteractor.npcActs();
        if (encounterInteractor.getTip()) {
            sheriff.setText("You tipped your hat, and the sheriff tipped back!");
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(SheriffEncounterActivity.this,
                            ConflictResolvedActivity.class);
                    intent.putExtra("PLAYER_ID", player.getId());
                    database.child("players").child(player.getId()).setValue(player);
                    database.child("wagons").child(player.getId()).setValue(player.getPlayerWagon());
                    database.child("universes").child(player.getId()).setValue(player.getUniverse());
                    startActivity(intent);
                }
            }, 2000);
        } else {
            sheriff.setText("You tipped your hat, but the sheriff ain't having it");
            if (encounterInteractor.getNPCHits()) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        sheriff.setText("The sheriff takes a shot and hits you!");
                        health.setText(player.getHealth());
                    }
                }, 2000);
            } else {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        sheriff.setText("The sheriff takes a shot but misses!");
                    }
                }, 2000);
            }
        }
    }

    public void onShootPressed(View view) {
        boolean hit = encounterInteractor.playerShoots();
        if (hit) {
            sheriff.setText("You took a shot, and you hit!");
        } else {
            sheriff.setText("You took a shot, but you missed!");
        }
        if (encounterInteractor.getNPCDead()) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    sheriff.setText("The sheriff is dead as a door nail!");
                }
            }, 2000);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(SheriffEncounterActivity.this,
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
                    sheriff.setText("The sheriff takes a shot at you, and hits you!");
                    health.setText(player.getHealth());
                } else {
                    sheriff.setText("The sheriff takes a shot at you, but misses!");
                }
            }
            if (encounterInteractor.getPlayerDead()) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        sheriff.setText("You've become dead meat!");
                    }
                }, 2000);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(SheriffEncounterActivity.this,
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

    public void onRunPressed(View view) {
        encounterInteractor.playerRuns();
        if (!encounterInteractor.getRun()) {
            encounterInteractor.npcActs();
            if (encounterInteractor.getShoot()) {
                sheriff.setText("The sheriff ain't lettin' you leave his sight");
                if (encounterInteractor.getNPCHits()) {
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            sheriff.setText("The sheriff takes a shot and hits you!");
                            health.setText(player.getHealth());
                        }
                    }, 2000);
                } else {
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            sheriff.setText("The sheriff takes a shot but misses!");
                        }
                    }, 2000);
                }
            } else {
                sheriff.setText("The sheriff stopped you, but let you go!");
            }
        } else {
            sheriff.setText("You were able to make yourself scarce!");
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(SheriffEncounterActivity.this,
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
    @Override
    public void onDestroy()
    {
        super.onDestroy();
        Fragment fragment = getSupportFragmentManager().getPrimaryNavigationFragment();//.findFragmentById(R.id.nmap);//getFragmentManager().findFragmentById(R.id.nav_view);
        FragmentTransaction ft = this.getSupportFragmentManager().beginTransaction();
        ft.remove(fragment);
        ft.commit();
    }
}
