package com.example.a38096.fitnessproject.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.example.a38096.fitnessproject.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Serhii Boiko on 01.05.2018.
 */
public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        ButterKnife.bind(this);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    @Override
    public void onBackPressed() {
        finish();
        overridePendingTransition(0,
                R.anim.activity_up_close);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case android.R.id.home:
                onBackPressed();
        }
        return (super.onOptionsItemSelected(menuItem));
    }

    @OnClick(R.id.btnSubmit)
    protected void onSubmitRegisterClick() {
        startActivity(new Intent(this, MainActivity.class));
    }


}
