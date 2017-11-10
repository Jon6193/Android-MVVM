package com.jonathanpetitfrere.mvvm.di;

import android.arch.persistence.room.Room;
import android.content.Context;

import com.jonathanpetitfrere.mvvm.repository.persistence.MvvmDatabase;
import com.jonathanpetitfrere.mvvm.repository.persistence.dao.UserDao;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * @author jpetit
 */

@Module
class RepositoryModule {

    @Singleton
    @Provides
    MvvmDatabase provideMvvmDatabase(Context context) {
        return Room.databaseBuilder(context.getApplicationContext(), MvvmDatabase.class, MvvmDatabase.class.getSimpleName())
                .build();
    }

    @Singleton
    @Provides
    UserDao provideUserDao(MvvmDatabase mvvmDatabase) {
        return mvvmDatabase.userDao();
    }
}
