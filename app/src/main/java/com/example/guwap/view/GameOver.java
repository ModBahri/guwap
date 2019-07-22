package com.example.guwap.view;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.content.Intent;
import com.example.guwap.R;

public class GameOver extends AppCompatActivity {
    private Button titleScreen;
    private TextView gameOver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        titleScreen = findViewById(R.id.backButton);
        titleScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onTitleScreenPressed();
            }
        });
    }

    protected void onTitleScreenPressed() {
        Intent intent = new Intent(this, TitleScreen.class);
        startActivity(intent);
    }
}
