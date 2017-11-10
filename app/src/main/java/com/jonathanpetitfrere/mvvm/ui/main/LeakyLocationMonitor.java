package com.jonathanpetitfrere.mvvm.ui.main;

import android.support.annotation.NonNull;

/**
 * @author jpetit
 */

class LeakyLocationMonitor {

    public final static String TAG = LeakyLocationMonitor.class.getSimpleName();

    @NonNull
    private final Listener listener;

    private FakeLocationManager locationManager;

    interface Listener {
        void onLocationUpdate(double latitude, double longitude);
    }

    LeakyLocationMonitor(@NonNull Listener listener) {
        this.listener = listener;
    }

    void onStart() {
        locationManager = new FakeLocationManager();
        locationManager.setLocationUpdateListener(listener::onLocationUpdate);
    }

    void onResume() {
        locationManager.startRequestingLocationUpdates();
    }

    void onPause() {
        locationManager.stopRequestingLocationUpdates();
    }
}


