package com.example.a38096.fitnessproject.views;

/**
 * Created by Serhii Boiko on 06.05.2018.
 */
public interface RegisterView extends BaseView {

    void goToMainActivity();

    void showEmptyFirstNameError();

    void showEmptySecondNameError();

    void showEmptyEmailError();

    void showEmptyPasswordError();

    void showEmptyPasswordRepeatError();

    void showWrongPasswordRepeatError();
}
