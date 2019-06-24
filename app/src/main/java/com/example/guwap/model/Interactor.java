package com.example.guwap.model;

import com.example.guwap.entity.Location;
import com.example.guwap.entity.PeopleType;

import java.util.ArrayList;
import java.util.List;

public class Interactor {
    private List<PeopleType.Player> allPlayers;

    public Interactor() {
        allPlayers = new ArrayList<PeopleType.Player>();
    }

    public List<PeopleType.Player> getAllPlayers() { return allPlayers; }

    public void addPlayer(PeopleType.Player player) {
        allPlayers.add(player);
    }

    public static class NonPlayer implements Location.IEntity {
    }
}
