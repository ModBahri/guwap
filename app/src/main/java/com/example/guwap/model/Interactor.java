package com.example.guwap.model;

import com.example.guwap.entity.Location;
import com.example.guwap.entity.PeopleType;

import java.util.ArrayList;
import java.util.List;

public abstract class Interactor {
    /*private List<PeopleType.Player> allPlayers;
    private PeopleType.Player current;

    public setCurrentPlayer(PeopleType.Player current){ this.current = current; }

    public Interactor() {
        allPlayers = new ArrayList<PeopleType.Player>();
    }

    public List<PeopleType.Player> getAllPlayers() { return allPlayers; }

    public void addPlayer(PeopleType.Player player) {
        allPlayers.add(player);
    }

    public void travel(Location location) {current.setLocation(location);}

    public static class NonPlayer implements Location.IEntity {
    }
    */

    private Repository myRepository;

    protected Interactor(Repository repo) { myRepository = repo;}

    protected Repository getRepository() { return myRepository; }
}
