package com.example.a38096.fitnessproject.model.remote;

import com.example.a38096.fitnessproject.api.FitnessApi;
import com.example.a38096.fitnessproject.model.WorkoutDataSource;
import com.example.a38096.fitnessproject.model.entities.Workout;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Observable;

/**
 * Created by Serhii Boiko on 06.05.2018.
 */
public class WorkoutRemoteDataSource implements WorkoutDataSource {

	private FitnessApi fitnessApi;

	public WorkoutRemoteDataSource(FitnessApi fitnessApi) {
		this.fitnessApi = fitnessApi;
	}

	@Override
	public Completable createWorkout(String uuid, String type, int calories, double distance, int duration, long workoutDate,
									 String loginPasswordBase64) {
		return fitnessApi.createWorkout(uuid, type, calories, distance, duration, workoutDate, loginPasswordBase64);
	}

	@Override
	public Completable updateWorkout(String uuid,
									 long workoutId,
									 String type,
									 int calories,
									 double distance,
									 int duration,
									 long workoutDate,
									 String loginPasswordBase64) {
		return fitnessApi.updateWorkout(uuid, workoutId, type, calories, distance, duration, workoutDate, loginPasswordBase64);
	}

	@Override
	public Completable deleteWorkout(String uuid, int workoutId,
									 String loginPasswordBase64) {
		return fitnessApi.deleteWorkout(uuid, workoutId, loginPasswordBase64);
	}

	@Override
	public Observable<List<Workout>> getWorkouts(String uuid,
												 String loginPasswordBase64) {
		return fitnessApi.getWorkouts(uuid, loginPasswordBase64);
	}
}
