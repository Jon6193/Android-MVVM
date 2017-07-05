package com.jonathanpetitfrere.mvvm.di;

import android.app.Application;
import android.arch.persistence.room.Room;
import android.content.Context;

import com.jonathanpetitfrere.mvvm.MvvmApplication;
import com.jonathanpetitfrere.mvvm.repository.persistence.MvvmDatabase;
import com.jonathanpetitfrere.mvvm.repository.persistence.dao.UserDao;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * @author jpetit
 */

@Module
public class ApplicationModule {

    private final MvvmApplication application;

    public ApplicationModule(MvvmApplication application) {
        this.application = application;
    }

    @Singleton
    @Provides
    Application provideApplication() {
        return this.application;
    }

    @Singleton
    @Provides
    Context provideContext() {
        return this.application;
    }
}