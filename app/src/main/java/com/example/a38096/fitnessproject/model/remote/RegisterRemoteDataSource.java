package com.example.a38096.fitnessproject.model.remote;

import com.example.a38096.fitnessproject.api.FitnessApi;
import com.example.a38096.fitnessproject.model.RegisterDataSource;
import com.example.a38096.fitnessproject.model.entities.User;

import io.reactivex.Observable;

/**
 * Created by Serhii Boiko on 06.05.2018.
 */
public class RegisterRemoteDataSource implements RegisterDataSource {

    private FitnessApi fitnessApi;

    public RegisterRemoteDataSource(FitnessApi fitnessApi) {
        this.fitnessApi = fitnessApi;
    }

    @Override
    public Observable<User> registerUser(String firstName, String lastName, String email, String password, String gender) {
        return fitnessApi.registerUser(firstName, lastName, email, password, gender);
    }
}
