package com.jonathanpetitfrere.mvvm.ui.main;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.widget.TextView;

import com.jonathanpetitfrere.mvvm.R;
import com.jonathanpetitfrere.mvvm.persistence.entity.User;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author jpetit
 */

public class UserListItemView extends ConstraintLayout {

    @BindView(R.id.user_email_text)
    TextView emailText;

    @BindView(R.id.user_first_name_text)
    TextView firstNameText;

    @BindView(R.id.user_last_name_text)
    TextView lastNameText;

    public UserListItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        ButterKnife.bind(this);
    }

    public void bind(User user) {
        emailText.setText(user.getEmail());
        firstNameText.setText(user.getFirstName());
        lastNameText.setText(user.getLastName());
    }
}
