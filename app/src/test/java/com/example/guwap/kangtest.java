package com.example.guwap;

import com.example.guwap.model.EncounterInteractor;
import com.example.guwap.entity.Player;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Muddassir's Tests
 * @author Muddassir Bahri
 * @version 1.0
 */

public class kangtest {

    @Test
    public void npcActsTest() {
        Player player = new Player();
        player.setNotoriety(50);
        EncounterInteractor banditEncounter = new EncounterInteractor(player, 0.5);
        EncounterInteractor sheriffEncounter = new EncounterInteractor(player, 0.9);

        banditEncounter.npcActs();
        sheriffEncounter.npcActs();

        assertTrue(banditEncounter.getTip());
        assertTrue(sheriffEncounter.getShoot());

        player.setNotoriety(25);
        banditEncounter.npcActs();
        sheriffEncounter.npcActs();

        assertTrue(banditEncounter.getShoot());
        assertTrue(sheriffEncounter.getTip());
    }

    @Test
    public void playerShootsTest() {
        Player player = new Player();
        EncounterInteractor banditEncounter = new EncounterInteractor(player, 0.5);
        EncounterInteractor sheriffEncounter = new EncounterInteractor(player, 0.9);

        banditEncounter.playerShoots();
        assertEquals(-15, player.getNotoriety());

        sheriffEncounter.playerShoots();
        assertEquals(0, player.getNotoriety());

    }
}
