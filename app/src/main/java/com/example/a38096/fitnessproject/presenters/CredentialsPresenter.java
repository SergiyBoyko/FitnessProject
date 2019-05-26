package com.example.a38096.fitnessproject.presenters;

import android.text.TextUtils;

import com.example.a38096.fitnessproject.model.CredentialsDataSource;
import com.example.a38096.fitnessproject.model.UserDataSource;
import com.example.a38096.fitnessproject.utils.rx.RxErrorAction;
import com.example.a38096.fitnessproject.utils.rx.RxRetryWithDelay;
import com.example.a38096.fitnessproject.views.CredentialsView;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Serhii Boiko on 14.05.2018.
 */
public class CredentialsPresenter extends BasePresenter<CredentialsView> {

    private final CredentialsDataSource mCredentialsDataSource;

    private final UserDataSource mUserDataSource;

    public CredentialsPresenter(CredentialsDataSource mCredentialsDataSource, UserDataSource mUserDataSource) {
        this.mCredentialsDataSource = mCredentialsDataSource;
        this.mUserDataSource = mUserDataSource;
    }

    private boolean isNotValidRegisterData(String firstName, String lastName) {
        if (TextUtils.isEmpty(firstName)) {
            getView().showEmptyFirstNameError();
            return true;
        }
        if (TextUtils.isEmpty(lastName)) {
            getView().showEmptySecondNameError();
            return true;
        }
        return false;
    }

    public void updateUser(String firstName, String lastName, String gender) {
        if (isNotValidRegisterData(firstName, lastName)) {
            return;
        }

//        getView().showSuccessUpdated();

        addDisposable(mCredentialsDataSource.updateUser(
                mUserDataSource.getToken(),
                firstName,
                lastName,
                gender,
                mUserDataSource.getBase64Data()
                )
                        .retryWhen(new RxRetryWithDelay())
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(responseBody -> {
                            mUserDataSource.setFirstName(firstName);
                            mUserDataSource.setSecondName(lastName);
                            mUserDataSource.setGender(gender);
                            getView().showSuccessUpdated();
                        }, new RxErrorAction(view))
        );
    }
}
