package com.example.a38096.fitnessproject.model.remote;

import com.example.a38096.fitnessproject.api.FitnessApi;
import com.example.a38096.fitnessproject.model.ILoginDataSource;
import com.example.a38096.fitnessproject.model.entities.User;

import io.reactivex.Observable;

/**
 * Created by Serhii Boiko on 06.05.2018.
 */
public class LoginRemoteDataSource implements ILoginDataSource {
    private final FitnessApi fitnessApi;

    public LoginRemoteDataSource(FitnessApi fitnessApi) {
        this.fitnessApi = fitnessApi;
    }

    @Override
    public Observable<User> login(String loginPasswordBase64) {
        return fitnessApi.login(loginPasswordBase64);
    }
}
