package com.jonathanpetitfrere.mvvm.ui.main;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.jonathanpetitfrere.mvvm.repository.UserRepository;
import com.jonathanpetitfrere.mvvm.repository.persistence.entity.User;
import com.jonathanpetitfrere.mvvm.ui.base.BaseViewModel;
import com.jonathanpetitfrere.mvvm.util.RxSchedulers;

import java.util.List;

import javax.inject.Inject;

/**
 * @author jpetit
 */

public class MainViewModel extends BaseViewModel {

    @Inject
    UserRepository userRepository;

    @Inject
    RxSchedulers schedulers;

    private MutableLiveData<String> emailLiveData = new MutableLiveData<>();

    private MutableLiveData<String> firstNameLiveData = new MutableLiveData<>();

    private MutableLiveData<String> lastNameLiveData = new MutableLiveData<>();

    private MutableLiveData<Boolean> validInputLiveData = new MutableLiveData<>();

    @Inject
    MainViewModel() {
    }

    public LiveData<Boolean> createUser() {
        User user = new User(emailLiveData.getValue(), firstNameLiveData.getValue(), lastNameLiveData.getValue());
        return userRepository.saveUser(user);
    }

    public LiveData<User> getUser(String email) {
        return userRepository.getUser(email);
    }

    public LiveData<List<User>> getUsers() {
        return userRepository.getUsers();
    }

    public LiveData<Boolean> validInput() {
        //default value
        if(validInputLiveData.getValue() == null) {
            validInputLiveData.setValue(false);
        }

        return validInputLiveData;
    }

    public void setEmail(String email) {
        emailLiveData.setValue(email);
        validateInput();
    }

    public void setFirstName(String firstName) {
        firstNameLiveData.setValue(firstName);
        validateInput();
    }

    public void setLastName(String lastName) {
        lastNameLiveData.setValue(lastName);
        validateInput();
    }

    private void validateInput() {
        String email = emailLiveData.getValue();
        String firstName = firstNameLiveData.getValue();
        String lastName = lastNameLiveData.getValue();

        boolean validInput = (email != null && !email.isEmpty())
                && (firstName != null && !firstName.isEmpty())
                && (lastName != null && !lastName.isEmpty());

        validInputLiveData.setValue(validInput);
    }

}
