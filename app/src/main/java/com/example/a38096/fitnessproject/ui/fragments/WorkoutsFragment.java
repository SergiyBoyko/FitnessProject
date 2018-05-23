package com.example.a38096.fitnessproject.ui.fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.a38096.fitnessproject.AppFitness;
import com.example.a38096.fitnessproject.R;
import com.example.a38096.fitnessproject.di.component.AppComponent;
import com.example.a38096.fitnessproject.di.component.DaggerPresentersComponent;
import com.example.a38096.fitnessproject.di.module.PresentersModule;
import com.example.a38096.fitnessproject.model.entities.Workout;
import com.example.a38096.fitnessproject.presenters.WorkoutPresenter;
import com.example.a38096.fitnessproject.ui.activities.EditWorkoutActivity;
import com.example.a38096.fitnessproject.views.WorkoutView;
import com.example.a38096.fitnessproject.widgets.adapters.WorkoutAdapter;

import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Serhii Boiko on 05.05.2018.
 */
public class WorkoutsFragment extends Fragment implements WorkoutView, WorkoutAdapter.OnWorkoutClickListener {
    @BindView(R.id.rvRecyclerView)
    RecyclerView recyclerView;

    @Inject
    WorkoutPresenter mPresenter;

    private WorkoutAdapter mAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_workout, container, false);

        ButterKnife.bind(this, view);

        DaggerPresentersComponent.builder()
                .appComponent(getAppComponent())
                .presentersModule(new PresentersModule())
                .build()
                .inject(this);

        mPresenter.setView(this);

        mAdapter = new WorkoutAdapter(loadTestData(), this);
//        mPresenter.getWorkouts();

        recyclerView.setAdapter(mAdapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        return view;
    }

    private List<Workout> loadTestData() {
        Workout workout = new Workout();
        workout.setWorkoutDate(1527057000000L);
        workout.setType("Cross");
        workout.setCalories(50);
        workout.setDistance(51.15);
        workout.setDuration(52);
        workout.setWorkoutId(1L);
        return Arrays.asList(workout, workout, workout, workout, workout, workout, workout);
    }

    @OnClick(R.id.fab)
    protected void onCreateNewWorkoutClick() {
        Intent intent = new Intent(getActivity(), EditWorkoutActivity.class);
        intent.putExtra(getString(R.string.id_intent), EditWorkoutActivity.EMPTY_WORKOUT_ID);
        startActivity(intent);
    }

    public AppComponent getAppComponent() {
        return getApp().appComponent();
    }

    private AppFitness getApp() {
        return (AppFitness) getActivity().getApplication();
    }

    @Override
    public void onWorkoutClick(Workout workout) {
        Intent intent = new Intent(getActivity(), EditWorkoutActivity.class);
        intent.putExtra(getString(R.string.id_intent), workout.getWorkoutId());
        intent.putExtra(getString(R.string.date_intent), workout.getWorkoutDate());
        intent.putExtra(getString(R.string.type_intent), workout.getType());
        intent.putExtra(getString(R.string.calories_intent), workout.getCalories());
        intent.putExtra(getString(R.string.duration_intent), workout.getDuration());
        intent.putExtra(getString(R.string.distance_intent), workout.getDistance());
        startActivity(intent);
    }


    @Override
    public void onWorkoutLongClick(Workout workout) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("Delete this workout?")
                .setPositiveButton(R.string.delete_action, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //mPresenter.deleteWorkout(workout.getWorkoutId());
                    }
                })
                .setNegativeButton(R.string.cancel_action, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the dialog
                    }
                });
        // Create the AlertDialog object and return it
        builder.create().show();
    }

    @Override
    public void showWorkouts(List<Workout> workouts) {
        mAdapter.updateItems(workouts);
    }

    @Override
    public void deleteWorkout(int workoutId) {
        mAdapter.removeWorkout(workoutId);
    }
}
