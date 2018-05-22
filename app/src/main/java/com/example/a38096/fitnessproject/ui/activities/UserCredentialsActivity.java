package com.example.a38096.fitnessproject.ui.activities;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatRadioButton;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.a38096.fitnessproject.AppFitness;
import com.example.a38096.fitnessproject.R;
import com.example.a38096.fitnessproject.di.component.AppComponent;
import com.example.a38096.fitnessproject.di.component.DaggerPresentersComponent;
import com.example.a38096.fitnessproject.di.module.PresentersModule;
import com.example.a38096.fitnessproject.model.IUserDataSource;
import com.example.a38096.fitnessproject.presenters.CredentialsPresenter;
import com.example.a38096.fitnessproject.views.CredentialsView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Serhii Boiko on 13.05.2018.
 */
public class UserCredentialsActivity extends AppCompatActivity implements CredentialsView {

    @BindView(R.id.tilFirstName)
    TextInputLayout mTilFirstName;
    @BindView(R.id.tietFirstName)
    TextInputEditText mTietFirstName;

    @BindView(R.id.tilSecondName)
    TextInputLayout mTilSecondName;
    @BindView(R.id.tietSecondName)
    TextInputEditText mTietSecondName;

    @BindView(R.id.rbMale)
    AppCompatRadioButton mRbMale;

    @BindView(R.id.rbFemale)
    AppCompatRadioButton mRbFemale;

    @Inject
    CredentialsPresenter mPresenter;

    @Inject
    IUserDataSource mUserDataSource;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_credentials);

        ButterKnife.bind(this);

        DaggerPresentersComponent.builder()
                .appComponent(getAppComponent())
                .presentersModule(new PresentersModule())
                .build()
                .inject(this);

        mPresenter.setView(this);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        initUserData();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case android.R.id.home:
                onBackPressed();
        }
        return (super.onOptionsItemSelected(menuItem));
    }

    private void initUserData() {
        if (mUserDataSource.isAuthorized()) {
            mTietFirstName.setText(mUserDataSource.getFirstName());
            mTietSecondName.setText(mUserDataSource.getSecondName());
            mRbMale.setChecked(mUserDataSource.getGender().equals("M"));
            mRbFemale.setChecked(mUserDataSource.getGender().equals("W"));
        }
    }

    @OnClick(R.id.btnSubmit)
    public void onSubmitClick() {
        mPresenter.updateUser(
                mTietFirstName.getText().toString().trim(),
                mTietSecondName.getText().toString().trim(),
                mRbMale.isChecked() ? "M" : "W"
        );
    }

    @Override
    public void showSuccess() {
        clearErrors();
        Toast.makeText(this, "Completed!", Toast.LENGTH_SHORT).show();
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

    private void clearErrors() {
        mTilFirstName.setError(null);
        mTilSecondName.setError(null);
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
