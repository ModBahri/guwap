package com.example.guwap.entity;
import com.example.guwap.model.PlayerInteractor;

public abstract class NPC {
    private int health;
    private int damage;

    public NPC() {
        health = 50;
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
