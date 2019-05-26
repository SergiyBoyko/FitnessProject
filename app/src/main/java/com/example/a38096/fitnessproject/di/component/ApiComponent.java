package com.example.a38096.fitnessproject.di.component;

import com.example.a38096.fitnessproject.model.ClubsDataSource;
import com.example.a38096.fitnessproject.model.CredentialsDataSource;
import com.example.a38096.fitnessproject.model.LoginDataSource;
import com.example.a38096.fitnessproject.model.RegisterDataSource;
import com.example.a38096.fitnessproject.model.UserDataSource;
import com.example.a38096.fitnessproject.model.WorkoutDataSource;

import retrofit2.Retrofit;

/**
 * Created by Serhii Boiko on 01.05.2018.
 */
public interface ApiComponent {

    Retrofit retrofit();

    LoginDataSource loginDataSource();

    RegisterDataSource registerDataSource();

    WorkoutDataSource workoutDataSource();

    CredentialsDataSource credentialsDataSource();

    UserDataSource userDataSource();

    ClubsDataSource clubsDataSource();

}
