package com.example.a38096.fitnessproject.di.component;

import com.example.a38096.fitnessproject.model.ICredentialsDataSource;
import com.example.a38096.fitnessproject.model.ILoginDataSource;
import com.example.a38096.fitnessproject.model.IRegisterDataSource;
import com.example.a38096.fitnessproject.model.IUserDataSource;
import com.example.a38096.fitnessproject.model.IWorkoutDataSource;

import retrofit2.Retrofit;

/**
 * Created by Serhii Boiko on 01.05.2018.
 */
public interface ApiComponent {

    Retrofit retrofit();

    ILoginDataSource loginDataSource();

    IRegisterDataSource registerDataSource();

    IWorkoutDataSource workoutDataSource();

    ICredentialsDataSource credentialsDataSource();

    IUserDataSource userDataSource();

}
