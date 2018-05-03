package com.example.ravonda.cita495;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback{

    GoogleMap map;
    private static final String KEY_CAMERA_POSITION = "camera_position";
    private static final String KEY_LOCATION = "location";
    public LatLng mCurrentLocation;
    public CameraPosition mCameraPosition;
    public LatLng mLastKnownLocation;
    public LatLng center;
    public Boolean onPage;
    public Polyline line;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        onPage = true;
        if (savedInstanceState != null) {
            mCurrentLocation = savedInstanceState.getParcelable(KEY_LOCATION);
            mCameraPosition = savedInstanceState.getParcelable(KEY_CAMERA_POSITION);
        }
        setContentView(R.layout.activity_maps);
        MapFragment mapFragment = (MapFragment) getFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        final Button button4 = findViewById(R.id.button4);
        button4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                startActivity(new Intent(MapsActivity.this, GalleryScreen.class));
                onPage = false;
            }
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        if (map != null) {
            outState.putParcelable(KEY_CAMERA_POSITION, map.getCameraPosition());
            outState.putParcelable(KEY_LOCATION, mLastKnownLocation);
            super.onSaveInstanceState(outState);
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        //Initialize Google Play Services
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)
                    == PackageManager.PERMISSION_GRANTED) {
                map.setMyLocationEnabled(true);
            }
        }
        else {
            map.setMyLocationEnabled(true);
        }
        /**
         * PSEUDO CODE FOR DRAWING POLYLINE UPDATES
         *
         * while user is on navigation screen
         *      get current location
         *      if last known location is null
         *              set current location == to last location
         *      else if current location is equal to previous location
         *              do nothing
         *      else if current location is not equal to previous location
         *              draw polyline from previous location to current location
         *              set previous location to current location
         */
        while(onPage){
            if(mLastKnownLocation == null){
                mLastKnownLocation = mCurrentLocation;
            }
            else if (mCurrentLocation == mLastKnownLocation){
                mLastKnownLocation = mCurrentLocation;
            }
            else if(mCurrentLocation != mLastKnownLocation){
                Polyline line = map.addPolyline(new PolylineOptions()
                        .add(mLastKnownLocation, mCurrentLocation)
                        .width(5)
                        .color(Color.BLACK));
                mLastKnownLocation = mCurrentLocation;
            }
        }
    }
}
