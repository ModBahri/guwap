package com.example.guwap.entity;

//Everything in this class was made by jorge.
public enum TypeOfItem {
    STEERS ("steers", 100),
    HORSES ("horses",  200),
    SNAKEOIL ("snakeoil", 50),
    WHISKEY ("whiskey", 20),
    CHICKENWING ("chickenwing", 5),
    GOLD ("gold", 200),
    WOOD ("wood", 100),
    CHEWINGTOBACCO ("chewingtobacco", 5),
    PEYOTE ("peyote", 20),
    COAL ("coal",2 ),
    GUNS ("guns", 100),
    WEIRDMUSHROOMS ("weirdmushrooms", 87);

    private String name;
    private double price;
    private TypeOfItem(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }
    public double getPrice() { return price; }
}
