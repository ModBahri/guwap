package com.example.guwap.model;
import com.example.guwap.entity.Difficulty;

public class randomEncounterGenerator {
	private double chance;
	private int encounter;

	public randomEncounterGenerator(String d) {
		switch (d) {
			case "Beginner":
				encounter = 40;
				break;
			case "Easy":
				encounter = 60;
				break;
			case "Normal":
				encounter = 70;
				break;
			case "Hard":
				encounter = 90;
				break;
			case "Impossible":
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