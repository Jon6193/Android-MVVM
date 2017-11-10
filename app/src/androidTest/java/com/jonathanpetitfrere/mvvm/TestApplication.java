package com.jonathanpetitfrere.mvvm;

/**
 * @author jpetit
 */

public class TestApplication extends MvvmApplication {

    public void setAppGraph(AppGraph appGraph) {
        this.component = appGraph;
    }

}
