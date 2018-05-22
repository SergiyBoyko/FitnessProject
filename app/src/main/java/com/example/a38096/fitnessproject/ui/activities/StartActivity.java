package com.example.a38096.fitnessproject.ui.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;

import com.example.a38096.fitnessproject.AppFitness;
import com.example.a38096.fitnessproject.R;
import com.example.a38096.fitnessproject.di.component.AppComponent;
import com.example.a38096.fitnessproject.di.component.DaggerPresentersComponent;
import com.example.a38096.fitnessproject.di.module.PresentersModule;
import com.example.a38096.fitnessproject.presenters.LoginPresenter;
import com.example.a38096.fitnessproject.views.LoginView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Serhii Boiko on 01.05.2018.
 */
public class StartActivity extends AppCompatActivity implements LoginView {

    @BindView(R.id.tilEmail)
    TextInputLayout mTilEmail;

    @BindView(R.id.tilPassword)
    TextInputLayout mTilPassword;

    @BindView(R.id.tietEmail)
    TextInputEditText mTietEmail;

    @BindView(R.id.tietPassword)
    TextInputEditText mTietPassword;

    @Inject
    LoginPresenter mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        ButterKnife.bind(this);

        DaggerPresentersComponent.builder()
                .appComponent(getAppComponent())
                .presentersModule(new PresentersModule())
                .build()
                .inject(this);

        mPresenter.setView(this);
    }

    @OnClick(R.id.tvRegisterButton)
    protected void onRegisterClick() {
        startActivity(new Intent(this, RegisterActivity.class));
        overridePendingTransition(R.anim.activity_down_open, R.anim.activity_down_close);
    }

    @OnClick(R.id.btnEnter)
    protected void onEnterClick() {
        mPresenter.onEnterClicked(
                mTietEmail.getText().toString(),
                mTietPassword.getText().toString()
        );
    }

    private void clearErrors() {
        mTilPassword.setError(null);
        mTilEmail.setError(null);
    }

    @Override
    public void showEmptyLoginError() {
        clearErrors();
        mTilEmail.setError(getString(R.string.login_error_empty_login));
    }

    @Override
    public void showEmptyPasswordError() {
        clearErrors();
        mTilPassword.setError(getString(R.string.login_error_empty_password));
    }

    @Override
    public void goToMainActivity() {
        startActivity(new Intent(this, MainActivity.class));
    }

    @Override
    public Context getContext() {
        return this;
    }

    public AppComponent getAppComponent() {
        return getApp().appComponent();
    }

    private AppFitness getApp() {
        return (AppFitness) getApplication();
    }
}
