package com.jonathanpetitfrere.mvvm.ui.main;

import android.app.Application;
import android.arch.lifecycle.LiveData;

import com.jonathanpetitfrere.mvvm.repository.UserRepository;
import com.jonathanpetitfrere.mvvm.repository.persistence.entity.User;
import com.jonathanpetitfrere.mvvm.ui.base.BaseAndroidViewModel;

import java.util.List;

import javax.inject.Inject;

/**
 * @author jpetit
 */

public class MainViewModel extends BaseAndroidViewModel {

    @Inject
    UserRepository userRepository;

    public MainViewModel(Application application) {
        super(application);
        getMvvmApplication().getComponent().inject(this);
    }

    public LiveData<Boolean> createUser(User user) {
        return userRepository.saveUser(user);
    }

    public LiveData<User> getUser(String email) {
        return userRepository.getUser(email);
    }

    public LiveData<List<User>> getUsers() {
        return userRepository.getUsers();
    }

}
