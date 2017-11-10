package com.jonathanpetitfrere.mvvm.util;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * @author jpetit
 */

@Singleton
public class RxSchedulers {

    @Inject
    RxSchedulers(){}

    public Scheduler ui() {
        return AndroidSchedulers.mainThread();
    }

    public Scheduler io() {
        return Schedulers.io();
    }

    public Scheduler computation() {
        return Schedulers.computation();
    }
}
