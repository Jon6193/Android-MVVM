package com.jonathanpetitfrere.mvvm.di;

import android.content.Context;

import com.jonathanpetitfrere.mvvm.MvvmApplication;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * @author jpetit
 */

@Module(includes = {
        ViewModelModule.class,
        ApiModule.class,
        RepositoryModule.class
})
class ApplicationModule {

    @Singleton
    @Provides
    Context provideContext(MvvmApplication application) {
        return application;
    }
}