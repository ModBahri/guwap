package com.example.guwap.entity;

import java.util.ArrayList;
import java.util.List;

public class Difficulty {

    private String difficulty;
    private List<String> difficulties;

    public Difficulty(int dif) {
        difficulties = new ArrayList<>();
        difficulties.add("Beginner");
        difficulties.add("Easy");
        difficulties.add("Normal");
        difficulties.add("Hard");
        difficulties.add("Impossible");

        difficulty = difficulties.get(dif);
    }

    public Difficulty() {

    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(int u) {
        this.difficulty = difficulties.get(u);
    }

    public List<String> getDifficulties() {
        return difficulties;
    }

    public void setDifficulties(List<String> difficulties) {
        this.difficulties = difficulties;
    }
}
