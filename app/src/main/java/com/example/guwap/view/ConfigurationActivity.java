package com.example.guwap.view;

import android.content.Context;
import android.os.Bundle;
import android.arch.lifecycle.ViewModelProviders;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import com.example.guwap.R;
import com.example.guwap.entity.Difficulty;
import com.example.guwap.model.Player;
import com.example.guwap.viewmodel.ConfigurationViewModel;

public class ConfigurationActivity extends AppCompatActivity {
    /**View model reference*/
    private ConfigurationViewModel viewModel;

    /** Widgets used in this model */
    private TextView nameField;
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

        /*
          Grab the dialog widgets for later use
         */
        nameField = findViewById(R.id.char_editor);
        difficulty = findViewById(R.id.diff_selector);
        pilot = findViewById(R.id.pilot);
        engineer = findViewById(R.id.engineer);
        trader = findViewById(R.id.trader);
        fighter = findViewById(R.id.fighter);

        /*
          Set up adapter to display the difficulty levels in the spinner
         */
        ArrayAdapter<Difficulty> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, Difficulty.values());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        difficulty.setAdapter(adapter);


        /*
          Add default text to buttons
         */
        player = new Player();
        nameField.setText(player.getName());
        difficulty.setSelection(2);
        pilot.setText("0");
        engineer.setText("0");
        trader.setText("0");
        fighter.setText("0");

        /*
          Set the viewmodel
         */
        viewModel = ViewModelProviders.of(this).get(ConfigurationViewModel.class);


    }

    /**
     * Button handler for the add new player button
     *
     * @param view the button that was pressed
     */
    public void onAddPressed(View view) throws Exception{
        Context context = getApplicationContext();
        CharSequence text = "Hello toast!";
        int duration = Toast.LENGTH_SHORT;


        /*
          Creating variables to hold entered values, so they can be later checked.
         */

        String tName = nameField.getText().toString();
        Difficulty tDifficulty = Difficulty.valueOf(difficulty
                .getSelectedItem()
                .toString()
                .toUpperCase());
        int tPilot = Integer.parseInt(pilot.getText().toString());
        int tEngineer = Integer.parseInt(engineer.getText().toString());
        int tFighter = Integer.parseInt(fighter.getText().toString());
        int tTrader = Integer.parseInt(trader.getText().toString());


        if(tPilot + tEngineer + tTrader + tFighter > 16) {
            Toast toast = Toast.makeText(context, "Points cannot total more than 16", duration);
            toast.show();
        } else if (tPilot + tEngineer + tTrader + tFighter < 16) {
            Toast toast = Toast.makeText(context, "Points cannot total less than 16", duration);
            toast.show();
        } else if (tName == null || tName.equals("")) {
            Toast toast = Toast.makeText(context, "Name cannot be blank", duration);
            toast.show();
        } else {
            player = new Player(tName, tDifficulty, tPilot, tEngineer, tFighter, tTrader);

            viewModel.addPlayer(player);

            finish();
        }


    }
}
