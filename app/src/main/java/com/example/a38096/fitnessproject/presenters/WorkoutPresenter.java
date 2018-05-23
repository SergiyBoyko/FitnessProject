package com.example.a38096.fitnessproject.presenters;

import com.example.a38096.fitnessproject.model.IUserDataSource;
import com.example.a38096.fitnessproject.model.IWorkoutDataSource;
import com.example.a38096.fitnessproject.utils.rx.RxErrorAction;
import com.example.a38096.fitnessproject.utils.rx.RxRetryWithDelay;
import com.example.a38096.fitnessproject.views.WorkoutView;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Serhii Boiko on 06.05.2018.
 */
public class WorkoutPresenter extends BasePresenter<WorkoutView> {

    private final IWorkoutDataSource mWorkoutDataSource;

    private final IUserDataSource mDataSource;

    public WorkoutPresenter(IWorkoutDataSource mWorkoutDataSource, IUserDataSource mDataSource) {
        this.mWorkoutDataSource = mWorkoutDataSource;
        this.mDataSource = mDataSource;
    }

    public void getWorkouts() {
        addSubscription(mWorkoutDataSource.getWorkouts(
                mDataSource.getToken(), mDataSource.getBase64Data()
                )
                        .retryWhen(new RxRetryWithDelay())
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(workouts -> getView().showWorkouts(workouts),
                                new RxErrorAction(getView().getContext()))
        );
    }

    public void createWorkout(String type, int calories,
                              double distance, int duration, long workoutDate) {
        addSubscription(mWorkoutDataSource.createWorkout(
                mDataSource.getToken(), type, calories, distance, duration, workoutDate, mDataSource.getBase64Data()
                )
                        .retryWhen(new RxRetryWithDelay())
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(workouts -> getView().showWorkouts(null),
                                new RxErrorAction(getView().getContext()))
        );
    }

    public void updateWorkout(long workoutId, String type, int calories,
                              double distance, int duration, Long workoutDate) {
        addSubscription(mWorkoutDataSource.updateWorkout(
                mDataSource.getToken(), workoutId, type, calories, distance, duration, workoutDate,
                mDataSource.getBase64Data()
                )
                        .retryWhen(new RxRetryWithDelay())
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(workouts -> getView().showWorkouts(null),
                                new RxErrorAction(getView().getContext()))
        );
    }

    public void deleteWorkout(long workoutId) {
        addSubscription(mWorkoutDataSource.deleteWorkout(
                mDataSource.getToken(), (int) workoutId, mDataSource.getBase64Data()
                )
                        .retryWhen(new RxRetryWithDelay())
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(workouts -> getView().deleteWorkout((int) workoutId),
                                new RxErrorAction(getView().getContext()))
        );
    }
}
