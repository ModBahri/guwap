package com.example.guwap.model;

import com.example.guwap.entity.Difficulty;
import com.example.guwap.entity.Skills;
import com.example.guwap.model.IEntity;

public class Player implements IEntity {
    private String name;
    private Difficulty difficulty;
    private final int INITIALSKILLPOINTS = 16;
    private final int INITIALCREDITS = 1000;
    private Skills PILOT, FIGHTER, TRADER, ENGINEER;

    public Player(String name, Difficulty difficulty) {
        this.name = name;
        this.difficulty = difficulty;
    }

    public void addSkillPoints(Skills skillset, int skillpoints) {
        skillset.addSkill(skillpoints);
    }

    public String getName() {
        return name;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }
}
