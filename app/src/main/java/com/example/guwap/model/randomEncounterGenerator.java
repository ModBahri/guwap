package com.example.guwap.model;
import com.example.guwap.entity.Difficulty;

public class randomEncounterGenerator {
	private double chance;
	private int encounter;

	public randomEncounterGenerator(Difficulty d) {
		switch (d) {
			case BEGINNER:
				encounter = 40;
				break;
			case EASY:
				encounter = 60;
				break;
			case NORMAL:
				encounter = 70;
				break;
			case HARD:
				encounter = 90;
				break;
			case IMPOSSIBLE:
				encounter = 100;
				break;
		}
	}

	public boolean diceRoll() {
		chance = Math.random();
		int d = (int) chance * 100;

		if (d < encounter) {
			return true;
		} else {
			return false;
		}
	}
}