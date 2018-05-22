package com.example.a38096.fitnessproject.di.component;

import com.example.a38096.fitnessproject.di.module.PresentersModule;
import com.example.a38096.fitnessproject.di.scope.Scope;
import com.example.a38096.fitnessproject.di.scope.Scopes;
import com.example.a38096.fitnessproject.ui.activities.EditWorkoutActivity;
import com.example.a38096.fitnessproject.ui.activities.MainActivity;
import com.example.a38096.fitnessproject.ui.activities.RegisterActivity;
import com.example.a38096.fitnessproject.ui.activities.StartActivity;
import com.example.a38096.fitnessproject.ui.activities.UserCredentialsActivity;
import com.example.a38096.fitnessproject.ui.fragments.WorkoutsFragment;

import dagger.Component;

/**
 * Created by Serhii Boiko on 01.05.2018.
 */


@Scope(Scopes.VIEW)
@Component(
        modules = {PresentersModule.class},
        dependencies = {AppComponent.class}
)
public interface PresentersComponent {

    void inject(StartActivity startActivity);

    void inject(RegisterActivity registerActivity);

    void inject(WorkoutsFragment workoutsFragment);

    void inject(UserCredentialsActivity credentialsActivity);

    void inject(MainActivity mainActivity);

    void inject(EditWorkoutActivity editWorkoutActivity);

}
