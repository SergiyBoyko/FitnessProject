package com.example.a38096.fitnessproject.model.remote;

import com.example.a38096.fitnessproject.api.FitnessApi;
import com.example.a38096.fitnessproject.model.ICredentialsDataSource;

import okhttp3.ResponseBody;
import rx.Observable;

/**
 * Created by Serhii Boiko on 14.05.2018.
 */
public class CredentialsRemoteDataSource implements ICredentialsDataSource {
    private FitnessApi fitnessApi;

    public CredentialsRemoteDataSource(FitnessApi fitnessApi) {
        this.fitnessApi = fitnessApi;
    }

    @Override
    public Observable<ResponseBody> updateUser(String uuid, String firstName, String lastName, String gender, String loginPasswordBase64) {
        return fitnessApi.updateUser(uuid, firstName, lastName, gender, loginPasswordBase64);
    }
}
