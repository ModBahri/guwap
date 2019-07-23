package com.example.guwap.entity;

//Everything in this class was made by jorge.
public class Item {
    private int price;
    private String name;
    private int quantity;

    public Item(String difficulty, TypeOfItem typeOfItem) {
        if (difficulty.equals("Beginner")) {
            this.price = typeOfItem.getPrice() / 2;
            this.quantity = 0;
        } else if (difficulty.equals("Easy")) {
            this.price = typeOfItem.getPrice();
            this.quantity = 0;
        } else if (difficulty.equals("Hard")) {
            this.price = typeOfItem.getPrice() * 2;
            this.quantity = 0;
        } else if (difficulty.equals("Impossible")) {
            this.price = typeOfItem.getPrice() * 4;
            this.quantity = 0;
        }
        this.name = typeOfItem.getName();
    }

    public Item() {

    }

    public Item(String d, Difficulty r, String pt, TypeOfItem typeOfItem) {
        if (d.equals("Beginner")) {
            this.price = typeOfItem.getPrice() / 2;
            this.quantity = (int) (Math.random() * ((400 - 200) + 1)) + 200;
        } else if (d.equals("Easy")) {
            this.price = typeOfItem.getPrice();
            this.quantity = (int) (Math.random() * ((300 - 100) + 1)) + 100;
        } else if (d.equals("Normal")) {
            this.price = typeOfItem.getPrice() * 2;
            this.quantity = (int) (Math.random() * ((200 - 0) + 1)) + 0;
        } else {
            this.price = typeOfItem.getPrice() * 4;
            this.quantity = (int) (Math.random() * ((100 - 0) + 1)) + 0;
        }
        this.name = typeOfItem.getName();
        if ((pt.equals("Troglodyte") || pt.equals("Bandit"))
                && (typeOfItem.getName().equals("guns") || typeOfItem.getName().equals("snakeoil"))) {
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
