package com.example.a38096.fitnessproject.model;

import com.example.a38096.fitnessproject.model.entities.Club;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Response;

/**
 * Created by Serhii Boiko on 20.05.2019.
 */
public interface ClubsDataSource {
    Observable<List<Club>> fetchClubs(String exerciserUuid, String loginPasswordBase64);

    Observable<Response<Void>> addToFavorites(
            String exerciserUuid,
            String uuid,
            String loginPasswordBase64);

    Observable<Response<Void>> removeFromFavorites(
            String exerciserUuid,
            String uuid,
            String loginPasswordBase64);
}
