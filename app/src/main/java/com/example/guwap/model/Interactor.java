package com.example.guwap.model;

import com.example.guwap.entity.Location;
import com.example.guwap.entity.PeopleType;

import java.util.ArrayList;
import java.util.List;

public class Interactor {
    private List<PeopleType> allPlayers;

    public Interactor() {
        allPlayers = new ArrayList<PeopleType>();
    }

    public List<PeopleType > getAllPlayers() { return allPlayers; }

    public void addPlayer(PeopleType player) {
        allPlayers.add(player);
    }

    public static class NonPlayer implements Location.IEntity {
    }
}
