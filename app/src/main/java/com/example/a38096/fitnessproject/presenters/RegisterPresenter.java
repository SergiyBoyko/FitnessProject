package com.example.a38096.fitnessproject.presenters;

import android.text.TextUtils;

import com.example.a38096.fitnessproject.model.IRegisterDataSource;
import com.example.a38096.fitnessproject.model.IUserDataSource;
import com.example.a38096.fitnessproject.utils.rx.RxErrorAction;
import com.example.a38096.fitnessproject.utils.rx.RxRetryWithDelay;
import com.example.a38096.fitnessproject.views.RegisterView;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Serhii Boiko on 06.05.2018.
 */
public class RegisterPresenter extends BasePresenter<RegisterView> {

    private final IRegisterDataSource registerDataSource;

    private final IUserDataSource mDataSource;

    public RegisterPresenter(IRegisterDataSource registerDataSource, IUserDataSource mDataSource) {
        this.registerDataSource = registerDataSource;
        this.mDataSource = mDataSource;
    }

    public void onRegisterClicked(String firstName, String lastName, String email,
                                  String password, String passwordRepeat, String gender) {
        if (isNotValidRegisterData(firstName, lastName, email, password, passwordRepeat)) {
            return;
        }
        // TODO: 12.05.2018 add login request
        register(firstName, lastName, email, password, gender);
//        getView().goToMainActivity();
    }

    private boolean isNotValidRegisterData(String firstName, String lastName, String email,
                                           String password, String passwordRepeat) {
        if (TextUtils.isEmpty(firstName)) {
            getView().showEmptyFirstNameError();
            return true;
        }
        if (TextUtils.isEmpty(lastName)) {
            getView().showEmptySecondNameError();
            return true;
        }
        if (TextUtils.isEmpty(email)) {
            getView().showEmptyeMailError();
            return true;
        }
        if (TextUtils.isEmpty(password)) {
            getView().showEmptyPasswordError();
            return true;
        }
        if (TextUtils.isEmpty(passwordRepeat)) {
            getView().showEmptyPasswordRepeatError();
            return true;
        }
        if (!password.equals(passwordRepeat)) {
            getView().showWrongPasswordRepeatError();
            return true;
        }
        return false;
    }

    private void register(String firstName, String lastName, String email, String password, String gender) {
        addSubscription(registerDataSource.registerUser(firstName, lastName, email, password, gender)
                .retryWhen(new RxRetryWithDelay())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(user -> {
                    mDataSource.setAuthorized();
                    mDataSource.setToken(user.getUuid());
                    mDataSource.setFirstName(user.getFirstName());
                    mDataSource.setSecondName(user.getLastName());
                    mDataSource.setGender(user.getGender());
                    getView().goToMainActivity();

                }, new RxErrorAction(getView().getContext()))
        );
    }
}
