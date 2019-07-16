package com.example.guwap.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.guwap.R;

//Gabi
public class SheriffEncounterActivity extends AppCompatActivity {
    private Button tipHat2;
    private Button shoot2;
    private Button run2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sheriff_encounter);

        tipHat2 = findViewById(R.id.tip_hat);
        tipHat2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //If low notoriety, Let em pass

                //If high notoriety, Gun Showdown based on RNG
            }
        });

        shoot2 = findViewById(R.id.shoot);
        shoot2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //If low notoriety, Gun Showdown based on RNG; Notoriety increased

                //If high notoriety, Gun Showdown based on RNG; Notoriety increased
            }
        });

        run2 = findViewById(R.id.run);
        run2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //If low notoriety, Escape probability based on RNG; Notoriety increased

                //If high notoriety, Escape probability based on RNG; Notoriety increased
            }
        });
    }
    //tipHat method
    //shoot method
    //run method
}
