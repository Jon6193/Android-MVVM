package com.jonathanpetitfrere.mvvm.persistence.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.jonathanpetitfrere.mvvm.persistence.entity.User;

import java.util.List;

/**
 * @author jpetit
 */

@Dao
public interface UserDao {

    @Query("SELECT * FROM user WHERE email = :email")
    LiveData<User> loadUser(String email);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void saveUser(User user);

    @Query("SELECT * FROM user")
    LiveData<List<User>> getUsers();
}
