package com.jonathanpetitfrere.mvvm.ui.base;

import android.support.annotation.LayoutRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.andrewreitz.velcro.BindableRecyclerAdapter;

import java.util.Collections;
import java.util.List;

/**
 * @author jpetit
 */

public abstract class BaseRecyclerAdapter<T> extends BindableRecyclerAdapter<T> {

    private List<T> elements = Collections.emptyList();

    @Override
    public View newView(LayoutInflater layoutInflater, int i, ViewGroup viewGroup) {
        return layoutInflater.inflate(getLayoutRes(), viewGroup, false);
    }

    @Override
    public T getItem(int i) {
        return elements.get(i);
    }

    @Override
    public void bindView(T item, View view, int i) {
        bind(item, view, i);
    }

    @Override
    public int getItemCount() {
        return elements.size();
    }

    @LayoutRes
    protected abstract int getLayoutRes();

    protected abstract void bind(T item, View view, int pos);

    public void setData(List<T> elements) {
        this.elements = elements;
        notifyDataSetChanged();
    }
}
