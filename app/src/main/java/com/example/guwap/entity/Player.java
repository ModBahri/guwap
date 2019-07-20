package com.example.guwap.entity;

import android.util.Log;

import com.google.firebase.database.Exclude;

import java.util.Random;
import java.util.UUID;

import static com.example.guwap.entity.Universe.regionArrayList;

public class Player{
    private String name;
    private Difficulty difficulty;
    private final int INITIAL_SKILL_POINTS = 16;
    private int credits;
    private int pilot, fighter, engineer, trader;
    private Region region;
    private Wagon playerWagon;
    private int damage;
    private int health;
    private int notoriety;
    private String uuid;

    public Player() {
        //this("Name", Difficulty.NORMAL, 0, 0, 0, 0);
    }

    public Player(String name, Difficulty difficulty, int pilot, int engineer, int fighter, int trader) {
        this.name = name;
        this.difficulty = difficulty;
        this.pilot = pilot;
        this.engineer = engineer;
        this.trader = trader;
        this.fighter = fighter;
        this.notoriety = 0;

        if (difficulty == Difficulty.BEGINNER) {
            damage = 20 + fighter;
            health = 100;
        } else if (difficulty == Difficulty.EASY) {
            damage = 15 + fighter;
            health = 90;
        } else if (difficulty == Difficulty.NORMAL) {
            damage = 10 + fighter;
            health = 80;
        } else if (difficulty == Difficulty.HARD) {
            damage = 5 + fighter;
            health = 70;
        } else if (difficulty == Difficulty.IMPOSSIBLE) {
            damage = 0 + fighter;
            health = 60;
        }

        Random random = new Random();
        this.region = regionArrayList.get(random.nextInt(13));
        this.credits = 1000;
        Log.i("Information: ", "Player name: " + this.name
                + "\n Difficulty: " + difficulty.toString()
                + "\n pilot: " + this.pilot
                + "\n engineer: " + this.engineer
                + "\n fighter: " + this.fighter
                + "\n trader: " + this.trader
                + "\n health: " + this.health);

        this.playerWagon = new Wagon(difficulty);

        this.uuid = UUID.randomUUID().toString();

    }


    public String getName() {
        return name;
    }

    @Exclude
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

    @Exclude
    public Wagon getPlayerWagon() {
        return playerWagon;
    }

    public void setPlayerWagon(Wagon playerWagon) {
        this.playerWagon = playerWagon;
    }

    @Exclude
    public Region getRegion() { return this.region; }

    public int getCredits() {
        return this.credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage() {
        this.damage = damage;
    }

    public int getNotoriety() {
        return notoriety;
    }

    public int getINITIAL_SKILL_POINTS() {
        return INITIAL_SKILL_POINTS;
    }

    public void setNotoriety(int notoriety) {
        this.notoriety = notoriety;
    }

    public int getPilot() {
        return pilot;
    }

    public int getFighter() {
        return fighter;
    }

    public String getId() { return this.uuid; }
}
