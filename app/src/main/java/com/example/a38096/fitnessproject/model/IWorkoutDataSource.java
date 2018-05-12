package com.example.a38096.fitnessproject.model;

import okhttp3.ResponseBody;
import rx.Observable;

/**
 * Created by Serhii Boiko on 06.05.2018.
 */
public interface IWorkoutDataSource {

    Observable<ResponseBody> createWorkout(int token,
                                           int type,
                                           int calories,
                                           int distance,
                                           int duration,
                                           int workoutDate);

    Observable<ResponseBody> updateWorkout(int token,
                                           int id,
                                           int type,
                                           int calories,
                                           int distance,
                                           int duration,
                                           int workoutDate);

    Observable<ResponseBody> deleteWorkout(int token,
                                           int id,
                                           int type,
                                           int calories,
                                           int distance,
                                           int duration,
                                           int workoutDate);

}
