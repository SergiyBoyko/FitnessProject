package com.example.a38096.fitnessproject.model.remote;

import com.example.a38096.fitnessproject.api.FitnessApi;
import com.example.a38096.fitnessproject.model.ClubsDataSource;
import com.example.a38096.fitnessproject.model.entities.Club;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Response;

/**
 * Created by Serhii Boiko on 20.05.2019.
 */
public class ClubsRemoteDataSource implements ClubsDataSource {
    private final FitnessApi fitnessApi;

    public ClubsRemoteDataSource(FitnessApi fitnessApi) {
        this.fitnessApi = fitnessApi;
    }

    @Override
    public Observable<List<Club>> fetchClubs(String exerciserUuid, String loginPasswordBase64) {
        return fitnessApi.getFavorites(exerciserUuid, loginPasswordBase64);
    }

    @Override
    public Observable<Response<Void>> addToFavorites(String exerciserUuid, String uuid, String loginPasswordBase64) {
        return fitnessApi.addToFavorites(exerciserUuid, uuid, loginPasswordBase64);
    }

    @Override
    public Observable<Response<Void>> removeFromFavorites(String exerciserUuid, String uuid, String loginPasswordBase64) {
        return fitnessApi.removeFromFavorites(exerciserUuid, uuid, loginPasswordBase64);
    }
}
