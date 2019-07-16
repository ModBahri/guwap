package com.example.guwap.model;

import com.example.guwap.entity.Region;
import com.example.guwap.entity.Player;

import java.util.List;

public class PlayerInteractor extends Interactor {
    /**
     * constructor for PlayerInteractor
     * @param repo repository to use
     */
    public PlayerInteractor(Repository repo) { super (repo);}

    /**
     * gets all players in the repo
     * @return
     */
    public List<Player> getAllPlayers() { return getRepository().getAllPlayers(); }

    /**
     * adds player
     * @param p player to add
     */
    public void addPlayer (Player p) { getRepository().addPlayer(p); }

    /**
     * updates player
     * @param p player to update
     */
    public void updatePlayer(Player p) {
        getRepository().updatePlayer(p);
    }

    /**
     * deletes player
     * @param p player to delete
     */
    public void deletePlayer(Player p) {
        getRepository().deletePlayer(p);
    }

    /**
     * updates region
     * @param player player to use
     * @param region region to use
     */
    public void updateRegion(Player player, Region region) {
        //getRepository.
    }

    /**
     * gets current player
     * @return current player
     */
    public Player getCurrentPlayer() {
        return getRepository().getCurrent();
    }

}
