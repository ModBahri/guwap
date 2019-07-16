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

    /**
     * Interactor constructor
     * @param repo repository to use
     */
    protected Interactor(Repository repo) { myRepository = repo;}

    /**
     * Repository getter
     * @return repository used
     */
    protected Repository getRepository() { return myRepository; }
}
