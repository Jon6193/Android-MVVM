package com.jonathanpetitfrere.mvvm.ui.main;

import android.support.annotation.NonNull;

/**
 * @author jpetit
 */

class FakeLocationManager {

    private LocationUpdateListener listener;

    interface LocationUpdateListener {
        void onLocationUpdate(double latitude, double longitude);
    }

    void setLocationUpdateListener(@NonNull LocationUpdateListener listener) {
        this.listener = listener;
    }

    void startRequestingLocationUpdates() {
        //send location updates to listener
        listener.onLocationUpdate(123, 123);
    }

    void stopRequestingLocationUpdates() {
        //stop sending location updates to listener
    }

}


