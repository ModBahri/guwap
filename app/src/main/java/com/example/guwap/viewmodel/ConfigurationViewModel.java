package com.example.guwap.viewmodel;

import android.arch.lifecycle.ViewModel;

import com.example.guwap.entity.Interactor;
import com.example.guwap.model.Player;

public class ConfigurationViewModel extends ViewModel {
    private Interactor interactor;

    public void addPlayer(Player player) { interactor.addPlayer(player); }

}
