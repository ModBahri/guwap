package com.example.guwap.entity;

import android.util.Log;

import java.util.Random;

public enum PeopleType {
    TROGOLODYTE (0),
    BANDIT (1),
    AMERICAN (2),
    MEXICAN (3),
    CHEYENNE (4),
    APACHE (5),
    COMANCHE (6),
    NAVAJO (7);

    private final int code;

    PeopleType(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static PeopleType randPeopleType() {
        Random random = new Random();
        return values()[random.nextInt(values().length)];
    }

    public static class Player implements Location.IEntity {
        private String name;
        private Difficulty difficulty;
        private final int INITIAL_SKILL_POINTS = 16;
        private final int INITIAL_CREDITS = 1000;
        private int pilot, fighter, engineer, trader;

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
            Log.i("Information: ", "Player name: " + this.name
                    + "\n Difficulty: " + difficulty.toString()
                    + "\n pilot: " + this.pilot
                    + "\n engineer: " + this.engineer
                    + "\n fighter: " + this.fighter
                    + "\n trader: " + this.trader   );
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

        public String getPeopleType() { return this.difficulty.toString();}

    }
}
