package com.example.guwap.model;

import java.util.HashMap;
import java.util.Map;

public class Model {
    private Repository myRepository;
    private Map<String, Object> interactorMap;

    private static Model instance = new Model();

    public static Model getInstance() { return instance;}

    private Model() {
        myRepository = new Repository();
        interactorMap = new HashMap();
        registerInteractors();
    }

    private void registerInteractors() {
        interactorMap.put("Player", new PlayerInteractor(myRepository));
        interactorMap.put("Location", new LocationInteractor(myRepository));
    }

    public PlayerInteractor getPlayerInteractor() {
        return (PlayerInteractor) interactorMap.get("Player");
    }

    public LocationInteractor getLocationInteractor() {
        return (LocationInteractor) interactorMap.get("Location");
    }
}
