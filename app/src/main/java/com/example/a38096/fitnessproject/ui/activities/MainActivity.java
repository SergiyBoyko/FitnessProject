package com.example.a38096.fitnessproject.ui.activities;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.graphics.drawable.VectorDrawableCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.a38096.fitnessproject.R;
import com.example.a38096.fitnessproject.listeners.PageSelectListener;
import com.example.a38096.fitnessproject.model.IUserDataSource;
import com.example.a38096.fitnessproject.ui.fragments.ClubsMapFragment;
import com.example.a38096.fitnessproject.ui.fragments.WorkoutsFragment;
import com.example.a38096.fitnessproject.utils.AndroidUtils;
import com.example.a38096.fitnessproject.widgets.adapters.ViewPagerAdapter;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseAppCompatActivity {

    @BindView(R.id.main_view_pager)
    protected ViewPager mainViewPager;
    @BindView(R.id.bottom_navigation_view)
    protected BottomNavigationView bottomNavigationView;

    @BindView(R.id.toolbarMain)
    protected Toolbar mToolbar;
    @BindView(R.id.navigationDrawer)
    protected DrawerLayout mDrawerLayout;
    @BindView(R.id.navigationView)
    protected NavigationView navigationView;
    @BindView(R.id.title)
    protected TextView title;
    @BindView(R.id.ivMenu)
    protected ImageView mIvMenu;
    @Inject
    protected IUserDataSource userDataSource;

    private ViewPagerAdapter viewPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        getPresentersComponent().inject(this);

        setupViewPager(mainViewPager);
        initNavigationMenuClickListener(bottomNavigationView);
        initNavigationDrawer();

    }

    private void setupViewPager(ViewPager viewPager) {
        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        // Hide keyboard when it not needed
        viewPager.addOnPageChangeListener((PageSelectListener) position -> {
            title.setText(viewPagerAdapter.getAppBarTitle(position));
            bottomNavigationView.setSelectedItemId(viewPagerAdapter.getMenuRes(position));
            AndroidUtils.hideKeyboard(MainActivity.this);
        });

        viewPagerAdapter.addFragment(
                WorkoutsFragment.newInstance(),
                getString(R.string.tab_workout),
                getString(R.string.tab_workout),
                R.id.action_workout
        );
        viewPagerAdapter.addFragment(
                ClubsMapFragment.newInstance(),
                getString(R.string.tab_clubs),
                getString(R.string.tab_clubs),
                R.id.action_map
        );

        viewPager.setOffscreenPageLimit(3);
        viewPager.setAdapter(viewPagerAdapter);
    }

    private void initNavigationMenuClickListener(BottomNavigationView navigationView) {
        navigationView.setOnNavigationItemSelectedListener(menuItem -> {
            mainViewPager.setCurrentItem(viewPagerAdapter.getPosition(menuItem.getItemId()));
            return true;
        });

    }

    /**
     * Instantiating navigation drawer
     */
    private void initNavigationDrawer() {
        configureToolbar();

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mDrawerLayout, mToolbar, R.string.accessibility_open_nav_drawer,
                R.string.accessibility_close_nav_drawer) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                drawerView.bringToFront();
                drawerView.requestLayout();
                navigationView.bringToFront();
                navigationView.requestLayout();
            }
        };
        toggle.setDrawerIndicatorEnabled(false);

        initDrawerHeader();

        mDrawerLayout.addDrawerListener(toggle);
        toggle.syncState();
    }

    private void setMenuLeftDrawable(TextView textView, @DrawableRes int drawableRes) {
        Drawable drawable = VectorDrawableCompat.create(getResources(), drawableRes, null);
        if (drawable != null) {
            drawable = DrawableCompat.wrap(drawable);
            textView.setCompoundDrawablesWithIntrinsicBounds(drawable, null, null, null);
        }
    }

    private void initDrawerHeader() {
    }

    /**
     * Configuration of toolbar
     */
    private void configureToolbar() {
        setSupportActionBar(mToolbar);

        mIvMenu.setOnClickListener(v -> mDrawerLayout.openDrawer(GravityCompat.START));
    }

    private void changeTabView(boolean isActive, @DrawableRes int iconRes, TextView tabText) {
        if (isActive) {
            tabText.setTextColor(ContextCompat.getColor(this, R.color.colorPrimaryDark));
        } else {
            tabText.setTextColor(ContextCompat.getColor(this, R.color.textSecondary));
        }
        Drawable icon = VectorDrawableCompat.create(getResources(), iconRes, null);
        int size = (int) AndroidUtils.convertDpToPixel(20, this);
        if (icon != null) {
            icon.setBounds(0, 0, size, size);
            tabText.setCompoundDrawables(null, icon, null, null);
        }
    }

    @Override
    public void onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @OnClick(R.id.tvMenuCredentials)
    public void OnCredentialsClick() {
        startActivity(new Intent(this, UserCredentialsActivity.class));
    }

    @OnClick(R.id.tvMenuLogout)
    public void OnLogoutClick() {
        userDataSource.clear();
        finish();
        startActivity(new Intent(this, StartActivity.class));
    }
}
