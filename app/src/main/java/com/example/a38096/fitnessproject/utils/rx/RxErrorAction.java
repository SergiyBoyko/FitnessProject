package com.example.a38096.fitnessproject.utils.rx;

import android.util.Log;

import com.example.a38096.fitnessproject.utils.AndroidUtils;
import com.example.a38096.fitnessproject.views.BaseView;

import io.reactivex.functions.Consumer;

/**
 * Created by Serhii Boiko on 01.05.2018.
 */
public class RxErrorAction implements Consumer<Throwable> {

    private static final String TAG = RxErrorAction.class.getSimpleName();
    private final BaseView baseView;
    private Runnable runnable;
    private boolean show = true;

    public RxErrorAction(BaseView baseView) {
        this.baseView = baseView;
    }

    public RxErrorAction(BaseView baseView, Runnable onErrorAction) {
        this.baseView = baseView;
        this.runnable = onErrorAction;
    }

    @Override
    public void accept(Throwable throwable) {
        throwable.printStackTrace();
        Log.e(TAG, throwable.getMessage());

        if (baseView != null && baseView.getContext() != null && show) {
            AndroidUtils.showShortToast(baseView.getContext(), throwable.getLocalizedMessage());
        }

        if (runnable != null) {
            runnable.run();
        }
    }

    public RxErrorAction showToast(boolean show) {
        this.show = show;
        return this;
    }
}
