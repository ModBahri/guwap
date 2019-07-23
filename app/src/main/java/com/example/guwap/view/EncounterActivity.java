package com.example.guwap.view;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.guwap.R;
import com.example.guwap.entity.Player;
import com.example.guwap.entity.Region;
import com.example.guwap.entity.Sheriff;
import com.example.guwap.entity.Universe;
import com.example.guwap.entity.Wagon;
import com.example.guwap.model.EncounterInteractor;
import com.example.guwap.viewmodel.PlayerViewModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.concurrent.CountDownLatch;

import static android.content.ContentValues.TAG;

/**
 * Intermediary screen that decides whether player encounters a bandit or a sheriff
 */
public class EncounterActivity extends FragmentActivity {
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
     DatabaseReference database;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        database = FirebaseDatabase.getInstance().getReference();
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

    public void render(){
        setContentView(R.layout.activity_encounter);
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
            Intent intent = new Intent(this, ConflictResolvedActivity.class);
            intent.putExtra("PLAYER_ID", player.getId());
            database.child("players").child(player.getId()).setValue(player);
            database.child("wagons").child(player.getId()).setValue(player.getPlayerWagon());
            database.child("universes").child(player.getId()).setValue(player.getUniverse());
            startActivity(intent);
        } else {
            if (encounterInteractor.getNpc() instanceof Sheriff) {
                Intent intent = new Intent(this, SheriffEncounterActivity.class);
                intent.putExtra("PLAYER_ID", player.getId());
                database.child("players").child(player.getId()).setValue(player);
                database.child("wagons").child(player.getId()).setValue(player.getPlayerWagon());
                database.child("universes").child(player.getId()).setValue(player.getUniverse());
                startActivity(intent);
            } else {
                Intent intent = new Intent(this, BanditEncounterActivity.class);
                intent.putExtra("PLAYER_ID", player.getId());
                database.child("players").child(player.getId()).setValue(player);
                database.child("wagons").child(player.getId()).setValue(player.getPlayerWagon());
                database.child("universes").child(player.getId()).setValue(player.getUniverse());
                startActivity(intent);
            }
        }
    }

    protected void onMeetPressed() {
        if (encounterInteractor.getNpc() instanceof Sheriff) {
            Intent intent = new Intent(this, SheriffEncounterActivity.class);
            intent.putExtra("PLAYER_ID", player.getId());
            database.child("players").child(player.getId()).setValue(player);
            database.child("wagons").child(player.getId()).setValue(player.getPlayerWagon());
            database.child("universes").child(player.getId()).setValue(player.getUniverse());
            startActivity(intent);
        } else {
            Intent intent = new Intent(this, BanditEncounterActivity.class);
            intent.putExtra("PLAYER_ID", player.getId());
            database.child("players").child(player.getId()).setValue(player);
            database.child("wagons").child(player.getId()).setValue(player.getPlayerWagon());
            database.child("universes").child(player.getId()).setValue(player.getUniverse());
            startActivity(intent);
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
