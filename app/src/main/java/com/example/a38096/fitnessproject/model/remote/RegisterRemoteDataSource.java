package com.example.a38096.fitnessproject.model.remote;

import com.example.a38096.fitnessproject.api.FitnessApi;
import com.example.a38096.fitnessproject.model.IRegisterDataSource;

import okhttp3.ResponseBody;
import rx.Observable;

/**
 * Created by Serhii Boiko on 06.05.2018.
 */
public class RegisterRemoteDataSource implements IRegisterDataSource {

    private FitnessApi fitnessApi;

    public RegisterRemoteDataSource(FitnessApi fitnessApi) {
        this.fitnessApi = fitnessApi;
    }

    @Override
    public Observable<ResponseBody> registerUser(int firstName, int lastName, int gender, int password, int email) {
        return fitnessApi.registerUser(firstName, lastName, gender, password, email);
    }
}
