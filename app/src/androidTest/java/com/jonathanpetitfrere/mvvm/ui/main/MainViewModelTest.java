package com.jonathanpetitfrere.mvvm.ui.main;

import android.arch.core.executor.testing.InstantTaskExecutorRule;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.jonathanpetitfrere.mvvm.TestApplication;
import com.jonathanpetitfrere.mvvm.repository.UserRepository;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.only;
import static org.mockito.Mockito.verify;

/**
 * @author jpetit
 */

@RunWith(AndroidJUnit4.class)
public class MainViewModelTest {

    @Rule
    public MockitoRule rule = MockitoJUnit.rule();

    @Rule
    public InstantTaskExecutorRule instantExecutorRule = new InstantTaskExecutorRule();

    @Mock
    public UserRepository userRepository;

    private MainViewModel viewModel;

    @Before
    public void setup() {
        TestApplication application = ((TestApplication) InstrumentationRegistry.getTargetContext().getApplicationContext());
        viewModel = new MainViewModel(application);
        viewModel.userRepository = userRepository;
    }

    @Test
    public void createUser() {
        String email = "john.doe@gmail.com";
        String firstName = "John";
        String lastName = "Doe";

        viewModel.setEmail(email);
        viewModel.setFirstName(firstName);
        viewModel.setLastName(lastName);

        viewModel.createUser();
        verify(userRepository, only()).saveUser(any());
    }

    @Test
    public void getUser() {
        String email = "john.doe@gmail.com";
        viewModel.getUser(email);
        verify(userRepository, only()).getUser(email);
    }

    @Test
    public void getUsers() {
        viewModel.getUsers();
        verify(userRepository, only()).getUsers();
    }

}