package com.example.a38096.fitnessproject.ui.activities;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.graphics.drawable.VectorDrawableCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.Toolbar;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.a38096.fitnessproject.R;
import com.example.a38096.fitnessproject.ui.fragments.OtherFragment;
import com.example.a38096.fitnessproject.ui.fragments.WorkoutsFragment;
import com.example.a38096.fitnessproject.utils.AndroidUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.tlBottomNavigation)
    TabLayout mTlBottomNavigation;
    @BindView(R.id.toolbarMain)
    Toolbar mToolbar;
    @BindView(R.id.mainViewPager)
    ViewPager mViewPager;
    @BindView(R.id.navigationDrawer)
    DrawerLayout mDrawerLayout;
    @BindView(R.id.navigationView)
    NavigationView mNavigationView;
    @BindView(R.id.tvToolbarTitle)
    TextView mTvToolbarTitle;
    @BindView(R.id.ivMenu)
    ImageView mIvMenu;

    private TextView mTvTabWorkouts;
    private TextView mTvTabOthers;

    private MainViewPagerAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        initViewPager();
        initTabLayout();
        initNavigationDrawer();

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
                mNavigationView.bringToFront();
                mNavigationView.requestLayout();
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

    private void initViewPager() {
        mAdapter = new MainViewPagerAdapter(getSupportFragmentManager());
        mAdapter.addFragmentTitle(getString(R.string.tab_workout));
        mAdapter.addFragmentTitle(getString(R.string.tab_other));

        mViewPager.setAdapter(mAdapter);
//        mViewPager.addOnPageChangeListener(mPresenter);
//        mViewPager.setOffscreenPageLimit(3);
    }

    private void initTabLayout() {
        mTlBottomNavigation.setupWithViewPager(mViewPager);

        //Init tab icons
        mTvTabWorkouts = initTab(true, 0, R.drawable.ic_workout);
        mTvTabOthers = initTab(false, 1, R.drawable.ic_workout);

        changeTitle(mAdapter.getPageTitle(0).toString());
    }

    private TextView initTab(boolean isActive, int index, @DrawableRes int iconRes) {
        LayoutInflater inflater = LayoutInflater.from(this);

        TextView tabText = (TextView) inflater.inflate(R.layout.item_custom_tab, null, false);
        tabText.setText(mAdapter.getPageTitle(index));

        changeTabView(isActive, iconRes, tabText);

        TabLayout.Tab tab = mTlBottomNavigation.getTabAt(index);
        if (tab != null) {
            tab.setCustomView(tabText);
        }

        return tabText;
    }

    public void changeTitle(String titleText) {
        mTvToolbarTitle.setText(titleText);
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

    private class MainViewPagerAdapter extends FragmentPagerAdapter {
        public static final int FRAGMENT_COUNT = 2;
        public static final int WORKOUTS = 0;
        public static final int OTHER = 1;

        private final List<String> mFragmentTitleList = new ArrayList<>();
        private SparseArray<Fragment> registeredFragments = new SparseArray<>();

        public MainViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            Fragment fragment;
            if (position == 0) {
                fragment = new WorkoutsFragment();
            } else {
                fragment = new OtherFragment();
            }

            registeredFragments.put(position, fragment);
            return fragment;
        }

        @Override
        public int getCount() {
            return FRAGMENT_COUNT;
        }

        public void addFragmentTitle(String title) {
            mFragmentTitleList.add(title);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            Fragment fragment = (Fragment) super.instantiateItem(container, position);
            registeredFragments.put(position, fragment);
            return fragment;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            registeredFragments.remove(position);
            super.destroyItem(container, position, object);
        }

        public Fragment getRegisteredFragment(int position) {
            return registeredFragments.get(position);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }
}
