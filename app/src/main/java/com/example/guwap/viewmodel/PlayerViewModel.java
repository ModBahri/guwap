package com.example.guwap.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.guwap.entity.Region;
import com.example.guwap.entity.Player;
import com.example.guwap.model.MarketInteractor;
import com.example.guwap.model.Model;
import com.example.guwap.model.PlayerInteractor;

import java.util.List;


public class PlayerViewModel extends AndroidViewModel {
    private PlayerInteractor model;
    private List<Player> players;
    private MarketInteractor market;

    /**
     * PlayerViewModel constructor
     * @param application current application
     */
    public PlayerViewModel(@NonNull Application application) {
        super(application);
        model = Model.getInstance().getPlayerInteractor();
        players = model.getAllPlayers();

    }

    /**
     * Method to add new player
     * @param player player to add
     */
    public void addPlayer(Player player) {
        model.addPlayer(player);
        players = model.getAllPlayers();
    }

    /**
     * Method to delete player
     * @param player player to delete
     */
    public void deletePlayer(Player player) {
        model.deletePlayer(player);
        players = model.getAllPlayers();
    }

    /**
     * Change region of the player
     * @param player player to update
     * @param region region to update
     */
    public void updateRegion(Player player, Region region) {
        model.updateRegion(player, region);
    }

    /**
     * Return current region
     * @param player
     * @return current region
     */
    public Region getRegion(Player player) {
        return model.getCurrentPlayer().getRegion();
    }

    /**
     * Get current player
     * @return current player
     */
    public Player getPlayer() {
        return model.getCurrentPlayer();
    }


}
