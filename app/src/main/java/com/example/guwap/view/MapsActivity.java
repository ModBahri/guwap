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
import com.example.guwap.entity.Wagon;
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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.concurrent.CountDownLatch;

import static android.content.ContentValues.TAG;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener {

    private GoogleMap mMap;
    private PlayerViewModel viewModel;
    private Player player;
    private TextView currLoc;
    private TextView selecLoc;
    private TextView cWings;
    private Marker selectedMarker;
    private Circle circle;
    private DatabaseReference database;

    /**
     * Method called when activity is opened
     * @param savedInstanceState current state
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        database = FirebaseDatabase.getInstance().getReference();
        //final DatabaseReference playersRef = database.child("players");
        //DatabaseReference playerRef = playersRef.child(id);


        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                CountDownLatch done = new CountDownLatch(1);
                String id = getIntent().getStringExtra("PLAYER_ID");
                player = dataSnapshot.child("players").child(id).getValue(Player.class);
                player.setRegion(dataSnapshot.child("regions").child(id).getValue(Region.class));
                player.setPlayerWagon(dataSnapshot.child("wagons").child(id).getValue(Wagon.class));
                player.setUniverse(dataSnapshot.child("universes").child(id).getValue(Universe.class));
                done.countDown();
                render();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException());
                // ...
            }
        });


    }

    public void render() {
        setContentView(R.layout.activity_maps);
        //Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.nmap);
        mapFragment.getMapAsync(this);
        viewModel = ViewModelProviders.of(this).get(PlayerViewModel.class);
        cWings = findViewById(R.id.cWing);
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
     * @param googleMap google map
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        LatLng curloc = new LatLng(player.getRegion().getLattitude(), player.getRegion().getLongitude());
        mMap.moveCamera(CameraUpdateFactory.newLatLng(curloc));
        mMap.moveCamera(CameraUpdateFactory.zoomTo(5));
        player.getPlayerWagon().setDistance(player.getPlayerWagon().getCargo().get(4).getQuantity() * 100000000);
        // Instantiates a new CircleOptions object and defines the center and radius
        CircleOptions circleOptions = new CircleOptions()
                .center(curloc)
                .radius(player.getPlayerWagon().getDistance()); // In meters

        // Get back the mutable Circle
        circle = mMap.addCircle(circleOptions);

        currLoc = findViewById(R.id.curr_loc);
        selecLoc = findViewById(R.id.selec_loc);
        currLoc.setText(player.getRegion().getName());

        cWings.setText(Integer.toString(player.getPlayerWagon().getCargo().get(4).getQuantity()));


        for (Region region: player.getUniverse().getRegionArrayList()) {
            String name = region.getName();
            String pplType = region.getPeopleType().toString();
            String resources = region.getResources().toString();
            double lat = region.getLattitude();
            double longe = region.getLongitude();

            Log.i("Region:", name + pplType + resources);

            LatLng markerPos = new LatLng(lat, longe);

            Marker marker = mMap.addMarker(new MarkerOptions().position(markerPos).title(name));
            selectedMarker = marker;
            marker.setTag(marker.hashCode());
        }

        // Set a listener for marker click.
        mMap.setOnMarkerClickListener(this);



    }

    /**
     * Click handler for markers
     * @param marker marker object that was clicked
     * @return successful?
     */
    @Override
    public boolean onMarkerClick(final Marker marker) {
        selecLoc.setText(marker.getTitle());
        selectedMarker = marker;
        return true;
    }

    /**
     * Click handler for travel button
     * @param view selected view
     */
    public void onTravelClick(View view) {
        if (selectedMarker != null) {
            for (Region region: player.getUniverse().getRegionArrayList()) {
                String name = region.getName();
                if(selectedMarker.getTitle().equals(name)) {
                    if (player.getRegion().distanceTo(region) * 1200 > player.getPlayerWagon().getDistance()) {

                        Context context = getApplicationContext();
                        int duration = Toast.LENGTH_LONG;
                        Toast toast = Toast.makeText(context, "Slow your roll partner! That location is mighty far. Buy some chicken wings for the journey!", duration);
                        toast.show();

                    } else {
                        double dist = player.getRegion().distanceTo(region) * 1200;
                        double chknwings = (dist) / 100000000;
                        int iChkn = (int) chknwings;
                        int curQuant = player.getPlayerWagon().getCargo().get(4).getQuantity();
                        player.setRegion(region);
                        currLoc.setText(region.getName());
                        selecLoc.setText("");
                        LatLng curloc = new LatLng(region.getLattitude(), region.getLongitude());
                        mMap.moveCamera(CameraUpdateFactory.newLatLng(curloc));
                        player.getPlayerWagon().getCargo().get(4).setQuantity(curQuant - iChkn);
                        player.getPlayerWagon().setDistance(player.getPlayerWagon().getDistance() - dist);

                        circle.remove();

                        curloc = new LatLng(player.getRegion().getLattitude(), player.getRegion().getLongitude());

                        double dista = player.getPlayerWagon().getDistance();

                        CircleOptions circleOptions = new CircleOptions()
                                .center(curloc)
                                .radius(player.getPlayerWagon().getDistance()); // In meters

                        // Get back the mutable Circle
                        circle = mMap.addCircle(circleOptions);

                        cWings.setText(Integer.toString(player.getPlayerWagon().getCargo().get(4).getQuantity()));
                    }

                }
            }
        }
    }

    /**
     * click handler for travel
     * @param item selected menu item
     */
    public void onClickTravel (MenuItem item) {
        Intent intent = new Intent(this, MapsActivity.class);
        intent.putExtra("PLAYER_ID", player.getId());
        database.child("players").child(player.getId()).setValue(player);
        database.child("wagons").child(player.getId()).setValue(player.getPlayerWagon());
        database.child("universes").child(player.getId()).setValue(player.getUniverse());

        startActivity(intent);
    }

    /**
     * click handler for market menu item
     * @param item selected menu item
     */
    public void onClickMarket (MenuItem item) {
        Intent intent = new Intent(this, MarketActivity.class);
        intent.putExtra("PLAYER_ID", player.getId());
        database.child("players").child(player.getId()).setValue(player);
        database.child("wagons").child(player.getId()).setValue(player.getPlayerWagon());
        database.child("universes").child(player.getId()).setValue(player.getUniverse());

        startActivity(intent);
    }
}

