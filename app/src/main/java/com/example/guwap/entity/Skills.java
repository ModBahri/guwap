package com.example.guwap.entity;

public enum Skills {
    PILOT(0), FIGHTER(0), TRADER(0), ENGINEER(0);

    int skillpoints;

    Skills(int skillpoints) {
        this.skillpoints = skillpoints;
    }

    public void addSkill (int i) {
        this.skillpoints += i;
    }
}
