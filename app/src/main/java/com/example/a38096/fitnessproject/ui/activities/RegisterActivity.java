package com.example.a38096.fitnessproject.ui.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.a38096.fitnessproject.R;

/**
 * Created by Serhii Boiko on 01.05.2018.
 */
public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
    }

    @Override
    public void onBackPressed() {
        finish();
        overridePendingTransition(0,
                R.anim.activity_up_close);
    }
}
