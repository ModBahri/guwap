import java.math.*;
package com.example.guwap.model;
import com.example.guwap.entity.Difficulty;

public class randomEncounterGenerator {
	private int chance;
	private int enemyEncounter;
	private int sheriffEncounter;

	public randomEncounterGenerator(Difficulty d) {
		switch (d) {
			case BEGINNER:
				enemyEncounter = 20;
				sheriffEncounter = 40;
				break;
			case EASY:
				enemyEncounter = 30;
				sheriffEncounter = 60;
				break;
			case NORMAL:
				enemyEncounter = 40;
				sheriffEncounter = 70;
				break;
			case HARD:
				enemyEncounter = 50;
				sheriffEncounter = 90;
				break;
			case IMPOSSIBLE:
				enemyEncounter = 50;
				sheriffEncounter = 100;
				break;
		}
	}

	public void diceRoll() {
		double chance = Math.random();
		int d = (int) chance * 100;

		if (d < enemyEncounter) {
			//a bandit encounter occurs
		} else if (d < sheriffEncounter) {
			//a sheriff encounter occurs
		}
	}
}