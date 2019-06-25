package com.example.guwap.model;

import com.example.guwap.entity.Location;
import com.example.guwap.entity.Player;

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

    public static class NonPlayer implements Location.IEntity {
    }
}
