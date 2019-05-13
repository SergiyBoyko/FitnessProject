package com.example.a38096.fitnessproject.ui.activities;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;

import com.example.a38096.fitnessproject.AppFitness;
import com.example.a38096.fitnessproject.di.component.PresentersComponent;
import com.example.a38096.fitnessproject.presenters.BasePresenter;
import com.example.a38096.fitnessproject.views.BaseView;

import butterknife.ButterKnife;

/**
 * Created by Serhii Boiko on 13.05.2019.
 */
public abstract class BaseAppCompatActivity<T extends BaseView> extends AppCompatActivity
        implements BaseView {

    private BasePresenter<T> presenter;
    private T view;

    public void registerPresenterLifecycle(BasePresenter<T> presenter, T view) {
        this.presenter = presenter;
        this.view = view;
        presenter.bindView(view);
        ButterKnife.bind(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if ((presenter != null) && !presenter.isBind()) {
            presenter.bindView(view);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if ((presenter != null) && presenter.isBind()) {
            presenter.unbindView();
        }
    }

    @Override
    public Context getContext() {
        return this;
    }

    public PresentersComponent getPresentersComponent() {
        return getApp().presentersComponent();
    }

    private AppFitness getApp() {
        return (AppFitness) getApplication();
    }
}
