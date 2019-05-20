package com.example.a38096.fitnessproject.utils;

import android.content.Context;
import android.graphics.drawable.Drawable;

import com.example.a38096.fitnessproject.R;

/**
 * Created by Serhii Boiko on 20.11.2018.
 */
public class ClubsIconProvider {
    private static final String SCOOTER_TYPE = "Scooter";
    private static final String TRICYCLE_TYPE = "Tricycle";

    private Drawable drawableClubReleased;
    private Drawable drawableClubSelected;

    private Drawable drawableFavoriteReleased;
    private Drawable drawableFavoriteSelected;

    public ClubsIconProvider(Context context) {
        drawableClubReleased = context.getResources().getDrawable(R.drawable.marker_club_disabled);
        drawableClubSelected = context.getResources().getDrawable(R.drawable.marker_club_enabled);
        drawableFavoriteReleased = context.getResources().getDrawable(R.drawable.marker_favorite_disable);
        drawableFavoriteSelected = context.getResources().getDrawable(R.drawable.marker_favorite_enable);
    }

    public Drawable provideReleasedDrawable(boolean isFavorite) {
        return isFavorite ? drawableFavoriteReleased : drawableClubReleased;
    }

    public Drawable provideSelectedDrawable(boolean isFavorite) {
        return isFavorite ? drawableFavoriteSelected : drawableClubSelected;
    }
}
