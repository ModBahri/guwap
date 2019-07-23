package com.example.guwap.view;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.example.guwap.R;

public class ConflictResolvedActivity extends AppCompatActivity{
    private Button next;
    private TextView win;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        next = findViewById(R.id.proceedButton);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                winOnPressed();
            }
        });
    }

    protected void winOnPressed() {
        Intent intent = new Intent(this, MapsActivity.class);
        startActivity(intent);
    }
}
