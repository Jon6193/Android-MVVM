package com.jonathnpetitfrere.mvvm.ui.base;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;

import com.jonathnpetitfrere.mvvm.MvvmApplication;
import com.jonathnpetitfrere.mvvm.persistence.MvvmDatabase;

/**
 * @author jpetit
 */

public abstract class BaseAndroidViewModel extends AndroidViewModel {

    public BaseAndroidViewModel(Application application) {
        super(application);
    }

    protected MvvmApplication getMvvmApplication() {
        return (MvvmApplication) getApplication();
    }

    protected MvvmDatabase getMvvmDatabase() {
        return getMvvmApplication().getMvvmDatabase();
    }
}
