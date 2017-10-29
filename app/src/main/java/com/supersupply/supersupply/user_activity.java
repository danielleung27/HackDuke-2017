package com.supersupply.supersupply;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import android.location.Location;
import android.widget.ListView;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.supersupply.supersupply.R;

import java.util.ArrayList;

public class user_activity extends FragmentActivity implements OnMapReadyCallback {

    private DatabaseReference mDatabase;

    private GoogleMap mMap;
    private GPSTracker gpsTracker;
    private Location mLocation;
    private ArrayList<Double> lats = new ArrayList<>();
    private ArrayList<Double> lons = new ArrayList<>();
    private ArrayList<String> org_names = new ArrayList<>();
    double latitude, longitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_activity);
        gpsTracker = new GPSTracker(getApplicationContext());
        mLocation = gpsTracker.getLocation();
        latitude = mLocation.getLatitude();
        longitude = mLocation.getLongitude();

        mDatabase = FirebaseDatabase.getInstance().getReference();

        mDatabase.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                double lat = Double.parseDouble(dataSnapshot.child("latitude").getValue(String.class));
                double lon = Double.parseDouble(dataSnapshot.child("longitude").getValue(String.class));
                String org_name = dataSnapshot.child("name").getValue(String.class);
                lats.add(lat);
                lons.add(lon);
                org_names.add(org_name);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
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

        // Add a marker in Sydney and move the camera
        LatLng currloc = new LatLng(latitude, longitude);
        mMap.addMarker(new MarkerOptions().position(currloc).title("Eyy we in HackDuke rn bitchez"));
        //INSERT VOLUNTEER APP FUNCTION
        for (int i = 0; i < lats.size(); i++)
        {
            LatLng curr = new LatLng(lats.get(i), lons.get(i));
            mMap.addMarker(new MarkerOptions().position(curr).title(org_names.get(i)));
        }
        float zoomLevel = 0.4f; //This goes up to 21
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(currloc, zoomLevel));
    }
}
