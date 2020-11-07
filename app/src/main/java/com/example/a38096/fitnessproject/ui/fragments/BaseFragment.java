package com.example.a38096.fitnessproject.ui.fragments;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import android.view.View;

import com.example.a38096.fitnessproject.AppFitness;
import com.example.a38096.fitnessproject.di.component.PresentersComponent;
import com.example.a38096.fitnessproject.presenters.BasePresenter;
import com.example.a38096.fitnessproject.views.BaseView;

import butterknife.ButterKnife;

/**
 * Created by Serhii Boiko on 12.11.2018.
 */
public abstract class BaseFragment<T extends BaseView> extends Fragment implements BaseView {

    private BasePresenter<T> presenter;
    private T view;

    public PresentersComponent getPresentersComponent() {
        return getApp().presentersComponent();
    }

    private AppFitness getApp() {
        return (AppFitness) getActivity().getApplication();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);

    }

    public void registerPresenterLifecycle(BasePresenter<T> presenter, T view) {
        this.presenter = presenter;
        this.view = view;
        presenter.bindView(view);
    }

    @Override
    public void onResume() {
        super.onResume();
        if ((presenter != null) && !presenter.isBind()) {
            presenter.bindView(view);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if ((presenter != null) && presenter.isBind()) {
            presenter.unbindView();
        }
    }

    public FragmentManager getSupportFragmentManager() {
        return getActivity().getSupportFragmentManager();
    }

}
