package com.jonathanpetitfrere.mvvm.ui.main;

import android.arch.lifecycle.LifecycleActivity;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.util.Log;

import com.jonathanpetitfrere.mvvm.R;

import butterknife.ButterKnife;
import butterknife.OnTextChanged;

import static butterknife.OnTextChanged.Callback.AFTER_TEXT_CHANGED;

/**
 * @author jpetit
 */

public class FirstNameActivity extends LifecycleActivity {

    private final static String TAG = FirstNameActivity.class.getSimpleName();

    private FirstNameViewModel viewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        viewModel = ViewModelProviders.of(this).get(FirstNameViewModel.class);

        viewModel.firstLetter().observe(this, firstLetter -> {
            //Update ui with first letter
            Log.d(TAG, String.format("First Letter is %s", firstLetter));
        });

        viewModel.firstName().observe(this, firstName -> {
            //Update ui with first name
            Log.d(TAG, String.format("First Name is %s", firstName));
        });
    }


    @OnTextChanged(value = R.id.main_first_name_input, callback = AFTER_TEXT_CHANGED)
    void onFirstNameTextChange(Editable input) {
        viewModel.setFirstName(input.toString());
    }
}
