package com.jonathanpetitfrere.mvvm;

import com.jonathanpetitfrere.mvvm.di.AppGraph;

/**
 * @author jpetit
 */

public class TestApplication extends MvvmApplication {

    public void setAppGraph(AppGraph appGraph) {
        this.component = appGraph;
    }

}
