package com.jonathnpetitfrere.mvvm.ui.base;

import android.arch.lifecycle.LifecycleActivity;
import android.os.Bundle;
import android.support.annotation.LayoutRes;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @author jpetit
 */

public abstract class BaseActivity extends LifecycleActivity {

    private Unbinder unbinder = Unbinder.EMPTY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResId());
        unbinder = ButterKnife.bind(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    @LayoutRes
    public abstract int getLayoutResId();
}
