package com.example.a38096.fitnessproject.di.module;

import android.content.SharedPreferences;

import com.example.a38096.fitnessproject.api.FitnessApiImpl;
import com.example.a38096.fitnessproject.common.Constants;
import com.example.a38096.fitnessproject.model.ClubsDataSource;
import com.example.a38096.fitnessproject.model.CredentialsDataSource;
import com.example.a38096.fitnessproject.model.LoginDataSource;
import com.example.a38096.fitnessproject.model.RegisterDataSource;
import com.example.a38096.fitnessproject.model.UserDataSource;
import com.example.a38096.fitnessproject.model.WorkoutDataSource;
import com.example.a38096.fitnessproject.model.prefs.UserPrefsDataSource;
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
	LoginDataSource provideLoginDataSource(Retrofit retrofit) {
		return new LoginRemoteDataSource(new FitnessApiImpl());
	}

	@Provides
	@Singleton
	RegisterDataSource provideRegisterDataSource(Retrofit retrofit) {
		return new RegisterRemoteDataSource(new FitnessApiImpl());
	}

	@Provides
	@Singleton
	WorkoutDataSource provideWorkoutDataSource(Retrofit retrofit) {
		return new WorkoutRemoteDataSource(new FitnessApiImpl());
	}

	@Provides
	@Singleton
	CredentialsDataSource provideCredentialsDataSource(Retrofit retrofit) {
		return new CredentialsRemoteDataSource(new FitnessApiImpl());
	}

	@Provides
	@Singleton
	UserDataSource provideUserDataSource(SharedPreferences preferences) {
		return new UserPrefsDataSource(preferences);
	}

	@Provides
	@Singleton
	ClubsDataSource provideClubsDataSource(Retrofit retrofit) {
		return new ClubsRemoteDataSource(new FitnessApiImpl());
	}
}
