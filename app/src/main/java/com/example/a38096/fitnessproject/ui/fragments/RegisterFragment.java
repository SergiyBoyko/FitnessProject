package com.example.a38096.fitnessproject.ui.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.AppCompatRadioButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.a38096.fitnessproject.R;
import com.example.a38096.fitnessproject.presenters.RegisterPresenter;
import com.example.a38096.fitnessproject.ui.activities.MainActivity;
import com.example.a38096.fitnessproject.views.RegisterView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Serhii Boiko on 14.05.2019.
 */
public class RegisterFragment extends BaseFragment<RegisterView> implements RegisterView {

    @BindView(R.id.tilFirstName)
    TextInputLayout mTilFirstName;
    @BindView(R.id.tietFirstName)
    TextInputEditText mTietFirstName;

    @BindView(R.id.tilSecondName)
    TextInputLayout mTilSecondName;
    @BindView(R.id.tietSecondName)
    TextInputEditText mTietSecondName;

    @BindView(R.id.tilEmail)
    TextInputLayout mTilEmail;
    @BindView(R.id.tietEmail)
    TextInputEditText mTietEmail;

    @BindView(R.id.tilPassword)
    TextInputLayout mTilPassword;
    @BindView(R.id.tietPassword)
    TextInputEditText mTietPassword;

    @BindView(R.id.tilPasswordRepeat)
    TextInputLayout mTilPasswordRepeat;
    @BindView(R.id.tietPasswordRepeat)
    TextInputEditText mTietPasswordRepeat;

    @BindView(R.id.rbMale)
    AppCompatRadioButton mRbMale;

    @Inject
    RegisterPresenter presenter;

    public static RegisterFragment newInstance() {
        return new RegisterFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_register, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ButterKnife.bind(this, view);

        getPresentersComponent().inject(this);
        registerPresenterLifecycle(presenter, this);

    }

    @OnClick(R.id.btnSubmit)
    protected void onSubmitRegisterClick() {
        presenter.onRegisterClicked(
                mTietFirstName.getText().toString(),
                mTietSecondName.getText().toString(),
                mTietEmail.getText().toString(),
                mTietPassword.getText().toString(),
                mTietPasswordRepeat.getText().toString(),
                mRbMale.isChecked() ? "M" : "W"
        );
    }

    private void clearErrors() {
        mTilFirstName.setError(null);
        mTilSecondName.setError(null);
        mTilEmail.setError(null);
        mTilPassword.setError(null);
        mTilPasswordRepeat.setError(null);
    }

    @Override
    public void goToMainActivity() {
        getActivity().finish();
        startActivity(new Intent(getActivity(), MainActivity.class));
    }

    @Override
    public void showEmptyFirstNameError() {
        clearErrors();
        mTilFirstName.setError(getString(R.string.register_error_empty_first_name));
    }

    @Override
    public void showEmptySecondNameError() {
        clearErrors();
        mTilSecondName.setError(getString(R.string.register_error_empty_second_name));
    }

    @Override
    public void showEmptyEmailError() {
        clearErrors();
        mTilEmail.setError(getString(R.string.register_error_empty_email));
    }

    @Override
    public void showEmptyPasswordError() {
        clearErrors();
        mTilPassword.setError(getString(R.string.register_error_empty_password));
    }

    @Override
    public void showEmptyPasswordRepeatError() {
        clearErrors();
        mTilPasswordRepeat.setError(getString(R.string.register_error_empty_password_repeat));
    }

    @Override
    public void showWrongPasswordRepeatError() {
        clearErrors();
        mTilPasswordRepeat.setError(getString(R.string.register_error_wrong_password_repeat));
    }
}
