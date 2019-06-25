package com.example.guwap.viewmodel;

import androidx.lifecycle.ViewModel;

import com.example.guwap.model.Interactor;
import com.example.guwap.entity.Player;

public class ConfigurationViewModel extends ViewModel {
    private Interactor interactor = new Interactor();
    public void addPlayer(Player player) { interactor.addPlayer(player); }

}
