package com.jonathanpetitfrere.mvvm.ui.main;

import android.app.Application;
import android.arch.lifecycle.LiveData;

import com.jonathanpetitfrere.mvvm.persistence.entity.User;
import com.jonathanpetitfrere.mvvm.ui.base.BaseAndroidViewModel;

/**
 * @author jpetit
 */

public class MainViewModel extends BaseAndroidViewModel {

    public MainViewModel(Application application) {
        super(application);
    }

    public void updateUser(User user) {
        getMvvmDatabase().userDao().saveUser(user);
    }

    public LiveData<User> getUser(String email) {
        return getMvvmDatabase().userDao().loadUser(email);
    }

}
