package com.jonathanpetitfrere.mvvm.di;

import com.jonathanpetitfrere.mvvm.ui.main.MainActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * @author jpetit
 */
@Module
abstract class InjectorsModule {

    @ContributesAndroidInjector()
    abstract MainActivity contributeMainActivity();
}
