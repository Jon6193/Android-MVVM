package com.jonathanpetitfrere.mvvm.repository.persistence.dao;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.jonathanpetitfrere.mvvm.repository.persistence.MvvmDatabase;
import com.jonathanpetitfrere.mvvm.repository.persistence.entity.User;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jpetit
 */

@RunWith(AndroidJUnit4.class)
public class UserDaoTest {

    private MvvmDatabase mvvmDatabase;
    private UserDao userDao;

    @Before
    public void setup() {
        Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();
        mvvmDatabase = Room.inMemoryDatabaseBuilder(context, MvvmDatabase.class).build();
        userDao = mvvmDatabase.userDao();
    }

    @After
    public void tearDown() {
        mvvmDatabase.close();
    }

    @Test
    public void saveAndLoadUserSuccess() throws InterruptedException {
        User user = new User("john.doe@gmail.com", "John", "Doe");
        userDao.saveUser(user);

        userDao.loadUser(user.getEmail())
                .take(1)
                .test()
                .await()
                .assertResult(user);
    }

    @Test
    public void saveAndLoadMultipleUsersSuccess() throws InterruptedException {
        User userOne = new User("john.doe@gmail.com", "John", "Doe");
        User userTwo = new User("jane.doe@gmail.com", "Jane", "Doe");

        List<User> users = new ArrayList<>();
        users.add(userOne);
        users.add(userTwo);

        for(User user : users) {
            userDao.saveUser(user);
        }

        //noinspection unchecked
        userDao.loadUsers()
                .take(1)
                .test()
                .await()
                .assertResult(users);
    }


    @Test
    public void saveAndLoadUserUpdateFirstNameSuccess() throws InterruptedException {
        User user = new User("john.doe@gmail.com", "John", "Doe");

        userDao.saveUser(user);
        user = new User(user.getEmail(), "Johnny", user.getLastName());
        userDao.saveUser(user);

        userDao.loadUser(user.getEmail())
                .take(1)
                .test()
                .await()
                .assertResult(user);
    }
}
