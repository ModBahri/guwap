package com.example.guwap.model;

import com.example.guwap.entity.Region;
import com.example.guwap.entity.PeopleType;

import java.util.ArrayList;
import java.util.List;

/*<<<<<<< HEAD
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
=======*/
public abstract class Interactor {

    private Repository myRepository;

    protected Interactor(Repository repo) { myRepository = repo;}

    protected Repository getRepository() { return myRepository; }
}
