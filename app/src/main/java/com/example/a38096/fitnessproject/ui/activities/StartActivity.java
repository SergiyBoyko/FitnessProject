package com.example.a38096.fitnessproject.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;

import com.example.a38096.fitnessproject.R;
import com.example.a38096.fitnessproject.model.IUserDataSource;
import com.example.a38096.fitnessproject.presenters.LoginPresenter;
import com.example.a38096.fitnessproject.views.LoginView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Serhii Boiko on 01.05.2018.
 */
public class StartActivity extends BaseAppCompatActivity<LoginView> implements LoginView {

    @BindView(R.id.tilEmail)
    protected TextInputLayout mTilEmail;

    @BindView(R.id.tilPassword)
    protected TextInputLayout mTilPassword;

    @BindView(R.id.tietEmail)
    protected TextInputEditText mTietEmail;

    @BindView(R.id.tietPassword)
    protected TextInputEditText mTietPassword;

    @Inject
    protected LoginPresenter presenter;

    @Inject
    protected IUserDataSource userDataSource;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        ButterKnife.bind(this);

        getPresentersComponent().inject(this);
        registerPresenterLifecycle(presenter, this);

        checkIfAuthorized();
    }

    private void checkIfAuthorized() {
        if (userDataSource.isAuthorized()) {
            goToMainActivity();
        }
    }

    @OnClick(R.id.tvRegisterButton)
    protected void onRegisterClick() {
        startActivity(new Intent(this, RegisterActivity.class));
        overridePendingTransition(R.anim.activity_down_open, R.anim.activity_down_close);
    }

    @OnClick(R.id.btnEnter)
    protected void onEnterClick() {
        presenter.onEnterClicked(
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

}
