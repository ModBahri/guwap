package com.example.guwap.model;

import com.example.guwap.entity.Region;
import com.example.guwap.entity.Player;

import java.util.List;

public class PlayerInteractor extends Interactor {
    public PlayerInteractor(Repository repo) { super (repo);}

    public List<Player> getAllPlayers() { return getRepository().getAllPlayers(); }

    public void addPlayer (Player p) { getRepository().addPlayer(p); }

    public void updatePlayer(Player p) {
        getRepository().updatePlayer(p);
    }

    public void deletePlayer(Player p) {
        getRepository().deletePlayer(p);
    }

    public void updateRegion(Player player, Region region) {
        //getRepository.
    }

    public Player getCurrentPlayer() {
        return getRepository().getCurrent();
    }

}
