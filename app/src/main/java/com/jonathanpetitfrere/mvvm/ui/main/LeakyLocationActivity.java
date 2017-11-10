package com.jonathanpetitfrere.mvvm.ui.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.jonathanpetitfrere.mvvm.R;

/**
 * @author jpetit
 */

public class LeakyLocationActivity extends AppCompatActivity {

    public final static String TAG = LeakyLocationActivity.class.getSimpleName();

    private LeakyLocationMonitor locationMonitor;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        locationMonitor = new LeakyLocationMonitor((latitude, longitude) -> {
            //show updated location
            Log.d(TAG, String.format("Location is (%f, %f)", latitude, longitude));
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        locationMonitor.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        locationMonitor.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        //locationMonitor.onPause();
    }
}
