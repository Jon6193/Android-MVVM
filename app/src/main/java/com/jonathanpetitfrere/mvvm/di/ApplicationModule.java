package com.jonathanpetitfrere.mvvm.di;

import android.content.Context;

import com.jonathanpetitfrere.mvvm.MvvmApplication;

import dagger.Binds;
import dagger.Module;

/**
 * @author jpetit
 */

@Module(includes = {
        ViewModelModule.class,
        ApiModule.class,
        RepositoryModule.class
})
abstract class ApplicationModule {

    @Binds
    abstract Context provideContext(MvvmApplication application);
}