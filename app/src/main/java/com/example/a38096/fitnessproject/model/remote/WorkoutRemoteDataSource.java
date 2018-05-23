package com.example.a38096.fitnessproject.model.remote;

import com.example.a38096.fitnessproject.api.FitnessApi;
import com.example.a38096.fitnessproject.model.IWorkoutDataSource;
import com.example.a38096.fitnessproject.model.entities.Workout;

import java.util.List;

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
    public Observable<ResponseBody> createWorkout(String uuid, String type, int calories, int distance, int duration, long workoutDate) {
        return fitnessApi.createWorkout(uuid, type, calories, distance, duration, workoutDate);
    }

    @Override
    public Observable<ResponseBody> updateWorkout(String uuid, int workoutId, String type, int calories, int distance, int duration, int workoutDate) {
        return fitnessApi.updateWorkout(uuid, workoutId, type, calories, distance, duration, workoutDate);
    }

    @Override
    public Observable<ResponseBody> deleteWorkout(String uuid, int workoutId) {
        return fitnessApi.deleteWorkout(uuid, workoutId);
    }

    @Override
    public Observable<List<Workout>> getWorkouts(String uuid) {
        return fitnessApi.getWorkouts(uuid);
    }
}
