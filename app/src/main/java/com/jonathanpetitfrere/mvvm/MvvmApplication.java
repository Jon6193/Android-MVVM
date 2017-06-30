package com.jonathanpetitfrere.mvvm;

import android.app.Application;

import com.facebook.stetho.Stetho;
import com.facebook.stetho.timber.StethoTree;
import com.jonathanpetitfrere.mvvm.di.AppGraph;
import com.jonathanpetitfrere.mvvm.di.ApplicationModule;
import com.jonathanpetitfrere.mvvm.di.DaggerApplicationComponent;
import com.squareup.leakcanary.LeakCanary;

import timber.log.Timber;

/**
 * @author jpetit
 */

public class MvvmApplication extends Application {

    protected AppGraph component;

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

        component = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();

        component.inject(this);
    }

    public AppGraph getComponent() {
        return component;
    }
}
