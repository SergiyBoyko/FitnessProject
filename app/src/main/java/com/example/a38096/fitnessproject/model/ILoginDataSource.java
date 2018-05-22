package com.example.a38096.fitnessproject.model;

import com.example.a38096.fitnessproject.model.entities.User;

import rx.Observable;

/**
 * Created by Serhii Boiko on 06.05.2018.
 */
public interface ILoginDataSource {
    Observable<User> login(String loginPasswordBase64);
}
