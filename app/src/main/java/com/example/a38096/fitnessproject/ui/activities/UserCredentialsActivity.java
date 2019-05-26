package com.example.a38096.fitnessproject.ui.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.AppCompatRadioButton;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.a38096.fitnessproject.R;
import com.example.a38096.fitnessproject.model.UserDataSource;
import com.example.a38096.fitnessproject.presenters.CredentialsPresenter;
import com.example.a38096.fitnessproject.views.CredentialsView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Serhii Boiko on 13.05.2018.
 */
public class UserCredentialsActivity extends BaseAppCompatActivity<CredentialsView> implements CredentialsView {

    @BindView(R.id.tilFirstName)
    protected TextInputLayout mTilFirstName;
    @BindView(R.id.tietFirstName)
    protected TextInputEditText mTietFirstName;

    @BindView(R.id.tilSecondName)
    protected TextInputLayout mTilSecondName;
    @BindView(R.id.tietSecondName)
    protected TextInputEditText mTietSecondName;

    @BindView(R.id.rbMale)
    protected AppCompatRadioButton mRbMale;

    @BindView(R.id.rbFemale)
    protected AppCompatRadioButton mRbFemale;

    @Inject
    protected CredentialsPresenter presenter;

    @Inject
    protected UserDataSource userDataSource;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credentials);
        ButterKnife.bind(this);

        getPresentersComponent().inject(this);
        registerPresenterLifecycle(presenter, this);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        initUserData();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return (super.onOptionsItemSelected(menuItem));
    }

    private void initUserData() {
        if (userDataSource.isAuthorized()) {
            mTietFirstName.setText(userDataSource.getFirstName());
            mTietSecondName.setText(userDataSource.getSecondName());
            mRbMale.setChecked(userDataSource.getGender().equals("M"));
            mRbFemale.setChecked(userDataSource.getGender().equals("W"));
        }
    }

    @OnClick(R.id.btnSubmit)
    public void onSubmitClick() {
        presenter.updateUser(
                mTietFirstName.getText().toString().trim(),
                mTietSecondName.getText().toString().trim(),
                mRbMale.isChecked() ? "M" : "W"
        );
    }

    @Override
    public void showSuccessUpdated() {
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
}
