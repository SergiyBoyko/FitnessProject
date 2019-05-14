package com.example.a38096.fitnessproject;

import android.support.multidex.MultiDexApplication;

import com.example.a38096.fitnessproject.di.component.DaggerAppComponent;
import com.example.a38096.fitnessproject.di.component.DaggerPresentersComponent;
import com.example.a38096.fitnessproject.di.component.PresentersComponent;
import com.example.a38096.fitnessproject.di.module.ApiModule;
import com.example.a38096.fitnessproject.di.module.AppModule;
import com.example.a38096.fitnessproject.di.module.PresentersModule;

public class AppFitness extends MultiDexApplication {

    private PresentersComponent presentersComponent;

    public AppFitness() {

        presentersComponent = DaggerPresentersComponent.builder()
                .appComponent(DaggerAppComponent.builder()
                        .apiModule(new ApiModule())
                        .appModule(new AppModule(this))
                        .build())
                .presentersModule(new PresentersModule())
                .build();
    }

    public PresentersComponent presentersComponent() {
        return presentersComponent;
    }

}
