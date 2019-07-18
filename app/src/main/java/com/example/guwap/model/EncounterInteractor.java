package com.example.guwap.model;

import com.example.guwap.entity.Bandit;
import com.example.guwap.entity.Difficulty;
import com.example.guwap.entity.NPC;
import com.example.guwap.entity.Player;
import com.example.guwap.entity.Sheriff;
import com.example.guwap.viewmodel.PlayerViewModel;

/**
 * Encounter interactor in the model
 */

public class EncounterInteractor{
    private Player player;
    private NPC npc;
    private double affiliation;
    private int notorietyModifier;
    private boolean shoot, tip, run;
    private boolean playerDead, npcDead;

    public EncounterInteractor(Player player) {
        this(player, Math.random());
    }

    public EncounterInteractor(Player player, double affiliation) {
        this.player = player;
        this.affiliation = affiliation;
        shoot = false;
        tip = false;
        run = false;
        playerDead = false;
        npcDead = false;
        if (affiliation <= 0.5) {
            npc = new Bandit();
        } else {
            npc = new Sheriff();
        }
        if (player.getDifficulty() == Difficulty.BEGINNER) {
            npc.setDamage(5);
            notorietyModifier = 5;
        } else if (player.getDifficulty() == Difficulty.EASY) {
            npc.setDamage(10);
            notorietyModifier = 10;
        } else if (player.getDifficulty() == Difficulty.NORMAL) {
            npc.setDamage(15);
            notorietyModifier = 15;
        } else if (player.getDifficulty() == Difficulty.HARD) {
            npc.setDamage(20);
            notorietyModifier = 20;
        } else if (player.getDifficulty() == Difficulty.IMPOSSIBLE) {
            npc.setDamage(25);
            notorietyModifier = 25;
        }
    }

    public boolean playerShoots() {
        double toHit = Math.random() * 100;
        if (npc instanceof Sheriff) {
            player.setNotoriety(player.getNotoriety() + notorietyModifier);
        } else {
            player.setNotoriety(player.getNotoriety() - notorietyModifier);
        }
        if (toHit >= 50 - player.getFighter()) {
            npc.setHealth(npc.getHealth() - player.getDamage());
            npcDead = npc.getHealth() <= 0;
            return true;
        } else {
            return false;
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

    public boolean npcShoots() {
        double toHit = Math.random() * 100;
        shoot = true;
        if (toHit >= 50 + player.getPilot() * 3) {
            player.setHealth(player.getHealth() - npc.getDamage());
            playerDead = player.getHealth() <= 0;
            return true;
        } else {
            return false;
        }
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

    public NPC getNpc() {
        return npc;
    }

    public boolean getNPCDead() {
        return npcDead;
    }

    public boolean getPlayerDead() {
        return playerDead;
    }
}
