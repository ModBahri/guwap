package com.example.guwap.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.ArrayAdapter;

import com.example.guwap.R;
import com.example.guwap.entity.Player;

public class LoadActivity extends AppCompatActivity {
    private Spinner characterOptions;
    private Button load;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        characterOptions = findViewById(R.id.CharacterSpinner);
        //characterOptions.setOnClickListener(new View.OnClickListener() {
        //            @Override
        //            public void onClick(View v) {
        //
        //            }
        //        });

        //Example
        //ArrayAdapter<Difficulty> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, Difficulty.values());
        //        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //        difficulty.setAdapter(adapter);

        //ArrayAdapter<Player> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, );
        //Name, Difficulty, Current Region??? possible characteristics in spinner
        //adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //characterOptions.setAdapter(adapter);

        load = findViewById(R.id.LoadButton);
        load.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
