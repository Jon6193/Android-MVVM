package com.jonathanpetitfrere.mvvm.di;

import com.jonathanpetitfrere.mvvm.MvvmApplication;
import com.jonathanpetitfrere.mvvm.ui.main.MainViewModel;

/**
 * This interface provides methods for all of the Dagger injection targets in the app. Making it
 * a separate interface allows normal and test components to access these methods.
 *
 * @author jpetit
 */
public interface AppGraph {
    //Application
    void inject(MvvmApplication mvvmApplication);

    //View Model
    void inject(MainViewModel mainViewModel);
}
