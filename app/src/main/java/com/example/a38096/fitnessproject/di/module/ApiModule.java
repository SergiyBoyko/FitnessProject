package com.example.a38096.fitnessproject.di.module;

import android.content.SharedPreferences;

import com.example.a38096.fitnessproject.api.FitnessApi;
import com.example.a38096.fitnessproject.common.Constants;
import com.example.a38096.fitnessproject.model.ClubsDataSource;
import com.example.a38096.fitnessproject.model.ICredentialsDataSource;
import com.example.a38096.fitnessproject.model.ILoginDataSource;
import com.example.a38096.fitnessproject.model.IRegisterDataSource;
import com.example.a38096.fitnessproject.model.IUserDataSource;
import com.example.a38096.fitnessproject.model.IWorkoutDataSource;
import com.example.a38096.fitnessproject.model.prefs.UserDataSource;
import com.example.a38096.fitnessproject.model.remote.ClubsRemoteDataSource;
import com.example.a38096.fitnessproject.model.remote.CredentialsRemoteDataSource;
import com.example.a38096.fitnessproject.model.remote.LoginRemoteDataSource;
import com.example.a38096.fitnessproject.model.remote.RegisterRemoteDataSource;
import com.example.a38096.fitnessproject.model.remote.WorkoutRemoteDataSource;
import com.example.a38096.fitnessproject.utils.retrofit.NullOnEmptyConverterFactory;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Serhii Boiko on 01.05.2018.
 */

@Module
public class ApiModule {

    @Provides
    @Singleton
    public Retrofit provideRetrofit() {
        return new Retrofit.Builder()
                .baseUrl(Constants.BASE_API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addConverterFactory(new NullOnEmptyConverterFactory())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    @Provides
    @Singleton
    ILoginDataSource provideLoginDataSource(Retrofit retrofit) {
        return new LoginRemoteDataSource(retrofit.create(FitnessApi.class));
    }

    @Provides
    @Singleton
    IRegisterDataSource provideRegisterDataSource(Retrofit retrofit) {
        return new RegisterRemoteDataSource(retrofit.create(FitnessApi.class));
    }

    @Provides
    @Singleton
    IWorkoutDataSource provideWorkoutDataSource(Retrofit retrofit) {
        return new WorkoutRemoteDataSource(retrofit.create(FitnessApi.class));
    }

    @Provides
    @Singleton
    ICredentialsDataSource provideCredentialsDataSource(Retrofit retrofit) {
        return new CredentialsRemoteDataSource(retrofit.create(FitnessApi.class));
    }

    @Provides
    @Singleton
    IUserDataSource provideUserDataSource(SharedPreferences preferences) {
        return new UserDataSource(preferences);
    }

    @Provides
    @Singleton
    ClubsDataSource provideClubsDataSource(Retrofit retrofit) {
        return new ClubsRemoteDataSource(retrofit.create(FitnessApi.class));
    }

}
