package com.example.a38096.fitnessproject.model;

import com.example.a38096.fitnessproject.model.entities.User;

import io.reactivex.Observable;

/**
 * Created by Serhii Boiko on 06.05.2018.
 */
public interface IRegisterDataSource {
    Observable<User> registerUser(String firstName,
                                  String lastName,
                                  String email,
                                  String password,
                                  String gender);
}
