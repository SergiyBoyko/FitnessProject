package com.example.a38096.fitnessproject.model;

/**
 * Created by Serhii Boiko on 05.05.2018.
 */
public interface IUserDataSource {

    String getToken();

    void setToken(String token);

    boolean isAuthorized();

    void setAuthorized();

    void clear();
}
