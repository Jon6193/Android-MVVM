package com.jonathanpetitfrere.mvvm.repository.persistence;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.jonathanpetitfrere.mvvm.repository.persistence.dao.UserDao;
import com.jonathanpetitfrere.mvvm.repository.persistence.entity.User;

/**
 * @author jpetit
 */

@Database(entities = {User.class}, version = 1)
public abstract class MvvmDatabase extends RoomDatabase {

    public abstract UserDao userDao();

}
