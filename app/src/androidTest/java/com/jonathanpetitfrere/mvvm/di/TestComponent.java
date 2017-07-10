package com.jonathanpetitfrere.mvvm.di;

import android.app.Application;

import javax.inject.Singleton;

import dagger.Component;

/**
 * @author jpetit
 */
@Singleton
@Component(modules = {
        ApplicationModule.class,
        ApiModule.class,
        RepositoryModule.class
})
public interface TestComponent extends AppGraph {

    final class Initializer {

        public static TestComponent init(Application application) {

            return DaggerTestComponent.builder()
                    .applicationModule(new TestApplicationModule(application))
                    .repositoryModule(new TestRepositoryModule())
                    .apiModule(new TestApiModule())
                    .build();
        }
    }

}
