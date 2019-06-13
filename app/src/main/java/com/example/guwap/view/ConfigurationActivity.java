package com.example.guwap.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.arch.lifecycle.ViewModelProvider;

import com.example.guwap.R;
import com.example.guwap.entity.Difficulty;
import com.example.guwap.entity.Skills;
import com.example.guwap.model.Player;

public class ConfigurationActivity extends AppCompatActivity {
    /**View model reference*/
    private ConfigurationViewModel viewModel;

    /** Widgets used in this model */
    private TextView nameField;
    private EditText nameField;
    private Spinner difficulty;
    private EditText pilot;
    private EditText engineer;
    private EditText trader;
    private EditText fighter;


    /** Player data */
    private Player player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config);

        /**
         * Grab the dialog widgets for later use
         */
        nameField = findViewById(R.id.);
        difficulty =   findViewById(R.id.);
        pilot = findViewById(R.id);
        engineer = findViewById(R.id);
        trader = findViewById(R.id);
        fighter = findViewById(R.id);

        /**
         * Add text to button
         */
        button.setText("Add");
        pilot.setText("0");
        engineer.setText("0");
        trader.setText("0");
        fighter.setText("0");

        /**
         * Set the viewmodel
         */
        viewModel = ViewModelProvider.of(this).get(ConfigurationViewModel.class);

        difficulty.setSelection(1);
    }

    /**
     * Button handler for the add new player button
     *
     * @param view the button that was pressed
     */
    public void onAddPressed(View view){

        /**
         * Creating variables to hold entered values, so they can be later checked.
         */
        String tName = nameField.getText().toString();
        Difficulty tDifficulty = Difficulty.valueOf(difficulty
                .getSelectedItem()
                .toString()
                .substring(0,1)
                .toUpperCase());
        int tPilot = Integer.parseInt(pilot.getText());
        int tEngineer = Integer.parseInt(engineer.getText());
        int tFighter = Integer.parseInt(fighter.getText());
        int tTrader = Integer.parseInt(trader.getText());

        if (tPilot == null || tEngineer == null || tTrader == null || tFighter == null) {
            throw new Exception("Cannot be null");
        }
        if (tPilot + tEngineer + tTrader + tFighter > 16) {
            throw new Exception("Too many points");
        }
        if (tName == null) {
            throw new Exception("Name must have a value");
        }

        player = new Player(tName, tDifficulty, tPilot, tEngineer, tFighter, tTrader);

        viewModel.addPlayer(player);

        finish();
    }
}
