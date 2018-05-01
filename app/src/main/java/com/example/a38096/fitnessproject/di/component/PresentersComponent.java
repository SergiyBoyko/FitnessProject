package com.example.a38096.fitnessproject.di.component;

import com.example.a38096.fitnessproject.di.module.PresentersModule;
import com.example.a38096.fitnessproject.di.scope.Scope;
import com.example.a38096.fitnessproject.di.scope.Scopes;

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
}
