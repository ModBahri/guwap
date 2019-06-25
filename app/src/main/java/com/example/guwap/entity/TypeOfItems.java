package com.example.guwap.entity;

public enum TypeOfItems {
    STEERS ("steers",1),
    HORSES ("horses", 2),
    SNAKEOIL ("snakeoil",3),
    WHISKEY ("whiskey",4),
    CHICKENWING ("chickenwing",5),
    GOLD ("gold",6),
    WOOD ("wood",7),
    CHEWINGTOBACCO ("chewingtobacco",8),
    PEYOTE ("peyote",9),
    COAL ("coal",10),
    GUNS ("guns",11),
    WEIRDMUSHROOMS ("weirdmushrooms",12);

    private int numberOfTypesOfItems;
    private String nameOfType;
    private TypeOfItems(String name, int type) {
        this.nameOfType = name;
        this.numberOfTypesOfItems = type;
    }
    public int getNumberOfTypesOfItems(){
        return numberOfTypesOfItems;
    }
    public String getNameOfType() {
        return nameOfType;
    }
}
