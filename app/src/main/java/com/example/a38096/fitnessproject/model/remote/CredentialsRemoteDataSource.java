package com.example.a38096.fitnessproject.model.remote;

import com.example.a38096.fitnessproject.api.FitnessApi;
import com.example.a38096.fitnessproject.model.CredentialsDataSource;

import io.reactivex.Completable;
import io.reactivex.Observable;
import okhttp3.ResponseBody;

/**
 * Created by Serhii Boiko on 14.05.2018.
 */
public class CredentialsRemoteDataSource implements CredentialsDataSource {
    private FitnessApi fitnessApi;

    public CredentialsRemoteDataSource(FitnessApi fitnessApi) {
        this.fitnessApi = fitnessApi;
    }

    @Override
    public Completable updateUser(String uuid, String firstName, String lastName, String gender, String loginPasswordBase64) {
        return fitnessApi.updateUser(uuid, firstName, lastName, gender, loginPasswordBase64);
    }
}
