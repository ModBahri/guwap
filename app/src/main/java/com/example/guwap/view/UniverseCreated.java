package com.example.guwap.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.example.guwap.R;
import com.example.guwap.entity.Location;
import com.example.guwap.entity.Universe;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class UniverseCreated extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_universe_created);
        for (Location location: Universe.locationArrayList) {
            Log.i("Location", location.getName() + "\n People type: " + location.getPeopleType() + "\nSpecial Resources: " + location.getResources());
            /*LatLng marker = new LatLng(location.getLattitude(), location.getLongitude());
            Marker marker1 = mMap.addMarker(new MarkerOptions().position(marker).title(location.getName()));
            marker1.setTag(marker1.hashCode()); */
        }
    }
}
