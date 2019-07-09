package com.example.guwap.model;

import com.example.guwap.entity.Region;
import com.example.guwap.entity.PeopleType;

import java.util.ArrayList;
import java.util.List;

public abstract class Interactor {

    private Repository myRepository;

    protected Interactor(Repository repo) { myRepository = repo;}

    protected Repository getRepository() { return myRepository; }
}
