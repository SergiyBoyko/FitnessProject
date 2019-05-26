package com.example.a38096.fitnessproject.model.prefs;

import android.content.SharedPreferences;

import com.example.a38096.fitnessproject.model.UserDataSource;

/**
 * Created by Serhii Boiko on 05.05.2018.
 */
public class UserPrefsDataSource extends BasePrefSource implements UserDataSource {

    public static final String AUTHORIZED = "authorized";
    public static final String AUTH_TOKEN = "auth_token";
    public static final String FIRST_NAME = "first_name";
    public static final String SECOND_NAME = "second_name";
    public static final String BASE64_DATA = "base64_data";
    public static final String GENDER = "gender";

    public UserPrefsDataSource(SharedPreferences sharedPreferences) {
        super(sharedPreferences);
    }

    @Override
    public String getToken() {
        return getStringPreference(AUTH_TOKEN);
    }

    @Override
    public void setToken(String token) {
        setStringPreference(AUTH_TOKEN, token);
    }

    @Override
    public boolean isAuthorized() {
        return getBooleanPreference(AUTHORIZED, false);
    }

    @Override
    public void setAuthorized() {
        setBooleanPreference(AUTHORIZED, true);
    }

    @Override
    public String getFirstName() {
        return getStringPreference(FIRST_NAME);
    }

    @Override
    public void setFirstName(String firstName) {
        setStringPreference(FIRST_NAME, firstName);
    }

    @Override
    public String getSecondName() {
        return getStringPreference(SECOND_NAME);
    }

    @Override
    public void setSecondName(String secondName) {
        setStringPreference(SECOND_NAME, secondName);
    }

    @Override
    public String getGender() {
        return getStringPreference(GENDER);
    }

    @Override
    public void setGender(String genderName) {
        setStringPreference(GENDER, genderName);
    }

    @Override
    public String getBase64Data() {
        return getStringPreference(BASE64_DATA);
    }

    @Override
    public void setBase64Data(String base64Data) {
        setStringPreference(BASE64_DATA, base64Data);
    }

    @Override
    public void clear() {
        mSharedPreferences.edit().clear().apply();
    }
}
