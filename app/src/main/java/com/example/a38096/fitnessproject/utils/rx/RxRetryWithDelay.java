package com.example.a38096.fitnessproject.utils.rx;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.functions.Func1;

/**
 * Created by Serhii Boiko on 01.05.2018.
 */
public class RxRetryWithDelay implements
        Func1<Observable<? extends Throwable>, Observable<?>> {

    private int maxRetries = 3;
    private long retryDelayMillis = 500;
    private int retryCount;

    public RxRetryWithDelay() {
    }

    public RxRetryWithDelay(int maxRetries) {
        this.maxRetries = maxRetries;
    }

    public RxRetryWithDelay(long retryDelayMillis) {
        this.retryDelayMillis = retryDelayMillis;
    }

    public RxRetryWithDelay(int maxRetries, long retryDelayMillis) {
        this.maxRetries = maxRetries;
        this.retryDelayMillis = retryDelayMillis;
    }

    @Override
    public Observable<?> call(Observable<? extends Throwable> attempts) {
        return attempts
                .flatMap((Func1<Throwable, Observable<?>>) throwable -> {
                    if (++retryCount < maxRetries) {
                        // When this Observable calls onNext, the original
                        // Observable will be retried (i.e. re-subscribed).
                        return Observable.timer(retryDelayMillis, TimeUnit.MILLISECONDS);
                    }

                    // Max retries hit. Just pass the error along.
                    return Observable.error(throwable);
                });
    }

}
