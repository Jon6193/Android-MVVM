package com.jonathanpetitfrere.mvvm.ui.main;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.widget.RecyclerView;

import com.jonathanpetitfrere.mvvm.R;
import com.jonathanpetitfrere.mvvm.repository.persistence.entity.User;
import com.jonathanpetitfrere.mvvm.ui.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author jpetit
 */

public class MainActivity extends BaseActivity {

    @BindView(R.id.main_email_input)
    TextInputEditText emailInput;

    @BindView(R.id.main_first_name_input)
    TextInputEditText firstNameInput;

    @BindView(R.id.main_last_name_input)
    TextInputEditText lastNameInput;

    @BindView(R.id.main_recycler_view)
    RecyclerView recyclerView;

    MainViewModel viewModel;

    private UserRecyclerAdapter userRecyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        userRecyclerAdapter = new UserRecyclerAdapter();
        recyclerView.setAdapter(userRecyclerAdapter);

        viewModel = ViewModelProviders.of(this).get(MainViewModel.class);

        viewModel.getUsers()
                .observe(this, userRecyclerAdapter::setData);
    }

    @Override
    public int getLayoutRes() {
        return R.layout.activity_main;
    }

    @OnClick(R.id.main_create_button)
    void onCreateUserClicked() {
        String email = emailInput.getText().toString();
        String firstName = firstNameInput.getText().toString();
        String lastName = lastNameInput.getText().toString();

        User user = new User(email, firstName, lastName);
        viewModel.createUser(user);
    }
}
