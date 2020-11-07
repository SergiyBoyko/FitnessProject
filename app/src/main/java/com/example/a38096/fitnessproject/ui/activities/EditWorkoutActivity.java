package com.example.a38096.fitnessproject.ui.activities;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import com.google.android.material.textfield.TextInputEditText;
import android.view.MenuItem;
import android.widget.DatePicker;

import com.example.a38096.fitnessproject.R;
import com.example.a38096.fitnessproject.common.Constants;
import com.example.a38096.fitnessproject.model.entities.Workout;
import com.example.a38096.fitnessproject.presenters.WorkoutPresenter;
import com.example.a38096.fitnessproject.views.WorkoutView;

import java.text.DateFormat;
import java.text.ParseException;
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
public class EditWorkoutActivity extends BaseAppCompatActivity<WorkoutView> implements
        DatePickerDialog.OnDateSetListener, WorkoutView {
    public static final long EMPTY_WORKOUT_ID = -100L;
    @BindView(R.id.tietDate)
    protected TextInputEditText dateInput;

    @BindView(R.id.tietType)
    protected TextInputEditText typeInput;

    @BindView(R.id.tietDistance)
    protected TextInputEditText distanceInput;

    @BindView(R.id.tietDuration)
    protected TextInputEditText durationInput;

    @BindView(R.id.tietCalories)
    protected TextInputEditText caloriesInput;
    @Inject
    protected WorkoutPresenter presenter;
    private long mDate;

    private long workoutId;

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

        getPresentersComponent().inject(this);
        registerPresenterLifecycle(presenter, this);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        Intent intent = getIntent();
        workoutId = intent.getLongExtra(getString(R.string.id_intent), EMPTY_WORKOUT_ID);

        distanceInput.setText(String.valueOf(workoutId));
        mDate = intent.getLongExtra(getString(R.string.date_intent), 0L);
        SimpleDateFormat dateFormat = new SimpleDateFormat(Constants.FORMAT, Locale.ENGLISH);
        dateInput.setText(dateFormat.format(new Date(mDate)));
        typeInput.setText(intent.getStringExtra(getString(R.string.type_intent)));
        distanceInput.setText(String.valueOf(intent.getDoubleExtra(getString(R.string.distance_intent), 0)));
        durationInput.setText(String.valueOf(intent.getIntExtra(getString(R.string.duration_intent), 0)));
        caloriesInput.setText(String.valueOf(intent.getIntExtra(getString(R.string.calories_intent), 0)));
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

    @OnClick(R.id.tietDate)
    public void onBirthDateFocused() {
        onBirthDateClick();
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar birthDate = Calendar.getInstance();
        birthDate.set(Calendar.YEAR, year);
        birthDate.set(Calendar.MONTH, month);
        birthDate.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        mDate = birthDate.getTimeInMillis();

        dateInput.setText(formatDateNumber(year, month, dayOfMonth));
        dateInput.setActivated(false);
        dateInput.clearFocus();
        typeInput.requestFocus();
    }

    @OnClick(R.id.btnSubmit)
    public void onSubmitClick() {
        DateFormat format = new SimpleDateFormat(Constants.FORMAT, Locale.ENGLISH);
        try {
            mDate = format.parse(dateInput.getText().toString().trim()).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        if (workoutId == EMPTY_WORKOUT_ID) {
            presenter.createWorkout(
                    typeInput.getText().toString().trim(),
                    Integer.parseInt(caloriesInput.getText().toString().trim()),
                    Double.parseDouble(distanceInput.getText().toString().trim()),
                    Integer.parseInt(durationInput.getText().toString().trim()),
                    mDate
            );
        } else {
            presenter.updateWorkout(workoutId, typeInput.getText().toString().trim(),
                    Integer.parseInt(caloriesInput.getText().toString().trim()),
                    Double.parseDouble(distanceInput.getText().toString().trim()),
                    Integer.parseInt(durationInput.getText().toString().trim()),
                    mDate);
        }
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void showWorkouts(List<Workout> workouts) {
        finish();
    }

    @Override
    public void applyDeletedWorkout(int workoutId) {

    }
}
