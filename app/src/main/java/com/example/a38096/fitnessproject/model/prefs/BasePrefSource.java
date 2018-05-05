package com.example.a38096.fitnessproject.model.prefs;

import android.content.SharedPreferences;

/**
 * Created by Serhii Boiko on 05.05.2018.
 */
public class BasePrefSource {

    protected SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor editor;

    BasePrefSource(SharedPreferences sharedPreferences) {
        mSharedPreferences = sharedPreferences;
        editor = mSharedPreferences.edit();
        editor.apply();
    }


    synchronized String getStringPreference(String name) {
        return mSharedPreferences.getString(name, null);
    }

    synchronized void setStringPreference(String name, String value) {

        editor.putString(name, value);
        editor.apply();
    }

    synchronized Boolean getBooleanPreference(String name) {
        return getBooleanPreference(name, true);
    }

    synchronized Boolean getBooleanPreference(String name, boolean def) {

        return mSharedPreferences.getBoolean(name, def);
    }

    synchronized void setBooleanPreference(String name, Boolean value) {
        //SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putBoolean(name, value);
        editor.apply();
    }

}
