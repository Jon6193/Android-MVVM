package com.jonathanpetitfrere.mvvm.ui.main;

import android.view.View;

import com.jonathanpetitfrere.mvvm.R;
import com.jonathanpetitfrere.mvvm.repository.persistence.entity.User;
import com.jonathanpetitfrere.mvvm.ui.base.BaseRecyclerAdapter;

/**
 * @author jpetit
 */

public class UserRecyclerAdapter extends BaseRecyclerAdapter<User> {

    @Override
    protected int getLayoutRes() {
        return R.layout.list_item_user;
    }

    @Override
    protected void bind(User item, View view, int pos) {
        ((UserListItemView) view).bind(item);
    }
}
