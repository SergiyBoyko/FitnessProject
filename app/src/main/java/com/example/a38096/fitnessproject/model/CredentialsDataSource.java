package com.example.a38096.fitnessproject.model;

import io.reactivex.Observable;
import okhttp3.ResponseBody;

/**
 * Created by Serhii Boiko on 14.05.2018.
 */
public interface CredentialsDataSource {
    Observable<ResponseBody> updateUser(String uuid,
                                        String firstName,
                                        String lastName,
                                        String gender,
                                        String loginPasswordBase64);
}
