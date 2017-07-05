package com.jonathanpetitfrere.mvvm.ui.base;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;

import com.jonathanpetitfrere.mvvm.MvvmApplication;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.internal.disposables.DisposableContainer;

/**
 * @author jpetit
 */

public abstract class BaseAndroidViewModel extends AndroidViewModel {

    private CompositeDisposable compositeDisposable;

    public BaseAndroidViewModel(Application application) {
        super(application);
    }

    protected MvvmApplication getMvvmApplication() {
        return (MvvmApplication) getApplication();
    }

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
