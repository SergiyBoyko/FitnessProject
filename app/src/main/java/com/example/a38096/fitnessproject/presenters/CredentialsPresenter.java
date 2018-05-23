package com.example.a38096.fitnessproject.presenters;

import android.text.TextUtils;

import com.example.a38096.fitnessproject.model.ICredentialsDataSource;
import com.example.a38096.fitnessproject.model.IUserDataSource;
import com.example.a38096.fitnessproject.utils.rx.RxErrorAction;
import com.example.a38096.fitnessproject.utils.rx.RxRetryWithDelay;
import com.example.a38096.fitnessproject.views.CredentialsView;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Serhii Boiko on 14.05.2018.
 */
public class CredentialsPresenter extends BasePresenter<CredentialsView> {

    private final ICredentialsDataSource mCredentialsDataSource;

    private final IUserDataSource mUserDataSource;

    public CredentialsPresenter(ICredentialsDataSource mCredentialsDataSource, IUserDataSource mUserDataSource) {
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

//        getView().showSuccess();

        addSubscription(mCredentialsDataSource.updateUser(
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
                            getView().showSuccess();
                        }, new RxErrorAction(getView().getContext()))
        );
    }
}
