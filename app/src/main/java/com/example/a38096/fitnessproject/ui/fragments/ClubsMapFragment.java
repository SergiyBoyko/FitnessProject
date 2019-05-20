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
import com.example.a38096.fitnessproject.listeners.OsmClubClickListener;
import com.example.a38096.fitnessproject.model.entities.Club;
import com.example.a38096.fitnessproject.presenters.ClubsPresenter;
import com.example.a38096.fitnessproject.utils.AndroidUtils;
import com.example.a38096.fitnessproject.utils.ClubsIconProvider;
import com.example.a38096.fitnessproject.views.ClubsView;

import org.osmdroid.api.IGeoPoint;
import org.osmdroid.config.Configuration;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.BoundingBox;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.CustomZoomButtonsController;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Marker;
import org.osmdroid.views.overlay.mylocation.MyLocationNewOverlay;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Serhii Boiko on 24.10.2018.
 */
public class ClubsMapFragment extends BaseFragment<ClubsView> implements ClubsView {
    private static final boolean APPLY_ANIMATION = true;
    private static final double DEFAULT_ZOOM = 16;
    private static final long DEFAULT_ANIMATION_SPEED = 1000;

    @BindView(R.id.map)
    protected MapView mapView;
    @Inject
    protected ClubsPresenter presenter;

    private List<Marker> clubMarkerArray;
    private MyLocationNewOverlay locationOverlay;

    private List<Club> clubList;
    private ClubsIconProvider clubsIconProvider;
    private OsmClubClickListener onMarkerClickListener;

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

        getPresentersComponent().inject(this);
        registerPresenterLifecycle(presenter, this);

        initData();
        presenter.fetchClubs();
    }

    private void initData() {
        clubsIconProvider = new ClubsIconProvider(Objects.requireNonNull(getContext()));
        onMarkerClickListener = new OsmClubClickListener(this::loadClubData, clubsIconProvider);
        clubMarkerArray = new ArrayList<>();
    }

    private void loadClubData(Club club) {
        AndroidUtils.showLongToast(getContext(), club.getName());
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initMap();
        initClubsLocations();
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
                if (event.getAction() == MotionEvent.ACTION_SCROLL) {
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

    private void initClubsLocations() {
        clubList = new ArrayList<>();
        presenter.fetchClubs();
    }

    @Override
    public void showClubs(List<Club> clubs) {
        AndroidUtils.showLongToast(getActivity(), String.valueOf(clubs.size()));
        clubList.clear();
        clubList.addAll(clubs);
        List<GeoPoint> geoPoints = new ArrayList<>();

        for (Club club : clubs) {
            Marker marker = new Marker(mapView);
            marker.setIcon(clubsIconProvider.provideReleasedDrawable(club.isFavorite()));
            marker.setOnMarkerClickListener(onMarkerClickListener);
            marker.setPosition(new GeoPoint(club.getLatitude(), club.getLongitude()));
            marker.setRelatedObject(club);

            geoPoints.add(marker.getPosition());
            clubMarkerArray.add(marker);
            mapView.getOverlays().add(marker);
        }
        if (!geoPoints.isEmpty()) {
            BoundingBox boundingBox = BoundingBox.fromGeoPoints(geoPoints);
            try {
                mapView.zoomToBoundingBox(boundingBox, APPLY_ANIMATION, 50);
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            }
        }
    }

    private void resetUi() {
        locationOverlay.disableMyLocation();
        mapView.getOverlays().clear();
        onMarkerClickListener.clear();
    }

    private void animateTo(GeoPoint geoPoint) {
        mapView.getController().animateTo(geoPoint, DEFAULT_ZOOM, DEFAULT_ANIMATION_SPEED);
    }
}
