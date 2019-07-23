package com.example.guwap.model;

import android.util.Log;

import com.example.guwap.entity.Difficulty;
import com.example.guwap.entity.Region;
import com.example.guwap.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.example.guwap.entity.Universe;
import com.google.firebase.FirebaseError;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static android.content.ContentValues.TAG;

public class PlayerInteractor {

    private DatabaseReference database;
    private DatabaseReference playerDB;
    private DatabaseReference regionDB;
    private DatabaseReference wagonDB;
    private DatabaseReference universeDB;
    private List<Player> playerList;

    /** Singleton Pattern Code
     *  this allows us to get access to this class
     *  anywhere, which will allow our View models to access
     *  the "back end"  more easily
     */
    private static  PlayerInteractor instance = new PlayerInteractor();

    public static PlayerInteractor getInstance() { return instance; }


    /**
     * constructor for PlayerInteractor
     */
    public PlayerInteractor() {
        database = FirebaseDatabase.getInstance().getReference();
        playerDB = database.child("players");
        regionDB = database.child("regions");
        wagonDB = database.child("wagons");
        universeDB = database.child("universes");
        playerList = new ArrayList<>();

    }

    /**
     * Adds player
     * @param name name of player
     * @param difficulty difficulty level
     * @param pilot pilot level
     * @param engineer engineer level
     * @param fighter fighter level
     * @param trader trader level
     */
    public String addPlayer (String name, int difficulty, int pilot, int engineer, int fighter, int trader) {
        //getRepository().addPlayer(p);
        Player player = new Player(name, difficulty, pilot, engineer, fighter, trader);
        playerDB.child(player.getId()).setValue(player);

        player.getRegion().setFid(player.getId());
        regionDB.child(player.getId()).setValue(player.getRegion());

        player.getPlayerWagon().setFid(player.getId());
        wagonDB.child(player.getId()).setValue(player.getPlayerWagon());


        player.getUniverse().setFid(player.getId());
        universeDB.child(player.getId()).setValue(player.getUniverse());


        return player.getId();
    }

    /**
     * Updates player
     * @param player player to update
     */
    public void updatePlayer(Player player) {
        database.child("player").child(player.getId()).setValue(player);
    }

    /**
     * deletes player
     * @param player player to delete
     */
    public void deletePlayer(Player player) {
       database.child("player").child(player.getId()).setValue(null);
    }

    /**
     * Gets player by id
     * @param id id to use
     * @return player with specified id
     */
    public Player getPlayer(String id) {
        for (Player player: playerList) {
            if (id.equals(player.getId())) {
                return player;
            }
        }

        return null;
    }

    /**
     * gets the list of all players
     * @return list of all players
     */
    public List<Player> getPlayerList() {
        return playerList;
    }


}
