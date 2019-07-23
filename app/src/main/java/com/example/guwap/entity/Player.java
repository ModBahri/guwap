package com.example.guwap.entity;

import android.util.Log;

import com.google.firebase.database.Exclude;

import java.util.Random;
import java.util.UUID;

public class Player{
    private String name;
    private String difficulty;
    private final int INITIAL_SKILL_POINTS = 16;
    private int credits;
    private int pilot, fighter, engineer, trader;
    private Region region;
    private Wagon playerWagon;
    private int damage;
    private int health;
    private int notoriety;
    private String uuid;
    private Universe universe;

    public Player() {
        //this("Name", Difficulty.NORMAL, 0, 0, 0, 0);
    }

    public Player(String name, int difficulty, int pilot, int engineer, int fighter, int trader) {
        Difficulty df =  new Difficulty(difficulty);

        this.name = name;
        this.difficulty = df.getDifficulties().get(difficulty);
        this.pilot = pilot;
        this.engineer = engineer;
        this.trader = trader;
        this.fighter = fighter;
        this.notoriety = 0;
        this.universe = new Universe(1);

        if (difficulty == 0) {
            damage = 20 + fighter;
            health = 100;
            this.playerWagon = new Wagon("Beginner");
        } else if (difficulty == 1) {
            damage = 15 + fighter;
            health = 90;
            this.playerWagon = new Wagon("Easy");
        } else if (difficulty == 2) {
            damage = 10 + fighter;
            health = 80;
            this.playerWagon = new Wagon("Normal");
        } else if (difficulty == 3) {
            damage = 5 + fighter;
            health = 70;
            this.playerWagon = new Wagon("Hard");
        } else if (difficulty == 4) {
            damage = 0 + fighter;
            health = 60;
            this.playerWagon = new Wagon("Impossible");
        }

        Random random = new Random();
        this.region = universe.getRegionArrayList().get(random.nextInt(13));
        this.credits = 1000;


        this.uuid = UUID.randomUUID().toString();

    }

    public void setPilot(int pilot) {
        this.pilot = pilot;
    }

    public void setFighter(int fighter) {
        this.fighter = fighter;
    }

    public int getEngineer() {
        return engineer;
    }

    public void setEngineer(int engineer) {
        this.engineer = engineer;
    }

    public int getTrader() {
        return trader;
    }

    public void setTrader(int trader) {
        this.trader = trader;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public Universe getUniverse() {
        return universe;
    }

    public void setUniverse(Universe universe) {
        this.universe = universe;
    }

    public String getName() {
        return name;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDifficulty(String difficulty) {
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
