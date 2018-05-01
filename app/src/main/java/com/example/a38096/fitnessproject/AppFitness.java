package com.example.a38096.fitnessproject;

import android.support.multidex.MultiDexApplication;

import com.example.a38096.fitnessproject.di.component.AppComponent;
import com.example.a38096.fitnessproject.di.component.DaggerAppComponent;
import com.example.a38096.fitnessproject.di.module.ApiModule;
import com.example.a38096.fitnessproject.di.module.AppModule;

public class AppFitness extends MultiDexApplication {

    private AppComponent appComponent;

    public AppFitness() {
        super();

        appComponent = DaggerAppComponent.builder()
                .apiModule(new ApiModule())
                .appModule(new AppModule(this))
                .build();
    }

    public AppComponent appComponent() {
        return appComponent;
    }

}
