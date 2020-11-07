package com.example.a38096.fitnessproject.presenters;

import androidx.annotation.NonNull;

import com.example.a38096.fitnessproject.utils.rx.AsyncTransformer;
import com.example.a38096.fitnessproject.utils.rx.RxErrorAction;
import com.example.a38096.fitnessproject.views.BaseView;

import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;


/**
 * Created by Serhii Boiko on 01.05.2018.
 */
public class BasePresenter<T extends BaseView> {

    protected T view;
    private CompositeDisposable compositeDisposable;

    public boolean isBind() {
        return view != null;
    }

    protected Disposable addDisposable(Disposable disposable) {
        compositeDisposable.add(disposable);
        return disposable;
    }

    <R> void makeRequest(Observable<R> responseObservable, Consumer<R> onOk) {
        addDisposable(
                responseObservable
                        .compose(new AsyncTransformer<>())
                        .subscribe(onOk, new RxErrorAction(view))
        );
    }

    public void bindView(@NonNull T view) {
        compositeDisposable = new CompositeDisposable();
        this.view = view;
    }

    public void unbindView() {
        if (compositeDisposable != null && !compositeDisposable.isDisposed()) {
            compositeDisposable.dispose();
        }
        this.view = null;
    }

    /*
     * @Deprecated because view now has protected access
     * */
    @Deprecated
    public T getView() {
        return view;
    }

    public void setView(T view) {
        this.view = view;
    }

}
