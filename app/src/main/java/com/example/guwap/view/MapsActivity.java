package com.example.guwap.view;

import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.guwap.R;
import com.example.guwap.entity.Location;
import com.example.guwap.entity.Player;
import com.example.guwap.entity.Universe;
import com.example.guwap.viewmodel.PlayerViewModel;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener {

    private GoogleMap mMap;
    private PlayerViewModel viewModel;
    private Player player;
    private TextView currLoc;
    private TextView selecLoc;
    private Marker selectedMarker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
         //Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.nmap);
        mapFragment.getMapAsync(this);
        viewModel = ViewModelProviders.of(this).get(PlayerViewModel.class);
        player = viewModel.getPlayer();
        Log.i("Player name", player.getName());
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        LatLng curloc = new LatLng(viewModel.getLocation(player).getLattitude(), viewModel.getLocation(player).getLongitude());
        mMap.moveCamera(CameraUpdateFactory.newLatLng(curloc));

        currLoc = findViewById(R.id.curr_loc);
        selecLoc = findViewById(R.id.selec_loc);
        currLoc.setText(viewModel.getLocation(player).getName());


        for (Location location: Universe.locationArrayList) {
            String name = location.getName();
            String pplType = location.getPeopleType().toString();
            String resources = location.getResources().toString();
            double lat = location.getLattitude();
            double longe = location.getLongitude();

            Log.i("Location:", name + pplType + resources);

            LatLng markerPos = new LatLng(lat, longe);
            Marker marker = mMap.addMarker(new MarkerOptions().position(markerPos).title(name));
            marker.setTag(marker.hashCode());
        }

        // Set a listener for marker click.
        mMap.setOnMarkerClickListener(this);


    }

    /** Called when the user clicks a marker. */
    @Override
    public boolean onMarkerClick(final Marker marker) {
        selecLoc.setText(marker.getTitle());
        selectedMarker = marker;
        return true;
    }

    public void onTravelClick() {
        if (selectedMarker != null) {
            for (Location location: Universe.locationArrayList) {
                String name = location.getName();
                if(selectedMarker.getTitle().equals(name)) {
                    player.setLocation(location);
                    Intent intent = new Intent(this, MapsActivity.class);
                    startActivity(intent);
                }
            }
        }
    }
}

