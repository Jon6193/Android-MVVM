package com.jonathnpetitfrere.mvvm.persistence;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.jonathnpetitfrere.mvvm.persistence.dao.UserDao;
import com.jonathnpetitfrere.mvvm.persistence.entity.User;

/**
 * @author jpetit
 */

@Database(entities = {User.class}, version = 1)
public abstract class MvvmDatabase extends RoomDatabase {

    public abstract UserDao userDao();

}
