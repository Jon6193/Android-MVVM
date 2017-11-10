package com.jonathanpetitfrere.mvvm.ui.main;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.OnLifecycleEvent;
import android.support.annotation.NonNull;

/**
 * @author jpetit
 */

class LifecycleLocationMonitor implements LifecycleObserver {

    public final static String TAG = LifecycleLocationMonitor.class.getSimpleName();

    @NonNull
    private final Listener listener;

    private final Lifecycle lifecycle;

    private FakeLocationManager locationManager;

    interface Listener {
        void onLocationUpdate(double latitude, double longitude);
    }

    LifecycleLocationMonitor(Lifecycle lifecycle, @NonNull Listener listener) {
        this.lifecycle = lifecycle;
        this.listener = listener;
        lifecycle.addObserver(this);
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    void onStart() {
        locationManager = new FakeLocationManager();

        locationManager.setLocationUpdateListener((latitude, longitude) -> {
            if(lifecycle.getCurrentState().isAtLeast(Lifecycle.State.RESUMED)) {
                listener.onLocationUpdate(latitude, longitude);
            }
        });
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    void onResume() {
        locationManager.startRequestingLocationUpdates();
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    void onPause() {
        locationManager.stopRequestingLocationUpdates();
    }

}
