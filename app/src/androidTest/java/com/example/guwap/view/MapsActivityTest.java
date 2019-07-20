package com.example.guwap.view;

import android.os.Looper;
import android.util.Log;

import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import com.example.guwap.entity.Difficulty;
import com.example.guwap.entity.Player;
import com.example.guwap.entity.Region;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

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
/*
    @Test
    public void onTravelClick2() {
        Looper.prepare();
        Marker selectedMarker = null;
        Player player = new Player("Name", Difficulty.BEGINNER, 16, 0, 0, 0);

        Region expected = new Region(32.27, -99.45, "TESTING");

        double lat = expected.getLattitude();
        double longe = expected.getLongitude();

        LatLng markerPos = new LatLng(lat, longe);

        Marker marker = mMap.addMarker(new MarkerOptions().position(markerPos).title(expected.getName()));


        MapsActivity mapsActivity = new MapsActivity();

        mapsActivity.onTravelClick(null);

        assertEquals(expected.getName(), player.getRegion().getName());

    }*/
}