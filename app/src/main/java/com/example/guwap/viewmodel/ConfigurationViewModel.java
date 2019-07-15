package com.example.guwap.viewmodel;

import androidx.lifecycle.ViewModel;

import com.example.guwap.model.PlayerInteractor;
import com.example.guwap.entity.Player;

public class ConfigurationViewModel extends ViewModel {
    private PlayerInteractor interactor = new PlayerInteractor();
    public void addPlayer(Player player) { interactor.addPlayer(player); }

}
