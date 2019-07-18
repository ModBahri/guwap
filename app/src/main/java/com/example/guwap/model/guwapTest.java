package com.example.guwap.model;
import org.junit.Before;
import org.junit.Test;
import com.example.guwap.entity.Sheriff;
import com.example.guwap.entity.Bandit;
import com.example.guwap.entity.NPC;
import static org.junit.Assert.*;


import com.example.guwap.entity.Difficulty;

public class guwapTest {

    private Difficulty testDifficulty;

    @Before
    public void setUp() {
        Difficulty testDifficulty = Difficulty.IMPOSSIBLE;
        randomEncounterGenerator myTest = new randomEncounterGenerator(testDifficulty);
        NPC result = myTest.diceRoll();
    }

    @Test
    public void test1() {
        randomEncounterGenerator myTest = new randomEncounterGenerator(testDifficulty);
        NPC result = myTest.diceRoll();
        Bandit myBandit = new Bandit();
        Sheriff mySheriff = new Sheriff();
        int r = myTest.getChance();
        assertNotNull(r);
    }

    @Test
    public void test2() {
        randomEncounterGenerator myTest = new randomEncounterGenerator(testDifficulty);
        NPC result = myTest.diceRoll();
        assertNotNull(result);
    }
}
