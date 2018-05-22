package com.example.a38096.fitnessproject.views;

/**
 * Created by Serhii Boiko on 06.05.2018.
 */
public interface LoginView extends BaseView {
    void showEmptyLoginError();

    void showEmptyPasswordError();

    void goToMainActivity();
}
