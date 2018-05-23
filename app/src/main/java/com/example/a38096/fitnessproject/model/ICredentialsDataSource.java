package com.example.a38096.fitnessproject.model;

import okhttp3.ResponseBody;
import rx.Observable;

/**
 * Created by Serhii Boiko on 14.05.2018.
 */
public interface ICredentialsDataSource {
    Observable<ResponseBody> updateUser(String uuid,
                                        String firstName,
                                        String lastName,
                                        String gender,
                                        String loginPasswordBase64);
}
