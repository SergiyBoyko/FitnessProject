package com.example.a38096.fitnessproject.ui.fragments;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.a38096.fitnessproject.R;
import com.example.a38096.fitnessproject.utils.AndroidUtils;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.Task;

import java.util.concurrent.TimeUnit;

import butterknife.ButterKnife;

/**
 * Created by Serhii Boiko on 24.10.2018.
 */
public class ClubsMapFragment extends BaseFragment {
    private static final String TAG = ClubsMapFragment.class.getSimpleName();
    private static final float DEFAULT_ZOOM = 16f;
    private GoogleMap googleMap;
    private FusedLocationProviderClient fusedLocationProviderClient;
    private LocationCallback locationCallback;
    private Location lastLocation;

    private GoogleApiClient googleApiClient;

    private BitmapDescriptor iconSelected;
    private BitmapDescriptor iconReleased;


    public static ClubsMapFragment newInstance() {
        return new ClubsMapFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_clubs_map, container, false);
        ButterKnife.bind(this, view);

        initData();
        initLocationCallback();
        initConnection();
        return view;
    }

    private void initData() {
//        iconSelected = BitmapDescriptorFactory.fromResource(R.drawable.pin_shop_focus);
//        iconReleased = BitmapDescriptorFactory.fromResource(R.drawable.pin_shop);
    }

    private void initLocationCallback() {
        locationCallback = new LocationCallback() {
            @Override
            public void onLocationResult(LocationResult locationResult) {
                if (locationResult == null) {
                    return;
                }
                for (Location location : locationResult.getLocations()) {

                    if (location != null) {
                        updateLastLocation(location);
                    }
                }
            }
        };
    }

    private void initConnection() {
        googleApiClient = new GoogleApiClient.Builder(getActivity())
                .addConnectionCallbacks(new GoogleApiClient.ConnectionCallbacks() {
                    @Override
                    public void onConnected(@Nullable Bundle bundle) {
                        initMap();
                    }

                    @Override
                    public void onConnectionSuspended(int i) {
                    }
                })
                .addOnConnectionFailedListener(connectionResult ->
                        AndroidUtils.showLongToast(getActivity(), "Please activate location")
                )
                .addApi(LocationServices.API)
                .build();
    }

    private void updateLastLocation(Location currentLocation) {
        lastLocation = currentLocation;
    }

    private void initMap() {
        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this::onMapReady);
    }

    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;

        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        googleMap.getUiSettings().setRotateGesturesEnabled(false);
        googleMap.getUiSettings().setMapToolbarEnabled(false);

        initDeviceLocation();

        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(),
                Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        googleMap.setMyLocationEnabled(true);
    }

    private void initDeviceLocation() {
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(getActivity());

        startLocationUpdates();

        try {
            Task location = fusedLocationProviderClient.getLastLocation();
            location.addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    Log.d(TAG, "onComplete: found location!");
                    Location currentLocation = (Location) task.getResult();
                    if (currentLocation != null) {
                        updateLastLocation(currentLocation);
                    }
                } else {
                    Log.d(TAG, "onComplete: current location is null");
                }
            });
        } catch (SecurityException e) {
            Log.e(TAG, "initDeviceLocation: SecurityException: " + e.getMessage());
        }
    }

    private void startLocationUpdates() {
        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(),
                Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }

        LocationRequest mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(TimeUnit.SECONDS.toMillis(2));
        mLocationRequest.setFastestInterval(TimeUnit.SECONDS.toMillis(2));
        mLocationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
        fusedLocationProviderClient.requestLocationUpdates(mLocationRequest,
                locationCallback,
                null /* Looper */);
    }

    private void moveCamera(LatLng latLng, float zoom) {
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, zoom));
    }

    private void animateCamera(LatLng latLng) {
        CameraUpdate location = CameraUpdateFactory.newLatLng(latLng);
        googleMap.animateCamera(location, 400, null);
    }

    @Override
    public void onStart() {
        super.onStart();
        if (googleApiClient != null) {
            googleApiClient.connect();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (googleApiClient != null) {
            googleApiClient.disconnect();
        }
    }
}
