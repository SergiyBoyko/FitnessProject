package com.example.a38096.fitnessproject.ui.activities;

import android.net.Uri;
import android.os.Bundle;
import androidx.annotation.Nullable;
import android.widget.VideoView;

import com.example.a38096.fitnessproject.R;
import com.example.a38096.fitnessproject.ui.fragments.LoginFragment;
import com.example.a38096.fitnessproject.ui.fragments.RegisterFragment;
import com.example.a38096.fitnessproject.utils.FragmentUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Serhii Boiko on 01.05.2018.
 */
public class StartActivity extends BaseAppCompatActivity {

    @BindView(R.id.video_background)
    protected VideoView videoView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        ButterKnife.bind(this);

        initVideoBackground();
        initLoginScreen();
    }

    private void initLoginScreen() {
        FragmentUtil.setFragment(
                getSupportFragmentManager(),
                LoginFragment.newInstance(this::openRegisterScreen),
                R.id.container
        );
    }

    private void openRegisterScreen() {
        FragmentUtil.changeFragment(
                getSupportFragmentManager(),
                RegisterFragment.newInstance(),
                true,
                true,
                R.id.container
        );
    }

    private void initVideoBackground() {
        Uri uri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.video_background);
        videoView.setVideoURI(uri);
        videoView.setOnPreparedListener(mp -> mp.setLooping(true));
        videoView.start();
    }

    @Override
    protected void onResume() {
        super.onResume();
        initVideoBackground();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (videoView != null) {
            videoView.pause();
        }
    }
}
