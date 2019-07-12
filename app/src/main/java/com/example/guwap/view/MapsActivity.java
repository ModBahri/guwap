package com.example.guwap.view;

import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.guwap.R;
import com.example.guwap.entity.Player;
import com.example.guwap.entity.Region;
import com.example.guwap.entity.Universe;
import com.example.guwap.viewmodel.PlayerViewModel;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
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
        LatLng curloc = new LatLng(viewModel.getRegion(player).getLattitude(), viewModel.getRegion(player).getLongitude());
        mMap.moveCamera(CameraUpdateFactory.newLatLng(curloc));
        mMap.moveCamera(CameraUpdateFactory.zoomTo(5));
        // Instantiates a new CircleOptions object and defines the center and radius
        CircleOptions circleOptions = new CircleOptions()
                .center(curloc)
                .radius(player.getPlayerWagon().getDistance()); // In meters

        // Get back the mutable Circle
        Circle circle = mMap.addCircle(circleOptions);

        currLoc = findViewById(R.id.curr_loc);
        selecLoc = findViewById(R.id.selec_loc);
        currLoc.setText(viewModel.getRegion(player).getName());


        for (Region region: Universe.regionArrayList) {
            String name = region.getName();
            String pplType = region.getPeopleType().toString();
            String resources = region.getResources().toString();
            double lat = region.getLattitude();
            double longe = region.getLongitude();

            Log.i("Region:", name + pplType + resources);

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

    public void onTravelClick(View view) {
        if (selectedMarker != null) {
            for (Region region: Universe.regionArrayList) {
                String name = region.getName();
                if(selectedMarker.getTitle().equals(name)) {
                    if (player.getRegion().distanceTo(region) * 1200 > player.getPlayerWagon().getDistance()) {

                        Context context = getApplicationContext();
                        int duration = Toast.LENGTH_LONG;
                        Toast toast = Toast.makeText(context, "Slow your roll partner! That location is mighty far. Buy some chicken wings for the journey!", duration);
                        toast.show();

                    } else {
                        player.setRegion(region);
                        currLoc.setText(region.getName());
                        selecLoc.setText("");
                        LatLng curloc = new LatLng(region.getLattitude(), region.getLongitude());
                        mMap.moveCamera(CameraUpdateFactory.newLatLng(curloc));
                    }

                }
            }
        }
    }

    public void onClickTravel (MenuItem item) {
        Intent intent = new Intent(this, MapsActivity.class);
        startActivity(intent);
    }

    public void onClickMarket (MenuItem item) {
        Intent intent = new Intent(this, MarketActivity.class);
        startActivity(intent);
    }
}

