package com.jonathanpetitfrere.mvvm.di;

import javax.inject.Singleton;

import dagger.Component;

/**
 * @author jpetit
 */
@Singleton
@Component(modules = {
        ApplicationModule.class,
        ApiModule.class
})
public interface ApplicationComponent extends AppGraph {

}
