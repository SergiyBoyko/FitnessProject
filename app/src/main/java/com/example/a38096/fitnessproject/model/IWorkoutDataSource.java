package com.example.a38096.fitnessproject.model;

import com.example.a38096.fitnessproject.model.entities.Workout;

import java.util.List;

import okhttp3.ResponseBody;
import rx.Observable;

/**
 * Created by Serhii Boiko on 06.05.2018.
 */
public interface IWorkoutDataSource {

    Observable<ResponseBody> createWorkout(String uuid,
                                           String type,
                                           int calories,
                                           int distance,
                                           int duration,
                                           long workoutDate);

    Observable<ResponseBody> updateWorkout(String uuid,
                                           int workoutId,
                                           String type,
                                           int calories,
                                           int distance,
                                           int duration,
                                           int workoutDate);

    Observable<ResponseBody> deleteWorkout(String uuid,
                                           int workoutId);


    Observable<List<Workout>> getWorkouts(String uuid);

}
