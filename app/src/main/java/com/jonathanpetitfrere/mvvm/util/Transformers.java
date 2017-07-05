package com.jonathanpetitfrere.mvvm.util;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.CompletableTransformer;
import io.reactivex.FlowableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * @author jpetit
 */

@Singleton
public class Transformers {

    @Inject
    Transformers(){}

    public <T> FlowableTransformer<T, T> applySchedulersToFlowable() {
        return flowable -> flowable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public CompletableTransformer applySchedulersToCompletable() {
        return completable -> completable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
