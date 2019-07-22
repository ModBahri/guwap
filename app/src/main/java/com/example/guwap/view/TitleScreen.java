package com.example.guwap.view;


import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.content.Intent;
import com.example.guwap.R;

public class TitleScreen extends AppCompatActivity {
    private Button newGame;
    private Button loadGame;
    private TextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        newGame = findViewById(R.id.NewButton);
        newGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onNewGamePressed();
            }
        });
        loadGame = findViewById(R.id.SavedButton);
        loadGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onLoadGamePressed();
            }
        });
    }

    protected void onNewGamePressed() {
        Intent intent = new Intent(this, ConfigurationActivity.class);
        startActivity(intent);
    }

    protected void onLoadGamePressed() {
        Intent intent = new Intent(this, LoadActivity.class);
        startActivity(intent);
    }
}
