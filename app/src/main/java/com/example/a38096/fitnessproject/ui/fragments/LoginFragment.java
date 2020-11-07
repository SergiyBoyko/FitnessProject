package com.example.a38096.fitnessproject.ui.fragments;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.a38096.fitnessproject.R;
import com.example.a38096.fitnessproject.model.UserDataSource;
import com.example.a38096.fitnessproject.presenters.LoginPresenter;
import com.example.a38096.fitnessproject.ui.activities.MainActivity;
import com.example.a38096.fitnessproject.views.LoginView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Serhii Boiko on 14.05.2019.
 */
public class LoginFragment extends BaseFragment<LoginView> implements LoginView {

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
    protected UserDataSource userDataSource;

    private Runnable onRegisterClick;

    public static LoginFragment newInstance(Runnable onRegisterClick) {
        LoginFragment loginFragment = new LoginFragment();
        loginFragment.setOnRegisterClick(onRegisterClick);
        return loginFragment;
    }

    public void setOnRegisterClick(Runnable onRegisterClick) {
        this.onRegisterClick = onRegisterClick;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);

        getPresentersComponent().inject(this);
        registerPresenterLifecycle(presenter, this);

        checkIfAuthorized();
    }

    private void checkIfAuthorized() {
        if (userDataSource.isAuthorized()) {
            onSuccessLogin();
        }
    }

    @OnClick(R.id.tvRegisterButton)
    protected void onRegisterClick() {
        onRegisterClick.run();
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
    public void onSuccessLogin() {
        getActivity().finish();
        startActivity(new Intent(getActivity(), MainActivity.class));
    }

}
