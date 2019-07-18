package com.example.guwap.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.guwap.entity.Region;
import com.example.guwap.entity.Player;
import com.example.guwap.model.Model;
import com.example.guwap.model.PlayerInteractor;

import java.util.List;

public class PlayerViewModel extends AndroidViewModel {
    private PlayerInteractor model;
    private List<Player> players;

    public PlayerViewModel(@NonNull Application application) {
        super(application);
        model = Model.getInstance().getPlayerInteractor();
        players = model.getAllPlayers();

    }

    public void addPlayer(Player player) {
        model.addPlayer(player);
        players = model.getAllPlayers();
    }

    public void deletePlayer(Player player) {
        model.deletePlayer(player);
        players = model.getAllPlayers();
    }

    public void updateRegion(Player player, Region region) {
        model.updateRegion(player, region);
    }

    public Region getRegion(Player player) {
        return model.getCurrentPlayer().getRegion();
    }

    public Player getPlayer() {
        return model.getCurrentPlayer();
    }


}
