package com.example.a38096.fitnessproject.widgets.adapters;

import androidx.annotation.IdRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerAdapter extends FragmentPagerAdapter {
    private final List<Fragment> fragmentList = new ArrayList<>();
    private final List<String> fragmentTitleList = new ArrayList<>();
    private final List<String> fragmentTitleBarList = new ArrayList<>();
    private final List<Integer> menuIdList = new ArrayList<>();

    public ViewPagerAdapter(FragmentManager manager) {
        super(manager);
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    public void addFragment(Fragment fragment, String title) {
        fragmentList.add(fragment);
        fragmentTitleList.add(title);
        fragmentTitleBarList.add("");
    }

    public int getPosition(@IdRes int menuId) {
        return menuIdList.indexOf(menuId);
    }

    public int getMenuRes(int position) {
        return menuIdList.get(position);
    }

    public void addFragment(Fragment fragment, String title, String barTitle, @IdRes int menuId) {
        fragmentList.add(fragment);
        fragmentTitleList.add(title);
        fragmentTitleBarList.add(barTitle);
        menuIdList.add(menuId);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return fragmentTitleList.get(position);
    }

    public String getAppBarTitle(int position) {
        return fragmentTitleBarList.get(position);
    }
}