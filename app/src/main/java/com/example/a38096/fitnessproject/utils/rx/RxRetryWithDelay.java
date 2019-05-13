package com.example.a38096.fitnessproject.utils.rx;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.functions.Function;


/**
 * Created by Serhii Boiko on 01.05.2018.
 */
public class RxRetryWithDelay implements
        Function<Observable<? extends Throwable>, Observable<?>> {

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
    public Observable<?> apply(Observable<? extends Throwable> observable) throws Exception {
        return observable
                .flatMap((Function<Throwable, Observable<?>>) throwable -> {
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
