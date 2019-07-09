package com.example.guwap.model;

import com.example.guwap.entity.Location;
import com.example.guwap.entity.Player;

import java.util.ArrayList;
import java.util.List;


/**
 * This class is an abstraction of the data storage for the business classes
 * Normally this would passthrough to our ROOM (database) objects.   To keep this assignment
 * simple, we are just using in-memory storage
 */
class Repository {

    /***
     * This provides a mechanism to generate simple unique numbers to be used as
     * keys in the application
     */
    private static int next_id = 1;

    private static int getNextUniqueID() {
        return next_id++;
    }

    /***************************************************************/

    /** all the students known in the application */
    private List<Player> allPlayers;

    /** all the courses known in the application */
    private List<Location> allLocations;

    /** current player */
    private Player current;

    /**
     * Make a new Repository object
     */
    public Repository() {
        allPlayers = new ArrayList<>();
        allLocations = new ArrayList<>();

    }


    /**
     * get all the students in teh system
     * @return list of all students
     */
    public List<Player> getAllPlayers() { return allPlayers;}


    /**
     * get all the courses in the system
     * @return list of all the courses
     */
    public List<Location>  getAllLocations() { return allLocations; }


    /**
     * add a new player
     * @param player player to add
     */
    public void addPlayer(Player player) {
       // player.setId(Repository.getNextUniqueID());
        allPlayers.add(player);
        this.current = player;
    }

    /** add a new location to the system
     *
     * @param location the location to add
     */
    public void addLocation(Location location) {
        //student.setId(Repository.getNextUniqueID());
        allLocations.add(location);
    }

    /**
     * delete a player
     * @param player the player to delete
     */
    public void deletePlayer(Player player) {
        allPlayers.remove(player);
    }

    /**
     * Updates the values stored in a player
     * @param p the student to update
     */
    public void updatePlayer(Player p) {
        /*for (Student student: allStudents) {
            if (student.getId() == s.getId()) {
                student.setMajor(s.getMajor());
                student.setName(s.getName());
                student.setStanding(s.getStanding());
                return;
            }
        }
        Log.d("APP", "Student not found with id = " + s.getId());*/
    }

    /**
     * Updates the values stored in a player
     * @param player the student to update
     * @param location location to set
     */
    public void updateLocation(Player player, Location location) {
        player.setLocation(location);
    }

    public Player getCurrent() {
        return current;
    }


}
