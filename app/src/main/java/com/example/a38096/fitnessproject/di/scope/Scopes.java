package com.example.a38096.fitnessproject.di.scope;

/**
 * Created by Serhii Boiko on 17.10.2018.
 */
public class Scopes {

    /**
     * Lifecycle scope annotation constants.
     */
    public static final String ACTIVITY = "activity";
    public static final String SERVICE = "service";
    public static final String FRAGMENT = "fragment";
    public static final String VIEW = "view";

    private Scopes() {
        throw new AssertionError("Unable to instantiate");
    }
}
