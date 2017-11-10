package com.jonathanpetitfrere.mvvm;

import com.facebook.stetho.Stetho;
import com.facebook.stetho.timber.StethoTree;
import com.jonathanpetitfrere.mvvm.di.DaggerApplicationComponent;
import com.squareup.leakcanary.LeakCanary;

import dagger.android.AndroidInjector;
import dagger.android.support.DaggerApplication;
import timber.log.Timber;

/**
 * @author jpetit
 */

public class MvvmApplication extends DaggerApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }

        LeakCanary.install(this);
        Stetho.initializeWithDefaults(this);

        if(BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
            Timber.plant(new StethoTree());
        }
    }

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        return DaggerApplicationComponent.builder().create(this);
    }
}
