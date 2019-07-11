package com.example.guwap.entity;

import android.util.Log;

import java.util.Random;

import static com.example.guwap.entity.Universe.regionArrayList;

public class Player{
    private String name;
    private Difficulty difficulty;
    private final int INITIAL_SKILL_POINTS = 16;
    private int credits;
    private int pilot, fighter, engineer, trader;
    private Region region;
    private Wagon playerWagon;

    public Player() {
        this("Name", Difficulty.NORMAL, 0, 0, 0, 0);
    }

    public Player(String name, Difficulty difficulty, int pilot, int engineer, int fighter, int trader) {
        this.name = name;
        this.difficulty = difficulty;
        this.pilot = pilot;
        this.engineer = engineer;
        this.trader = trader;
        this.fighter = fighter;

        Random random = new Random();
        this.region = regionArrayList.get(random.nextInt(13));
        this.credits = 1000;
        Log.i("Information: ", "Player name: " + this.name
                + "\n Difficulty: " + difficulty.toString()
                + "\n pilot: " + this.pilot
                + "\n engineer: " + this.engineer
                + "\n fighter: " + this.fighter
                + "\n trader: " + this.trader   );

        this.playerWagon = new Wagon(difficulty);

    }


    public String getName() {
        return name;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    public void setRegion(Region region) { this.region = region;}

    public String getPeopleType() { return this.difficulty.toString();}

    public Wagon getPlayerWagon() {
        return playerWagon;
    }

    public void setPlayerWagon(Wagon playerWagon) {
        this.playerWagon = playerWagon;
    }

    public Region getLocation() { return this.region; }

    public int getCredits() {
        return this.credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }
}
