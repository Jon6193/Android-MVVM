package com.jonathanpetitfrere.mvvm.ui.main;

import android.os.SystemClock;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.jonathanpetitfrere.mvvm.R;
import com.jonathanpetitfrere.mvvm.TestApplication;
import com.jonathanpetitfrere.mvvm.di.TestComponent;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static junit.framework.Assert.assertEquals;

/**
 * @author jpetit
 */

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public final ActivityTestRule<MainActivity> activityTestRule = new ActivityTestRule<>(MainActivity.class, false, false);

    @Before
    public void setup() {
        TestApplication application = ((TestApplication) InstrumentationRegistry.getTargetContext().getApplicationContext());
        TestComponent component = TestComponent.Initializer.init(application);
        application.setAppGraph(component);
        activityTestRule.launchActivity(null);
    }

    @Test
    public void enterUserDataAndCreateUser() {
        String email = "John.doe@gmail.com";
        String firstName = "John";
        String lastName = "Doe";

        onView(withId(R.id.main_email_input))
                .perform(typeText(email));

        onView(withId(R.id.main_first_name_input))
                .perform(typeText(firstName));

        onView(withId(R.id.main_last_name_input))
                .perform(typeText(lastName));

        onView(withId(R.id.main_create_button))
                .perform(click());

        MainActivity activity = activityTestRule.getActivity();
        UserListItemView view = (UserListItemView) activity.recyclerView.getChildAt(0);

        assertEquals("email == email", email, view.emailText.getText().toString());
        assertEquals("firstName == firstName", firstName, view.firstNameText.getText().toString());
        assertEquals("lastName == lastName", lastName, view.lastNameText.getText().toString());
    }

}