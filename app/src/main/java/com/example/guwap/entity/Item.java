package com.example.guwap.entity;

//Everything in this class was made by jorge.
public class Item {
    private int price;
    private String name;
    private int quantity;

    public Item(Difficulty difficulty, TypeOfItem typeOfItem) {
        if (difficulty == Difficulty.BEGINNER) {
            this.price = typeOfItem.getPrice() / 2;
            this.quantity = 0;
        } else if (difficulty == Difficulty.NORMAL) {
            this.price = typeOfItem.getPrice();
            this.quantity = 0;
        } else if (difficulty == Difficulty.HARD) {
            this.price = typeOfItem.getPrice() * 2;
            this.quantity = 0;
        } else if (difficulty == Difficulty.IMPOSSIBLE) {
            this.price = typeOfItem.getPrice() * 4;
            this.quantity = 0;
        }
        this.name = typeOfItem.getName();
    }

    public Item(Difficulty d, Resources r, PeopleType pt, TypeOfItem typeOfItem) {
        if (d == Difficulty.BEGINNER) {
            this.price = typeOfItem.getPrice() / 2;
            this.quantity = (int) (Math.random() * ((400 - 200) + 1)) + 200;
        } else if (d == Difficulty.NORMAL) {
            this.price = typeOfItem.getPrice();
            this.quantity = (int) (Math.random() * ((300 - 100) + 1)) + 100;
        } else if (d == Difficulty.HARD) {
            this.price = typeOfItem.getPrice() * 2;
            this.quantity = (int) (Math.random() * ((200 - 0) + 1)) + 0;
        } else if (d == Difficulty.IMPOSSIBLE) {
            this.price = typeOfItem.getPrice() * 4;
            this.quantity = (int) (Math.random() * ((100 - 0) + 1)) + 0;
        }
        this.name = typeOfItem.getName();
        if ((pt == PeopleType.TROGLODYTE || pt == PeopleType.BANDIT)
                && (typeOfItem.getName() == "guns" || typeOfItem.getName() == "snakeoil")) {
            this.quantity = this.quantity * 2;
        } /*else if (pt == PeopleType.BANDIT) {
            this.quantity
        }*/
    }

    public int getQuantity() {
        return quantity;
    }

    public int getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
