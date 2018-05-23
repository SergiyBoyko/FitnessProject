package com.example.a38096.fitnessproject.presenters;

import android.text.TextUtils;
import android.util.Base64;

import com.example.a38096.fitnessproject.model.ILoginDataSource;
import com.example.a38096.fitnessproject.model.IUserDataSource;
import com.example.a38096.fitnessproject.utils.rx.RxErrorAction;
import com.example.a38096.fitnessproject.utils.rx.RxRetryWithDelay;
import com.example.a38096.fitnessproject.views.LoginView;

import java.io.UnsupportedEncodingException;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Serhii Boiko on 06.05.2018.
 */
public class LoginPresenter extends BasePresenter<LoginView> {

    private final ILoginDataSource mLoginDataSource;

    private final IUserDataSource mDataSource;

    public LoginPresenter(ILoginDataSource mLoginDataSource, IUserDataSource mDataSource) {
        this.mLoginDataSource = mLoginDataSource;
        this.mDataSource = mDataSource;
    }

    public void onEnterClicked(String login, String password) {
        if (isNotValidLoginPassword(login, password)) {
            return;
        }
        // TODO: 13.05.2018 fix encode base64
        String source = login + ":" + password;
        String base64 = null;
        try {
            base64 = "Basic " + Base64.encodeToString(source.trim().getBytes("UTF-8"), Base64.NO_WRAP);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        login(base64);
//        getView().goToMainActivity();
    }

    private boolean isNotValidLoginPassword(String login, String password) {
        if (TextUtils.isEmpty(login)) {
            getView().showEmptyLoginError();
            return true;
        }
        if (TextUtils.isEmpty(password)) {
            getView().showEmptyPasswordError();
            return true;
        }
        return false;
    }

    private void login(String loginPasswordBase64) {
        addSubscription(mLoginDataSource.login(loginPasswordBase64)
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
