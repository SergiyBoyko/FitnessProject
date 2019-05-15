package com.example.a38096.fitnessproject.listeners;

import android.support.v4.view.ViewPager;

public interface PageSelectListener extends ViewPager.OnPageChangeListener {
    @Override
    default void onPageScrolled(int i, float v, int i1) {
    }

    @Override
    default void onPageScrollStateChanged(int i) {
    }
}