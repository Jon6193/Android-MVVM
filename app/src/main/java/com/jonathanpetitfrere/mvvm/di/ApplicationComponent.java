package com.jonathanpetitfrere.mvvm.di;

import com.jonathanpetitfrere.mvvm.MvvmApplication;

import javax.inject.Singleton;

import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

/**
 * @author jpetit
 */
@Singleton
@Component(modules = {
        AndroidSupportInjectionModule.class,
        InjectorsModule.class,
        ApplicationModule.class
})
public interface ApplicationComponent extends AndroidInjector<MvvmApplication> {

    @Component.Builder
    abstract class Builder extends AndroidInjector.Builder<MvvmApplication>{}
}
