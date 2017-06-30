package com.jonathnpetitfrere.mvvm.ui.main;

import android.os.Bundle;

import com.jonathnpetitfrere.mvvm.R;
import com.jonathnpetitfrere.mvvm.ui.base.BaseActivity;

/**
 * @author jpetit
 */

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public int getLayoutResId() {
        return R.layout.activity_main;
    }
}
