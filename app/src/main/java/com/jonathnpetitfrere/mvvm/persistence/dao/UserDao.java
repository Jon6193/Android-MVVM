package com.jonathnpetitfrere.mvvm.persistence.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.jonathnpetitfrere.mvvm.persistence.entity.User;

/**
 * @author jpetit
 */

@Dao
public interface UserDao {

    @Query("SELECT * FROM user WHERE email = :email")
    LiveData<User> loadUser(String email);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void saveUser(User user);
}
