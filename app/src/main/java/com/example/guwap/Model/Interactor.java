package com.example.guwap.Model;

import java.util.ArrayList;

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
