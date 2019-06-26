package com.example.guwap.entity;

public class Region {
    private Resources resources;
    private PeopleType peopleType;

    public Region(Resources resources, PeopleType peopleType) {
        this.resources = resources;
        this.peopleType = peopleType;
    }

    public Resources getResources() {
        return resources;
    }

    public PeopleType getPeopleType() {
        return peopleType;
    }
}
