package com.example.guwap.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.guwap.R;
//Gabi
public class BanditEncounterActivity extends AppCompatActivity {
    private Button tipHat;
    private Button shoot;
    private Button run;
    private ImageView sheriffIcon;

    /**
     * Initializes values on activity opened
     * @param savedInstanceState current state
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bandit_encounter);

        tipHat = findViewById(R.id.tip_hat);
        tipHat.setOnClickListener(new View.OnClickListener(){
            /**
             * Click handler for "tip hat"
             * @param v selected object
             */
            @Override
            public void onClick(View v){
                //If low notoriety, Get shook down by bandit

                //If high notoriety, Let em pass
            }
        });

        shoot = findViewById(R.id.shoot);
        shoot.setOnClickListener(new View.OnClickListener() {
            /**
             * click handler for "shoot"
             * @param v selected object
             */
            @Override
            public void onClick(View v) {
                //If low notoriety, Gun Showdown based on RNG; Notoriety increased Check????

                //If high notoriety, Gun Showdown based on RNG; Notoriety increased
            }
        });

        run = findViewById(R.id.run);
        run.setOnClickListener(new View.OnClickListener() {
            /**
             * click handler for "run"
             * @param v selected view
             */
            @Override
            public void onClick(View v) {
                //If low notoriety, Escape probability based on RNG

                //If high notoriety,  Escape probability based on RNG
            }
        });

        //RelativeLayout relative = (RelativeLayout) findViewById(R.id.sheriffIcon);
        //relative.setBackgroundResource(0);

        //ImageView myImage = (ImageView) findViewById(R.id.sheriffIcon);
        //myImage.setAlpha(0);
    }

    //tipHat method
    //shoot method
    //run method
}
