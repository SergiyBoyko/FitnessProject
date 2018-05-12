package com.example.a38096.fitnessproject.model;

import okhttp3.ResponseBody;
import rx.Observable;

/**
 * Created by Serhii Boiko on 06.05.2018.
 */
public interface IRegisterDataSource {

    Observable<ResponseBody> registerUser(int firstName,
                                          int lastName,
                                          int gender,
                                          int password,
                                          int email);
}
