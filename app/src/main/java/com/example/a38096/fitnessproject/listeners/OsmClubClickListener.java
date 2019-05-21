package com.example.a38096.fitnessproject.listeners;

import com.example.a38096.fitnessproject.model.entities.Club;
import com.example.a38096.fitnessproject.utils.ClubsIconProvider;

import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Marker;

public class OsmClubClickListener implements Marker.OnMarkerClickListener {
    private Marker previousMarker;
    private boolean previousIsFavorite;

    private OnScooterSelectedCallback callback;
    private ClubsIconProvider clubsIconProvider;

    public OsmClubClickListener(OnScooterSelectedCallback callback, ClubsIconProvider clubIconProvider) {
        this.callback = callback;
        this.clubsIconProvider = clubIconProvider;
    }

    @Override
    public boolean onMarkerClick(Marker marker, MapView mapView) {
        Club club = (Club) marker.getRelatedObject();
        if (club == null) return false;

        if (previousMarker != null) {
            previousMarker.setIcon(clubsIconProvider.provideReleasedDrawable(previousIsFavorite));
        }
        marker.setIcon(clubsIconProvider.provideSelectedDrawable(club.isFavorite()));
        callback.onClubSelected(marker);

        previousMarker = marker;
        previousIsFavorite = club.isFavorite();
        mapView.invalidate();
        return true;
    }

    public void setPrevious(Marker previousMarker, boolean previousIsFavorite) {
        this.previousMarker = previousMarker;
        this.previousIsFavorite = previousIsFavorite;
    }

    public void clear() {
        previousMarker = null;
        previousIsFavorite = false;
    }

    public interface OnScooterSelectedCallback {
        void onClubSelected(Marker clubMarker);
    }
}