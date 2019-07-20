package com.example.guwap.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.guwap.entity.Difficulty;
import com.example.guwap.entity.Player;
import com.example.guwap.model.PlayerInteractor;
import com.google.firebase.database.DatabaseReference;

import java.util.List;


public class PlayerViewModel extends AndroidViewModel {
    private PlayerInteractor model;
    private List<Player> playerList;
    private Player player;
    private DatabaseReference databaseReference;

    /**
     * PlayerViewModel constructor
     * @param application current application
     */
    public PlayerViewModel(@NonNull Application application) {
        super(application);
        model = PlayerInteractor.getInstance();
    }

    /**
     * Method to add new player
     * @param player player to add
     */
    public String addPlayer(String name, Difficulty difficulty, int pilot, int engineer, int fighter, int trader) {
       return model.addPlayer(name, difficulty, pilot, engineer, fighter, trader);
    }

    /**
     * Method to delete player
     * @param player player to delete
     */
    public void deletePlayer(Player player) {
        model.deletePlayer(player);
    }


    /**
     * Get current player
     * @return current player
     */
    public Player getPlayer(String id) {
        return model.getPlayer(id);
    }


}
