package com.example.a38096.fitnessproject.model.remote;

import com.example.a38096.fitnessproject.api.FitnessApi;
import com.example.a38096.fitnessproject.model.IWorkoutDataSource;

import okhttp3.ResponseBody;
import rx.Observable;

/**
 * Created by Serhii Boiko on 06.05.2018.
 */
public class WorkoutRemoteDataSource implements IWorkoutDataSource {

    private FitnessApi fitnessApi;

    public WorkoutRemoteDataSource(FitnessApi fitnessApi) {
        this.fitnessApi = fitnessApi;
    }

    @Override
    public Observable<ResponseBody> createWorkout(int token, int type, int calories, int distance, int duration, int workoutDate) {
        return fitnessApi.createWorkout(token, type, calories, distance, duration, workoutDate);
    }

    @Override
    public Observable<ResponseBody> updateWorkout(int token, int id, int type, int calories, int distance, int duration, int workoutDate) {
        return fitnessApi.updateWorkout(token, id, type, calories, distance, duration, workoutDate);
    }

    @Override
    public Observable<ResponseBody> deleteWorkout(int token, int id, int type, int calories, int distance, int duration, int workoutDate) {
        return fitnessApi.deleteWorkout(token, id, type, calories, distance, duration, workoutDate);
    }
}
