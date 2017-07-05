package com.jonathanpetitfrere.mvvm.repository.persistence.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.jonathanpetitfrere.mvvm.repository.persistence.entity.User;

import java.util.List;

import io.reactivex.Flowable;

/**
 * @author jpetit
 */

@Dao
public interface UserDao {

    @Query("SELECT * FROM user WHERE email = :email")
    Flowable<User> loadUser(String email);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void saveUser(User user);

    @Query("SELECT * FROM user")
    Flowable<List<User>> getUsers();
}
