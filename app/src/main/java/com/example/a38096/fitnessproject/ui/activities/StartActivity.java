package com.example.a38096.fitnessproject.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.a38096.fitnessproject.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Serhii Boiko on 01.05.2018.
 */
public class StartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        ButterKnife.bind(this);
    }

    @OnClick(R.id.tvRegisterButton)
    protected void onRegisterClick() {
        startActivity(new Intent(this, RegisterActivity.class));
        overridePendingTransition(R.anim.activity_down_open,
                R.anim.activity_down_close);
    }

}
