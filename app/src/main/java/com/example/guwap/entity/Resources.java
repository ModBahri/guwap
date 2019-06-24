package com.example.guwap.entity;

import java.util.Random;

public enum Resources {
    NORESOURCES (0),
    STEERS (1),
    HORSES (2),
    GOLD (3),
    FOREST (4),
    GRASSLAND (5),
    DESERT (6),
    OASIS (7),
    GHOSTTOWN (8),
    PEYOTE (9),
    WEIRDMUSHROOMS (10),
    IRON (11),
    SILVER (12);

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
