package com.example.a38096.fitnessproject.di.module;

import com.example.a38096.fitnessproject.di.scope.Scope;
import com.example.a38096.fitnessproject.di.scope.Scopes;
import com.example.a38096.fitnessproject.model.ICredentialsDataSource;
import com.example.a38096.fitnessproject.model.ILoginDataSource;
import com.example.a38096.fitnessproject.model.IRegisterDataSource;
import com.example.a38096.fitnessproject.model.IUserDataSource;
import com.example.a38096.fitnessproject.model.IWorkoutDataSource;
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
    public LoginPresenter provideLoginPresenter(ILoginDataSource loginDataSource,
                                                IUserDataSource userDataSource) {
        return new LoginPresenter(loginDataSource, userDataSource);
    }

    @Provides
    @Scope(Scopes.VIEW)
    public RegisterPresenter provideRegisterPresenter(IRegisterDataSource registerDataSource,
                                                      IUserDataSource userDataSource) {
        return new RegisterPresenter(registerDataSource, userDataSource);
    }


    @Provides
    @Scope(Scopes.VIEW)
    public WorkoutPresenter provideWorkoutPresenter(IWorkoutDataSource workoutDataSource,
                                                    IUserDataSource userDataSource) {
        return new WorkoutPresenter(workoutDataSource, userDataSource);
    }


    @Provides
    @Scope(Scopes.VIEW)
    public CredentialsPresenter provideCredentialsPresenter(ICredentialsDataSource credentialsDataSource,
                                                            IUserDataSource userDataSource) {
        return new CredentialsPresenter(credentialsDataSource, userDataSource);
    }

}
