package com.jonathanpetitfrere.mvvm.repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.jonathanpetitfrere.mvvm.repository.api.UserApi;
import com.jonathanpetitfrere.mvvm.repository.mapper.UserMapper;
import com.jonathanpetitfrere.mvvm.repository.persistence.dao.UserDao;
import com.jonathanpetitfrere.mvvm.repository.persistence.entity.User;
import com.jonathanpetitfrere.mvvm.util.Transformers;

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
    Transformers transformers;

    @Inject
    UserRepository() {}

    public LiveData<User> getUser(String email) {
        MutableLiveData<User> liveData = new MutableLiveData<>();

        userDao.loadUser(email)
                .compose(transformers.applySchedulersToFlowable())
                .subscribe(liveData::setValue, Timber::d);

        userApi.getUser(email)
                .compose(transformers.applySchedulersToFlowable())
                .map(mapper::toEntity)
                .subscribe(userDao::saveUser, Timber::d);

        return liveData;
    }

    public LiveData<List<User>> getUsers() {
        MutableLiveData<List<User>> liveData = new MutableLiveData<>();

        userDao.getUsers()
                .compose(transformers.applySchedulersToFlowable())
                .subscribe(liveData::setValue, Timber::d);

        userApi.getUsers()
                .compose(transformers.applySchedulersToFlowable())
                .flatMapIterable(users -> users)
                .map(mapper::toEntity)
                .subscribe(userDao::saveUser, Timber::d);

        return liveData;
    }

    public LiveData<Boolean> saveUser(User user) {
        MutableLiveData<Boolean> liveData = new MutableLiveData<>();

        Completable.fromAction(() -> userDao.saveUser(user))
                .compose(transformers.applySchedulersToCompletable())
                .subscribe(() -> liveData.setValue(true), throwable -> {
                    Timber.d(throwable);
                    liveData.setValue(false);
                });


        return liveData;
    }
}
