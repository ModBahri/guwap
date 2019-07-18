package com.example.guwap.view;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;
import com.example.guwap.R;

public class ConflictResolved extends AppCompatActivity{
    private Button next;
    private TextView win;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    protected void winOnPressed() {
        Intent intent = new Intent(this, MapsActivity.class);
        startActivity(intent);
    }
}
