package com.example.a38096.fitnessproject.model;

/**
 * Created by Serhii Boiko on 05.05.2018.
 */
public interface IUserDataSource {

    String getToken();

    void setToken(String token);

    boolean isAuthorized();

    void setAuthorized();

    String getFirstName();

    void setFirstName(String firstName);

    String getSecondName();

    void setSecondName(String secondName);

    String getGender();

    void setGender(String genderName);

    String getBase64Data();

    void setBase64Data(String base64Data);

    void clear();
}
