package com.hav.cigar.driver.activities;

import android.content.Intent;
import android.os.Bundle;
//import android.support.annotation.Nullable;
//import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.hav.cigar.driver.MapDirection.FetchURL;
import com.hav.cigar.driver.MapDirection.TaskLoadedCallback;
import com.hav.cigar.driver.R;
import com.hav.cigar.driver.fragments.DriverAcknowledgementFragment;
import com.hav.cigar.driver.fragments.NotificationFragment;

/**
 * Created by Vishal on 10/20/2018.
 */

public class MapActivity extends FragmentActivity implements OnMapReadyCallback, TaskLoadedCallback {
    private GoogleMap mMap;
    private MarkerOptions place1, place2;
    Button getDirection,reachedLocation;
    private Polyline currentPolyline;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_google_map);

        reachedLocation = findViewById(R.id.reachedLocation);
        reachedLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // getSupportFragmentManager().beginTransaction().
                //       replace(R.id.nav_host_fragment, new DriverAcknowledgementFragment()).commit();
                FragmentTransaction fmt = getSupportFragmentManager().beginTransaction();
                fmt.replace(R.id.nav_host_fragment,new DriverAcknowledgementFragment());
                fmt.addToBackStack(null);
                fmt.commit();
            }
        });
        getDirection = findViewById(R.id.route);
                getDirection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new FetchURL(MapActivity.this).execute(getUrl(place1.getPosition(), place2.getPosition(), "driving"), "driving");
            }
        });
        //27.658143,85.3199503
        //27.667491,85.3208583
        place1 = new MarkerOptions().position(new LatLng(27.658143, 85.3199503)).title("Location 1");
        place2 = new MarkerOptions().position(new LatLng(560.667491, 85.3208583)).title("Location 2");
       MapFragment mapFragment = (MapFragment) getFragmentManager()
                .findFragmentById(R.id.mapView);
        moveToCurrentLocation(new LatLng(27.658143,85.3199503));
        mapFragment.getMapAsync(this);
    }

 @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        Log.d("mylog", "Added Markers");
        mMap.addMarker(place1);
        mMap.addMarker(place2);
    }

    private String getUrl(LatLng origin, LatLng dest, String directionMode) {
        // Origin of route
        String str_origin = "origin=" + origin.latitude + "," + origin.longitude;
        // Destination of route
        String str_dest = "destination=" + dest.latitude + "," + dest.longitude;
        // Mode
        String mode = "mode=" + directionMode;
        // Building the parameters to the web service
        String parameters = str_origin + "&" + str_dest + "&" + mode;
        // Output format

        String output = "json";
        // Building the url to the web service
        String url = "https://maps.googleapis.com/maps/api/directions/" + output + "?" + parameters + "&key=" + getString(R.string.map_api);
        return url;
    }

    @Override
    public void onTaskDone(Object... values) {
        if (currentPolyline != null)
            currentPolyline.remove();
        moveToCurrentLocation(new LatLng(27.658143,85.3199503));
        currentPolyline = mMap.addPolyline((PolylineOptions) values[0]);
    }
    private void moveToCurrentLocation(LatLng currentLocation)
    {
        System.out.println("moveto CurrentLocation");
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(currentLocation,15));
        // Zoom in, animating the camera.
        mMap.animateCamera(CameraUpdateFactory.zoomIn());
        // Zoom out to zoom level 20, animating with a duration of 2 seconds.
        mMap.animateCamera(CameraUpdateFactory.zoomTo(200), 2000, null);

    }
}
