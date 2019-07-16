package com.example.guwap.entity;

public abstract class NPC {
    private int health;
    private int damage;
    private Player player;

    public NPC() {
        health = 50;
        if (player.getDifficulty() == Difficulty.BEGINNER) {
            damage = 5;
        } else if (player.getDifficulty() == Difficulty.EASY) {
            damage = 10;
        } else if (player.getDifficulty() == Difficulty.NORMAL) {
            damage = 15;
        } else if (player.getDifficulty() == Difficulty.HARD) {
            damage = 20;
        } else if (player.getDifficulty() == Difficulty.IMPOSSIBLE) {
            damage = 25;
        }
    }

    public int getHealth() {
        return health;
    }

    public int getDamage() {
        return damage;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

}
