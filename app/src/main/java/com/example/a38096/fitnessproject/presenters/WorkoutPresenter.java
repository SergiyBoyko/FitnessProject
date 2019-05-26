package com.example.a38096.fitnessproject.presenters;

import com.example.a38096.fitnessproject.model.UserDataSource;
import com.example.a38096.fitnessproject.model.WorkoutDataSource;
import com.example.a38096.fitnessproject.utils.rx.RxErrorAction;
import com.example.a38096.fitnessproject.utils.rx.RxRetryWithDelay;
import com.example.a38096.fitnessproject.views.WorkoutView;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Serhii Boiko on 06.05.2018.
 */
public class WorkoutPresenter extends BasePresenter<WorkoutView> {

    private final WorkoutDataSource mWorkoutDataSource;

    private final UserDataSource mDataSource;

    public WorkoutPresenter(WorkoutDataSource mWorkoutDataSource, UserDataSource mDataSource) {
        this.mWorkoutDataSource = mWorkoutDataSource;
        this.mDataSource = mDataSource;
    }

    public void getWorkouts() {
        addDisposable(mWorkoutDataSource.getWorkouts(
                mDataSource.getToken(), mDataSource.getBase64Data()
                )
                        .retryWhen(new RxRetryWithDelay())
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                .subscribe(workouts -> view.showWorkouts(workouts),
                        new RxErrorAction(view))
        );
    }

    public void createWorkout(String type, int calories,
                              double distance, int duration, long workoutDate) {
        addDisposable(mWorkoutDataSource.createWorkout(
                mDataSource.getToken(), type, calories, distance, duration, workoutDate, mDataSource.getBase64Data()
                )
                        .retryWhen(new RxRetryWithDelay())
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                .subscribe(workouts -> view.showWorkouts(null),
                        new RxErrorAction(view))
        );
    }

    public void updateWorkout(long workoutId, String type, int calories,
                              double distance, int duration, Long workoutDate) {
        addDisposable(mWorkoutDataSource.updateWorkout(
                mDataSource.getToken(), workoutId, type, calories, distance, duration, workoutDate,
                mDataSource.getBase64Data()
                )
                        .retryWhen(new RxRetryWithDelay())
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                .subscribe(workouts -> view.showWorkouts(null),
                        new RxErrorAction(view))
        );
    }

    public void deleteWorkout(long workoutId) {
        addDisposable(mWorkoutDataSource.deleteWorkout(
                mDataSource.getToken(), (int) workoutId, mDataSource.getBase64Data()
                )
                        .retryWhen(new RxRetryWithDelay())
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                .subscribe(workouts -> view.applyDeletedWorkout((int) workoutId),
                        new RxErrorAction(view))
        );
    }
}
