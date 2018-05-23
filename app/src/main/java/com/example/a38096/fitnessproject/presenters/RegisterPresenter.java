package com.example.a38096.fitnessproject.presenters;

import android.text.TextUtils;
import android.util.Base64;

import com.example.a38096.fitnessproject.model.IRegisterDataSource;
import com.example.a38096.fitnessproject.model.IUserDataSource;
import com.example.a38096.fitnessproject.utils.rx.RxErrorAction;
import com.example.a38096.fitnessproject.utils.rx.RxRetryWithDelay;
import com.example.a38096.fitnessproject.views.RegisterView;

import java.io.UnsupportedEncodingException;

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
        String source = email + ":" + password;
        String base64 = null;
        try {
            base64 = "Basic " + Base64.encodeToString(source.trim().getBytes("UTF-8"), Base64.NO_WRAP);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        String finalBase64 = base64;
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
                    mDataSource.setBase64Data(finalBase64);
                    getView().goToMainActivity();

                }, new RxErrorAction(getView().getContext()))
        );
    }
}
