package com.example.guwap.model;

import com.example.guwap.entity.Bandit;
import com.example.guwap.entity.Difficulty;
import com.example.guwap.entity.NPC;
import com.example.guwap.entity.Player;
import com.example.guwap.entity.Sheriff;

/**
 * Encounter interactor in the model
 */

public class EncounterInteractor {
    private Player player;
    private NPC npc;
    private double affiliation;
    private int notorietyModifier;
    private boolean shoot, tip, run;

    public EncounterInteractor(Player player) {
        this.player = player;
        affiliation = Math.random();
        shoot = false;
        tip = false;
        run = false;
        if (affiliation <= 0.5) {
            npc = new Bandit();
        } else {
            npc = new Sheriff();
        }
        if (player.getDifficulty() == Difficulty.BEGINNER) {
            notorietyModifier = 5;
        } else if (player.getDifficulty() == Difficulty.EASY) {
            notorietyModifier = 10;
        } else if (player.getDifficulty() == Difficulty.NORMAL) {
            notorietyModifier = 15;
        } else if (player.getDifficulty() == Difficulty.HARD) {
            notorietyModifier = 20;
        } else if (player.getDifficulty() == Difficulty.IMPOSSIBLE) {
            notorietyModifier = 25;
        }
    }

    public void playerShoots() {
        double toHit = Math.random() * 100;
        if (npc instanceof Sheriff) {
            player.setNotoriety(player.getNotoriety() + notorietyModifier);
        } else {
            player.setNotoriety(player.getNotoriety() - notorietyModifier);
        }
        if (toHit >= 50 - player.getFighter()) {
            npc.setHealth(npc.getHealth() - player.getDamage());
        }
    }

    public void playerTips() {
        if (npc instanceof Sheriff) {
            player.setNotoriety(player.getNotoriety() - notorietyModifier);
        } else {
            player.setNotoriety(player.getNotoriety() + notorietyModifier);
        }
    }

    public void playerRuns() {
        double toRun = Math.random() * 100;
        if (toRun >= 75 - player.getPilot() * 4) {
            run = true;
        }
    }

    public void npcActs() {
        shoot = false;
        tip = false;
        if (npc instanceof Sheriff) {
            if (player.getNotoriety() >= 50) {
                npcShoots();
            } else {
                npcTips();
            }
        } else {
            if (player.getNotoriety() < 50) {
                npcShoots();
            } else {
                npcTips();
            }
        }
    }

    public void npcTips() {
        tip = true;
    }

    public void npcShoots() {
        double toHit = Math.random() * 100;
        if (toHit >= 50 + player.getPilot() * 3) {
            player.setHealth(player.getHealth() - npc.getDamage());
        }
        shoot = true;
    }

    public boolean getShoot() {
        return shoot;
    }

    public boolean getTip() {
        return tip;
    }

    public boolean getRun() {
        return run;
    }
}
