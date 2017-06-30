package com.jonathanpetitfrere.mvvm.ui.main;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.widget.Toast;

import com.jonathanpetitfrere.mvvm.R;
import com.jonathanpetitfrere.mvvm.ui.base.BaseActivity;

/**
 * @author jpetit
 */

public class MainActivity extends BaseActivity {

    private MainViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        viewModel.getUser("test@gmail.com")
                .observe(this, user -> {
                    if(user != null) {
                        Toast.makeText(getApplicationContext(), "Found User " + user.getEmail(), Toast.LENGTH_SHORT).show();

                    } else {
                        Toast.makeText(getApplicationContext(), "Did not find User", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    @Override
    public int getLayoutResId() {
        return R.layout.activity_main;
    }
}
