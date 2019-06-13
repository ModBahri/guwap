package com.example.guwap.entity;

public enum Difficulty {
    BEGINNER("B"), EASY("E"), NORMAL("N"), HARD("H"), IMPOSSIBLE("I");

    private String difficultyLetter;

    private Difficulty(String s) {
        this.difficultyLetter = s;
    }
}
