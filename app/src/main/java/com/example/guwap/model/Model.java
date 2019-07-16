package com.example.guwap.model;

import com.example.guwap.entity.Market;

import java.util.HashMap;
import java.util.Map;

public class Model {
    private Repository myRepository;
    private Map<String, Object> interactorMap;

    private static Model instance = new Model();

    public static Model getInstance() {
        return instance;
    }

    private Model() {
        myRepository = new Repository();
        interactorMap = new HashMap();
        registerInteractors();
    }

    private void registerInteractors() {
        interactorMap.put("Player", new PlayerInteractor(myRepository));
        interactorMap.put("Region", new RegionInteractor(myRepository));
    }

    public PlayerInteractor getPlayerInteractor() {
        return (PlayerInteractor) interactorMap.get("Player");
    }

    public RegionInteractor getRegionInteractor() {
        return (RegionInteractor) interactorMap.get("Region");
    }

}
