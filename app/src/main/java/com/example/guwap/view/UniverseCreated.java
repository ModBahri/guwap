package com.example.guwap.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.example.guwap.R;
import com.example.guwap.entity.Region;
import com.example.guwap.entity.Universe;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class UniverseCreated extends AppCompatActivity {
    /**
     * Creates universe
     * @param savedInstanceState current state
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_universe_created);
        for (Region region: Universe.regionArrayList) {
            Log.i("Region", region.getName() + "\n People type: " + region.getPeopleType() + "\nSpecial Resources: " + region.getResources());
            /*LatLng marker = new LatLng(region.getLattitude(), region.getLongitude());
            Marker marker1 = mMap.addMarker(new MarkerOptions().position(marker).title(region.getName()));
            marker1.setTag(marker1.hashCode()); */
        }
    }
}
