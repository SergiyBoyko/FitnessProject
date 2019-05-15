package com.example.a38096.fitnessproject.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.example.a38096.fitnessproject.utils.PermissionUtils;

/**
 * Created by Serhii Boiko on 13.05.2019.
 */
public class SplashActivity extends BaseAppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        checkLocationPermissions();
    }

    private void checkLocationPermissions() {
        if (PermissionUtils.hasSelfPermissions(getContext(), PermissionUtils.LOCATION_PERMISSIONS)) {
            startActivity(new Intent(this, StartActivity.class));
            finish();
        } else {
            PermissionUtils.requestAllPermissions(
                    this, PermissionUtils.LOCATION_PERMISSIONS, PermissionUtils.LOCATION_REQUEST_CODE
            );
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        checkLocationPermissions();
    }
}
