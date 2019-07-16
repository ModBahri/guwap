package com.example.guwap.view;

import android.os.Looper;

import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import com.example.guwap.entity.Difficulty;
import com.example.guwap.entity.Player;
import com.example.guwap.entity.Region;
import com.google.android.gms.maps.model.Marker;

import org.junit.Test;

import static org.junit.Assert.*;

public class MapsActivityTest {

    @Test
    public void onTravelClick() {
        Looper.prepare();
        Marker selectedMarker = null;
        Player player = new Player("Name", Difficulty.BEGINNER, 16, 0, 0, 0);

        Region expected = player.getRegion();

        MapsActivity mapsActivity = new MapsActivity();

        mapsActivity.onTravelClick(null);

        assertEquals(expected, player.getRegion());

    }
}