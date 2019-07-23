package com.example.guwap.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.ArrayAdapter;

import com.example.guwap.R;
import com.example.guwap.entity.Difficulty;
import com.example.guwap.entity.Player;
import com.example.guwap.entity.Region;
import com.example.guwap.entity.Universe;
import com.example.guwap.entity.Wagon;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.List;
import java.util.concurrent.CountDownLatch;

import static android.content.ContentValues.TAG;

import java.util.ArrayList;
import java.util.List;


public class LoadActivity extends AppCompatActivity {
    private Spinner characterOptions;
    private Button load;
    private List<Player> players;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final DatabaseReference database = FirebaseDatabase.getInstance().getReference();
        //final DatabaseReference playersRef = database.child("players");
        //DatabaseReference playerRef = playersRef.child(id);


        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                CountDownLatch done = new CountDownLatch(3);
                List<Player> list = new ArrayList<>();
                for (DataSnapshot child : dataSnapshot.child("players").getChildren()) {
                    list.add(child.getValue(Player.class));
                }
                players = list;
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

    public void render() {
        characterOptions = findViewById(R.id.CharacterSpinner);

        //Dummy Data
        final ArrayAdapter<Player> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, players);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        characterOptions.setAdapter(adapter);

        load = findViewById(R.id.LoadButton);
        load.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (characterOptions.getSelectedItem() != null){
                    Intent intent = new Intent(LoadActivity.this, MainActivity.class);
                    intent.putExtra("PLAYER_ID", adapter.getItem(characterOptions.getSelectedItemPosition()).getId());
                    startActivity(intent);
                }

            }
        });
    }
}


