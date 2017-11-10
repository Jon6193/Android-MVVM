package com.jonathanpetitfrere.mvvm.ui.main;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Transformations;

import com.jonathanpetitfrere.mvvm.ui.base.BaseViewModel;

/**
 * @author jpetit
 */

public class FirstNameViewModel extends BaseViewModel {

    private final MutableLiveData<String> firstNameLiveData = new MutableLiveData<>();

    private final LiveData<String> firstLetterLiveData = Transformations.map(firstNameLiveData, firstName -> firstName.isEmpty() ? "" : firstName.substring(0, 1));

    void setFirstName(String firstName) {
        firstNameLiveData.setValue(firstName);
    }

    LiveData<String> firstLetter() {
        return firstLetterLiveData;
    }

    LiveData<String> firstName() {
        return firstNameLiveData;
    }
}
