package com.jonathanpetitfrere.mvvm.ui.base;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import dagger.android.AndroidInjection;

/**
 * @author jpetit
 */

public abstract class BaseActivity extends AppCompatActivity {

    private Unbinder unbinder = Unbinder.EMPTY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(getLayoutRes());
        unbinder = ButterKnife.bind(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        unbinder.unbind();
    }

    @LayoutRes
    public abstract int getLayoutRes();
}
