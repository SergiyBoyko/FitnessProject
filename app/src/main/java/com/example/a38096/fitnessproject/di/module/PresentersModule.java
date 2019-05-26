package com.example.a38096.fitnessproject.di.module;

import com.example.a38096.fitnessproject.di.scope.Scope;
import com.example.a38096.fitnessproject.di.scope.Scopes;
import com.example.a38096.fitnessproject.model.ClubsDataSource;
import com.example.a38096.fitnessproject.model.CredentialsDataSource;
import com.example.a38096.fitnessproject.model.LoginDataSource;
import com.example.a38096.fitnessproject.model.RegisterDataSource;
import com.example.a38096.fitnessproject.model.UserDataSource;
import com.example.a38096.fitnessproject.model.WorkoutDataSource;
import com.example.a38096.fitnessproject.presenters.ClubsPresenter;
import com.example.a38096.fitnessproject.presenters.CredentialsPresenter;
import com.example.a38096.fitnessproject.presenters.LoginPresenter;
import com.example.a38096.fitnessproject.presenters.RegisterPresenter;
import com.example.a38096.fitnessproject.presenters.WorkoutPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Serhii Boiko on 01.05.2018.
 */

@Module
public class PresentersModule {

    @Provides
    @Scope(Scopes.VIEW)
    public LoginPresenter provideLoginPresenter(LoginDataSource loginDataSource,
                                                UserDataSource userDataSource) {
        return new LoginPresenter(loginDataSource, userDataSource);
    }

    @Provides
    @Scope(Scopes.VIEW)
    public RegisterPresenter provideRegisterPresenter(RegisterDataSource registerDataSource,
                                                      UserDataSource userDataSource) {
        return new RegisterPresenter(registerDataSource, userDataSource);
    }


    @Provides
    @Scope(Scopes.VIEW)
    public WorkoutPresenter provideWorkoutPresenter(WorkoutDataSource workoutDataSource,
                                                    UserDataSource userDataSource) {
        return new WorkoutPresenter(workoutDataSource, userDataSource);
    }


    @Provides
    @Scope(Scopes.VIEW)
    public CredentialsPresenter provideCredentialsPresenter(CredentialsDataSource credentialsDataSource,
                                                            UserDataSource userDataSource) {
        return new CredentialsPresenter(credentialsDataSource, userDataSource);
    }


    @Provides
    @Scope(Scopes.VIEW)
    public ClubsPresenter provideClubsPresenter(ClubsDataSource clubsDataSource, UserDataSource userDataSource) {
        return new ClubsPresenter(clubsDataSource, userDataSource);
    }

}
