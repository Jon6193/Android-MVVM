package com.jonathanpetitfrere.mvvm.ui.main;

import android.app.Application;
import android.arch.lifecycle.LiveData;

import com.jonathanpetitfrere.mvvm.persistence.MvvmDatabase;
import com.jonathanpetitfrere.mvvm.persistence.entity.User;
import com.jonathanpetitfrere.mvvm.ui.base.BaseAndroidViewModel;

import javax.inject.Inject;

/**
 * @author jpetit
 */

public class MainViewModel extends BaseAndroidViewModel {

    @Inject
    MvvmDatabase database;

    public MainViewModel(Application application) {
        super(application);
        getMvvmApplication().getComponent().inject(this);
    }

    public void updateUser(User user) {
        database.userDao().saveUser(user);
    }

    public LiveData<User> getUser(String email) {
        return database.userDao().loadUser(email);
    }

}
