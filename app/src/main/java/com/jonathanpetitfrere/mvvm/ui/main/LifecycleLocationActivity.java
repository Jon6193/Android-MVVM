package com.jonathanpetitfrere.mvvm.ui.main;

import android.arch.lifecycle.LifecycleActivity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import com.jonathanpetitfrere.mvvm.R;

/**
 * @author jpetit
 */

public class LifecycleLocationActivity extends LifecycleActivity {

    public final static String TAG = LifecycleLocationActivity.class.getSimpleName();

    private LifecycleLocationMonitor locationMonitor;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        locationMonitor = new LifecycleLocationMonitor(getLifecycle(), (latitude, longitude) -> {
            //show updated location
            Log.d(TAG, String.format("Location is (%f, %f)", latitude, longitude));
        });

        getLifecycle().addObserver(locationMonitor);
    }
}
