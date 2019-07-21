package com.example.guwap.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.ArrayAdapter;

import com.example.guwap.R;
import com.example.guwap.entity.Player;

import java.util.ArrayList;
import java.util.List;

import static com.example.guwap.entity.Difficulty.EASY;
import static com.example.guwap.entity.Difficulty.HARD;
import static com.example.guwap.entity.Difficulty.IMPOSSIBLE;
import static com.example.guwap.entity.Difficulty.NORMAL;

public class LoadActivity extends AppCompatActivity {
    private Spinner characterOptions;
    private Button load;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        characterOptions = findViewById(R.id.CharacterSpinner);
        //Dummy Data
        List<Player> list = new ArrayList<>();
        list.add(new Player("Yukt", HARD, 3, 6, 5, 2));
        list.add(new Player("Mitch", NORMAL, 1, 5, 1, 9));
        list.add(new Player("Matthew", EASY, 0, 0, 0, 16));
        list.add(new Player("WP", IMPOSSIBLE, 8, 1, 1, 6));
        ArrayAdapter<Player> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        characterOptions.setAdapter(adapter);

        load = findViewById(R.id.LoadButton);
        load.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoadActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
