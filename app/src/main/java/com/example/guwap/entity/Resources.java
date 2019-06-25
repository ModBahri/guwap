package com.example.guwap.entity;

import java.util.Random;

public enum Resources {
    NORESOURCES (0), //no resources
    GRASSLAND (1), //steers, horses
    DESERT (2), //peyote,
    FOREST (3), //wood, horses
    OASIS (4), //whiskey
    CAVE (5), //weird mushrooms, coal, gold
    MOUNTAINS (6), //coal, gold
    ABANDONEDTRAINCAR (7), //guns, whiskey, snake oil
    CITY (8), //tobacco, whiskey, snake oil, guns
    TRIBAL (9), //tobacco, peyote
    RICHFAUNA (10), //steers,
    HERBS (11); //snake oil, peyote

    private final int code;

    Resources(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static Resources randResources(){
        Random random = new Random();
        return values()[random.nextInt(values().length)];
    }
}
