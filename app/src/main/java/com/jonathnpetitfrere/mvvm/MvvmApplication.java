package com.jonathnpetitfrere.mvvm;

import android.app.Application;
import android.arch.persistence.room.Room;

import com.jonathnpetitfrere.mvvm.persistence.MvvmDatabase;

/**
 * @author jpetit
 */

public class MvvmApplication extends Application {

    private MvvmDatabase mvvmDatabase;

    public MvvmDatabase getMvvmDatabase() {
        if(mvvmDatabase == null) {
            mvvmDatabase = Room.databaseBuilder(getApplicationContext(), MvvmDatabase.class, MvvmDatabase.class.getSimpleName())
                    .allowMainThreadQueries()
                    .build();
        }

        return mvvmDatabase;
    }
}
