package com.example.guwap.entity;

import com.example.guwap.model.Player;

import java.util.ArrayList;
import java.util.List;

public class Interactor {
    private List<Player> allPlayers;

    public Interactor() {
        allPlayers = new ArrayList<Player>();
    }

    public List<Player> getAllPlayers() { return allPlayers; }

    public void addPlayer(Player player) {
        allPlayers.add(player);
    }
}
