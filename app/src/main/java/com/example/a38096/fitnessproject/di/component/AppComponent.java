package com.example.a38096.fitnessproject.di.component;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.a38096.fitnessproject.di.module.ApiModule;
import com.example.a38096.fitnessproject.di.module.AppModule;

import java.util.concurrent.Executor;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Serhii Boiko on 01.05.2018.
 */

@Singleton
@Component(modules = {
        AppModule.class,
        ApiModule.class
})
public interface AppComponent extends ApiComponent {

    Context context();

    SharedPreferences preferences();

    Executor executor();

}
