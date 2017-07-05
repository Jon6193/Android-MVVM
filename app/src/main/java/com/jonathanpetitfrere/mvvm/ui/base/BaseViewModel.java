package com.jonathanpetitfrere.mvvm.ui.base;

import android.arch.lifecycle.ViewModel;

import io.reactivex.disposables.CompositeDisposable;

/**
 * @author jpetit
 */

public abstract class BaseViewModel extends ViewModel {

    private CompositeDisposable compositeDisposable;

    protected CompositeDisposable disposables() {
        if(compositeDisposable == null || compositeDisposable.isDisposed()) {
            compositeDisposable = new CompositeDisposable();
        }

        return compositeDisposable;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        compositeDisposable.dispose();
    }
}
