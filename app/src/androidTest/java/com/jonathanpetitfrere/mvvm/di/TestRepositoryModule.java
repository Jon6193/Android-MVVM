package com.jonathanpetitfrere.mvvm.di;

import android.arch.persistence.room.Room;
import android.content.Context;

import com.jonathanpetitfrere.mvvm.repository.persistence.MvvmDatabase;

/**
 * @author jpetit
 */

public class TestRepositoryModule extends RepositoryModule {

    @Override
    MvvmDatabase provideMvvmDatabase(Context context) {
        return Room.inMemoryDatabaseBuilder(context.getApplicationContext(), MvvmDatabase.class)
                .build();
    }
}
