package com.hav.cigar.driver.fragments;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.hav.cigar.driver.MapDirection.FetchURL;
import com.hav.cigar.driver.R;
import com.hav.cigar.driver.activities.MapActivity;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * A simple {@link Fragment} subclass.
 */
public class GoogleMapFragment {
        /*extends Fragment implements OnMapReadyCallback, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener,com.google.android.gms.location.LocationListener{

    @BindView(R.id.mapView)
    MapView mapView;
    @BindView(R.id.reachedLocation)
    AppCompatButton reachedLocation;
    private GoogleMap map;
    private GoogleApiClient mGoogleApiClient;
    private Location mLocation;
    private GoogleMap mMap;
    private MarkerOptions place1, place2;
    Button getDirection;
    private Polyline currentPolyline;
    List<Address> addresses;
    private static String TAG = "GoogleMap";
 public GoogleMapFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View googleMapView = inflater.inflate(R.layout.fragment_google_map, container, false);
        ButterKnife.bind(this,googleMapView);
//        getActivity().getActionBar().hide();
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(this);
        mGoogleApiClient = new GoogleApiClient.Builder(getContext())
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
        new FetchURL(getActivity()).execute(getUrl(place1.getPosition(), place2.getPosition(), "driving"), "driving");
        place1 = new MarkerOptions().position(new LatLng(27.658143, 85.3199503)).title("Location 1");
        place2 = new MarkerOptions().position(new LatLng(27.667491, 85.3208583)).title("Location 2");
       reachedLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fmt = getActivity().getSupportFragmentManager().beginTransaction();
                fmt.replace(R.id.nav_host_fragment,new DriverAcknowledgementFragment());
                fmt.addToBackStack(null);
                fmt.commit();
            }
        });

      // mapView.getMapAsync(this);
       // map = mapFragment.getMap();
        return googleMapView;
    }

    @Override
    public void onResume() {
        mapView.onResume();
        super.onResume();
        Objects.requireNonNull(((AppCompatActivity) requireActivity()).getSupportActionBar()).hide();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

    @Override
   /* public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        map.getUiSettings().setMyLocationButtonEnabled(true);
        map.setMyLocationEnabled(true);
        try {
            MapsInitializer.initialize(requireActivity());
        } catch (Exception e) {
            e.printStackTrace();
        }
        map.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(9.9252, 78.1198)));
    }

       // @Override
        public void onConnected(@Nullable Bundle bundle) {
            if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }
            //startLocationUpdates();
            mLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
            if(mLocation == null){
                startLocationUpdates();
            }
            if (mLocation != null) {
                double latitude = mLocation.getLatitude();
                double longitude = mLocation.getLongitude();


                try {
                    Geocoder gcd = new Geocoder(getContext(), Locale.getDefault());
                    addresses = gcd.getFromLocation(mLocation.getLatitude(),
                            mLocation.getLongitude(), 1);
                    if (addresses.size() > 0) {
                        System.out.println(addresses.get(0).getLocality());
                        String cityName = addresses.get(0).getLocality();
                        String cityStreet = addresses.get(0).getSubLocality();

                        LatLng sydney = new LatLng(mLocation.getLatitude(), mLocation.getLongitude());
                        map.addMarker(new MarkerOptions().position(sydney).title(addresses.get(0).getSubLocality()));
                        CameraUpdate center=CameraUpdateFactory.newLatLng(new LatLng(mLocation.getLatitude(), mLocation.getLongitude()));
                        CameraUpdate zoom=CameraUpdateFactory.zoomTo(11);
                        map.moveCamera(center);
                        map.animateCamera(zoom);
                    }
                }
                catch (IOException e) {
                    e.printStackTrace();
                }

            } else {
                // Toast.makeText(this, "Location not Detected", Toast.LENGTH_SHORT).show();
            }
        }

    /*public void onCreate(@Nullable Bundle savedInstanceState,LayoutInflater inflater, ViewGroup container) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_google_map);
        //LayoutInflater inflater = null;
        View googleMapView = inflater.inflate(R.layout.fragment_google_map, container, false);
        ButterKnife.bind(this,googleMapView);
//        getActivity().getActionBar().hide();
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(this);
        //getDirection = findViewById(R.id.reachedLocation);
        new FetchURL(getActivity()).execute(getUrl(place1.getPosition(), place2.getPosition(), "driving"), "driving");

        //27.658143,85.3199503
        //27.667491,85.3208583
        place1 = new MarkerOptions().position(new LatLng(27.658143, 85.3199503)).title("Location 1");
        place2 = new MarkerOptions().position(new LatLng(27.667491, 85.3208583)).title("Location 2");
       com.google.android.gms.maps.MapFragment mapFragment = (MapFragment) getFragmentManager()
               .findFragmentById(R.id.mapView);
       mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mGoogleApiClient.connect();
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

    public void onTaskDone(Object... values) {
        if (currentPolyline != null)
            currentPolyline.remove();
        currentPolyline = mMap.addPolyline((PolylineOptions) values[0]);
    }
    @Override
    public void onConnectionSuspended(int i) {
        Log.i(TAG, "Connection Suspended");
        mGoogleApiClient.connect();
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Log.i(TAG, "Connection failed. Error: " + connectionResult.getErrorCode());
    }

    @Override
    public void onStart() {
        super.onStart();
        mGoogleApiClient.connect();
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mGoogleApiClient.isConnected()) {
            mGoogleApiClient.disconnect();
        }
        Objects.requireNonNull(((AppCompatActivity) requireActivity()).getSupportActionBar()).show();
    }

        @Override
        public void onLocationChanged(Location location) {

        }
    protected void startLocationUpdates() {

        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions

            return;
        }
    }*/

}
