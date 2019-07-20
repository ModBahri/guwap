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
    private boolean shoot, playerShoot, tip, run;
    private boolean playerDead, npcDead;

    public EncounterInteractor(Player player) {
        this(player, Math.random());
    }

    public EncounterInteractor(Player player, double affiliation) {
        this.player = player;
        this.affiliation = affiliation;
        playerShoot = false;
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

    /**
     * Player shoots NPC
     * @return whether the player hits or not
     */
    public boolean playerShoots() {
        double toHit = Math.random() * 100;
        playerShoot = true;
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

    /**
     * Player tips their hat to NPC
     */
    public void playerTips() {
        if (npc instanceof Sheriff) {
            player.setNotoriety(player.getNotoriety() - notorietyModifier);
        } else {
            player.setNotoriety(player.getNotoriety() + notorietyModifier);
        }
    }

    /**
     * Player attempts to run from NPC
     */
    public void playerRuns() {
        double toRun = Math.random() * 100;
        if (toRun >= 75 - player.getPilot() * 4) {
            run = true;
        }
    }

    /**
     * NPC acts. Can either shoot or tip hat
     */
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

    /**
     * NPC tips hat.
     */
    public void npcTips() {
        tip = true;
    }

    /**
     * NPC shoots player
     * @return whether they hit or not.
     */
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

    /**
     * getter for if NPC shot
     * @return whether the NPC shot or not
     */
    public boolean getShoot() {
        return shoot;
    }

    /**
     * getter for if NPC tipped
     * @return whether the NPC tipped or not
     */
    public boolean getTip() {
        return tip;
    }

    /**
     * getter for if player got away
     * @return whether the player got away
     */
    public boolean getRun() {
        return run;
    }

    /**
     * getter for the NPC parameter
     * @return the NPC
     */
    public NPC getNpc() {
        return npc;
    }

    /**
     * getter for if the NPC died
     * @return whether the NPC died
     */
    public boolean getNPCDead() {
        return npcDead;
    }

    /**
     * getter for if the player died
     * @return whether the player died
     */
    public boolean getPlayerDead() {
        return playerDead;
    }
}
