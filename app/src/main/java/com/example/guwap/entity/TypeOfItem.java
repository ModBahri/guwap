package com.example.guwap.entity;

//Everything in this class was made by jorge.
public enum TypeOfItem {
    STEERS ("steers", 100, 0),
    HORSES ("horses",  200, 1),
    SNAKEOIL ("snakeoil", 50, 2),
    WHISKEY ("whiskey", 20,3),
    CHICKENWING ("chickenwing", 5,4),
    GOLD ("gold", 200,5),
    WOOD ("wood", 100,6),
    CHEWINGTOBACCO ("chewingtobacco", 5,7),
    PEYOTE ("peyote", 20,8),
    COAL ("coal",2,9),
    GUNS ("guns", 100,10),
    WEIRDMUSHROOMS ("weirdmushrooms", 87,11);

    private String name;
    private double price;
    private int type;
    private TypeOfItem(String name, double price, int type) {
        this.name = name;
        this.price = price;
        this.type = type;
    }

    public String getName() {
        return name;
    }
    public double getPrice() { return price; }
}
