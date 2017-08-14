package com.jonathanpetitfrere.mvvm.ui.main;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.widget.Button;

import com.jonathanpetitfrere.mvvm.R;
import com.jonathanpetitfrere.mvvm.repository.persistence.entity.User;
import com.jonathanpetitfrere.mvvm.ui.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.OnTextChanged;
import timber.log.Timber;

import static butterknife.OnTextChanged.Callback.AFTER_TEXT_CHANGED;

/**
 * @author jpetit
 */

public class MainActivity extends BaseActivity {

    @BindView(R.id.main_recycler_view)
    RecyclerView recyclerView;

    @BindView(R.id.main_create_button)
    Button createButton;

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

        viewModel.validInput()
                .observe(this, createButton::setEnabled);
    }

    @Override
    public int getLayoutRes() {
        return R.layout.activity_main;
    }

    @OnTextChanged(value = R.id.main_email_input, callback = AFTER_TEXT_CHANGED)
    void onEmailTextChange(Editable input) {
        viewModel.setEmail(input.toString());
    }

    @OnTextChanged(value = R.id.main_first_name_input, callback = AFTER_TEXT_CHANGED)
    void onFirstNameTextChange(Editable input) {
        viewModel.setFirstName(input.toString());
    }

    @OnTextChanged(value = R.id.main_last_name_input, callback = AFTER_TEXT_CHANGED)
    void onLastNameTextChange(Editable input) {
        viewModel.setLastName(input.toString());
    }

    @OnClick(R.id.main_create_button)
    void onCreateUserClicked() {
        viewModel.createUser();
    }
}
