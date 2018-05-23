package com.example.a38096.fitnessproject.ui.activities;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.DatePicker;

import com.example.a38096.fitnessproject.AppFitness;
import com.example.a38096.fitnessproject.R;
import com.example.a38096.fitnessproject.common.Constants;
import com.example.a38096.fitnessproject.di.component.AppComponent;
import com.example.a38096.fitnessproject.di.component.DaggerPresentersComponent;
import com.example.a38096.fitnessproject.di.module.PresentersModule;
import com.example.a38096.fitnessproject.model.entities.Workout;
import com.example.a38096.fitnessproject.presenters.WorkoutPresenter;
import com.example.a38096.fitnessproject.views.WorkoutView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Serhii Boiko on 09.05.2018.
 */
public class EditWorkoutActivity extends AppCompatActivity implements
        DatePickerDialog.OnDateSetListener, WorkoutView {
    public static final int EMPTY_WORKOUT_ID = -100;
    @BindView(R.id.tietDate)
    TextInputEditText mTietDate;

    @BindView(R.id.tietType)
    TextInputEditText mTietType;

    @BindView(R.id.tietDistance)
    TextInputEditText mTietDistance;

    @BindView(R.id.tietDuration)
    TextInputEditText mTietDuration;

    @BindView(R.id.tietCalories)
    TextInputEditText mTietCalories;
    @Inject
    WorkoutPresenter mPresenter;
    private long mDate;

    private int workoutId;

    public static String formatDateNumber(int year, int month, int dayOfMonth) {
        if (month >= 0 && month <= 11) {
            String monthStr = (month + 1) < 10 ? ("0" + (month + 1)) : String.valueOf(month + 1);
            String dayStr = dayOfMonth < 10 ? ("0" + dayOfMonth) : String.valueOf(dayOfMonth);
            return String.format(Locale.ENGLISH, "%s.%s.%d", dayStr, monthStr, year);
        } else {
            return String.format(Locale.ENGLISH, "%d.%s.%d", dayOfMonth, "01", year);
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_workout);
        ButterKnife.bind(this);

        DaggerPresentersComponent.builder()
                .appComponent(getAppComponent())
                .presentersModule(new PresentersModule())
                .build()
                .inject(this);

        mPresenter.setView(this);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        Intent intent = getIntent();
        workoutId = intent.getIntExtra(getString(R.string.id_intent), EMPTY_WORKOUT_ID);

        mTietDistance.setText(String.valueOf(workoutId));
        mDate = intent.getLongExtra(getString(R.string.date_intent), 0L);
        SimpleDateFormat dateFormat = new SimpleDateFormat(Constants.FORMAT, Locale.ENGLISH);
        mTietDate.setText(dateFormat.format(new Date(mDate)));
        mTietType.setText(intent.getStringExtra(getString(R.string.type_intent)));
        mTietDistance.setText(String.valueOf(intent.getDoubleExtra(getString(R.string.distance_intent), 0)));
        mTietDuration.setText(String.valueOf(intent.getIntExtra(getString(R.string.duration_intent), 0)));
        mTietCalories.setText(String.valueOf(intent.getIntExtra(getString(R.string.calories_intent), 0)));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case android.R.id.home:
                onBackPressed();
        }
        return (super.onOptionsItemSelected(menuItem));
    }

    @OnClick(R.id.ivDate)
    protected void onBirthDateClick() {
        Calendar calendar = Calendar.getInstance();
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, this,
                calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.setCancelable(false);
        datePickerDialog.setTitle(getString(R.string.edit_date_workout_dialog_title));
        datePickerDialog.show();
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar birthDate = Calendar.getInstance();
        birthDate.set(Calendar.YEAR, year);
        birthDate.set(Calendar.MONTH, month);
        birthDate.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        mDate = birthDate.getTimeInMillis();

        mTietDate.setText(formatDateNumber(year, month, dayOfMonth));
        mTietDate.setActivated(false);
        mTietDate.clearFocus();
        mTietType.requestFocus();
    }

    @OnClick(R.id.btnSubmit)
    public void onSubmitClick() {
        if (workoutId == EMPTY_WORKOUT_ID) {
            mPresenter.createWorkout(
                    mTietType.getText().toString().trim(),
                    Integer.parseInt(mTietCalories.getText().toString().trim()),
                    Double.parseDouble(mTietDistance.getText().toString().trim()),
                    Integer.parseInt(mTietDuration.getText().toString().trim()),
                    mDate
            );
        } else {
//            mPresenter.updateWorkout();
        }
    }

    public AppComponent getAppComponent() {
        return getApp().appComponent();
    }

    private AppFitness getApp() {
        return (AppFitness) getApplication();
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void showWorkouts(List<Workout> workouts) {

    }

    @Override
    public void deleteWorkout(int workoutId) {

    }
}
