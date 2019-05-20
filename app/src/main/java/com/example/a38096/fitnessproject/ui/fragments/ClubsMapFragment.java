package com.example.a38096.fitnessproject.ui.fragments;

import android.content.Context;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.InputDevice;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.example.a38096.fitnessproject.R;

import org.osmdroid.api.IGeoPoint;
import org.osmdroid.config.Configuration;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.views.CustomZoomButtonsController;
import org.osmdroid.views.MapView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Serhii Boiko on 24.10.2018.
 */
public class ClubsMapFragment extends BaseFragment {

    @BindView(R.id.map)
    protected MapView mapView;

    public static ClubsMapFragment newInstance() {
        return new ClubsMapFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_clubs_map, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Context ctx = getActivity();
        Configuration.getInstance().load(ctx, PreferenceManager.getDefaultSharedPreferences(ctx));
        ButterKnife.bind(this, view);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initMap();
    }

    private void initMap() {
        mapView.setTileSource(TileSourceFactory.MAPNIK);
        mapView.getZoomController().setVisibility(CustomZoomButtonsController.Visibility.NEVER);
        mapView.setMultiTouchControls(true);
        mapView.setTilesScaledToDpi(true);
        mapView.getController().setZoom(1f);
        mapView.setMapOrientation(0, false);
        mapView.scrollTo(0, 0);
        mapView.setOnGenericMotionListener((v, event) -> {
            if (0 != (event.getSource() & InputDevice.SOURCE_CLASS_POINTER)) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_SCROLL:
                        if (event.getAxisValue(MotionEvent.AXIS_VSCROLL) < 0.0f)
                            mapView.getController().zoomOut();
                        else {
                            //this part just centers the map on the current mouse location before the zoom action occurs
                            IGeoPoint iGeoPoint = mapView.getProjection().fromPixels((int) event.getX(), (int) event.getY());
                            mapView.getController().animateTo(iGeoPoint);
                            mapView.getController().zoomIn();
                        }
                        return true;
                }
            }
            return false;
        });
    }
}
