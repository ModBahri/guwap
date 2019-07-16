package com.example.guwap.model;

import com.example.guwap.entity.Market;

import java.util.HashMap;
import java.util.Map;

public class Model {
    private Repository myRepository;
    private Map<String, Object> interactorMap;

    private static Model instance = new Model();

    /**
     * get instance of model
     * @return instance
     */
    public static Model getInstance() {
        return instance;
    }

    /**
     * model constructor
     */
    private Model() {
        myRepository = new Repository();
        interactorMap = new HashMap();
        registerInteractors();
    }

    /**
     * register interactors
     */
    private void registerInteractors() {
        interactorMap.put("Player", new PlayerInteractor(myRepository));
        interactorMap.put("Region", new RegionInteractor(myRepository));
    }

    /**
     * gets the player interactor
     * @return player interactor
     */
    public PlayerInteractor getPlayerInteractor() {
        return (PlayerInteractor) interactorMap.get("Player");
    }

    /**
     * gets region interactor
     * @return region interactor
     */
    public RegionInteractor getRegionInteractor() {
        return (RegionInteractor) interactorMap.get("Region");
    }

}
