package com.example.a38096.fitnessproject.views;

import com.example.a38096.fitnessproject.model.entities.Workout;

import java.util.List;

/**
 * Created by Serhii Boiko on 06.05.2018.
 */
public interface WorkoutView extends BaseView {

    void showWorkouts(List<Workout> workouts);

    void deleteWorkout(int workoutId);
}
