package com.example.guwap.viewmodel;

import androidx.lifecycle.ViewModel;

import com.example.guwap.model.Interactor;
import com.example.guwap.entity.PeopleType;

public class ConfigurationViewModel extends ViewModel {
    private Interactor interactor = new Interactor();
    public void addPlayer(PeopleType.Player player) { interactor.addPlayer(player); }

}
