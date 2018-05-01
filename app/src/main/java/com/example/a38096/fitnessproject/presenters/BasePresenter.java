package com.example.a38096.fitnessproject.presenters;

import com.example.a38096.fitnessproject.views.BaseView;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by Serhii Boiko on 01.05.2018.
 */
public class BasePresenter<T extends BaseView> {

    private T view;

    private CompositeSubscription compositeSubscription = new CompositeSubscription();

    public T getView() {
        return view;
    }

    public void setView(T view) {
        this.view = view;
    }

    public void destroy() {
        compositeSubscription.clear();
    }

    protected Subscription addSubscription(Subscription subscription) {
        compositeSubscription.add(subscription);
        return subscription;
    }

}
