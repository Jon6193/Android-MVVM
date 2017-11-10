package com.jonathanpetitfrere.mvvm.repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.jonathanpetitfrere.mvvm.repository.api.UserApi;
import com.jonathanpetitfrere.mvvm.repository.mapper.UserMapper;
import com.jonathanpetitfrere.mvvm.repository.persistence.dao.UserDao;
import com.jonathanpetitfrere.mvvm.repository.persistence.entity.User;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Completable;
import timber.log.Timber;

/**
 * @author jpetit
 */

@Singleton
public class UserRepository {

    @Inject
    UserApi userApi;

    @Inject
    UserDao userDao;

    @Inject
    UserMapper mapper;

    @Inject
    UserRepository() {}

    public LiveData<User> getUser(String email) {
        MutableLiveData<User> liveData = new MutableLiveData<>();

        userDao.loadUser(email)
                .subscribe(liveData::setValue, Timber::d);

        userApi.getUser(email)
                .map(mapper::toEntity)
                .subscribe(userDao::saveUser, Timber::d);

        return liveData;
    }

    public LiveData<List<User>> getUsers() {
        MutableLiveData<List<User>> liveData = new MutableLiveData<>();

        userDao.loadUsers()
                .subscribe(liveData::setValue, Timber::d);

        userApi.getUsers()
                .flatMapIterable(users -> users)
                .map(mapper::toEntity)
                .subscribe(userDao::saveUser, Timber::d);

        return liveData;
    }

    public LiveData<Boolean> saveUser(User user) {
        MutableLiveData<Boolean> liveData = new MutableLiveData<>();

        Completable.fromAction(() -> userDao.saveUser(user))
                .subscribe(() -> liveData.setValue(true), throwable -> {
                    Timber.d(throwable);
                    liveData.setValue(false);
                });


        return liveData;
    }
}
