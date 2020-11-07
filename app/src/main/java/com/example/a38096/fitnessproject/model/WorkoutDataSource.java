package com.example.a38096.fitnessproject.model;

import com.example.a38096.fitnessproject.model.entities.Workout;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Observable;

/**
 * Created by Serhii Boiko on 06.05.2018.
 */
public interface WorkoutDataSource {

	Completable createWorkout(String uuid,
							  String type,
							  int calories,
							  double distance,
							  int duration,
							  long workoutDate,
							  String loginPasswordBase64);

	Completable updateWorkout(String uuid,
							  long workoutId,
							  String type,
							  int calories,
							  double distance,
							  int duration,
							  long workoutDate,
							  String loginPasswordBase64);

	Completable deleteWorkout(String uuid,
							  int workoutId,
							  String loginPasswordBase64);

	Observable<List<Workout>> getWorkouts(String uuid,
										  String loginPasswordBase64);
}
