package com.jonathanpetitfrere.mvvm.repository;

import android.arch.core.executor.testing.InstantTaskExecutorRule;
import android.arch.lifecycle.Observer;

import com.jonathanpetitfrere.mvvm.repository.api.UserApi;
import com.jonathanpetitfrere.mvvm.repository.mapper.UserMapper;
import com.jonathanpetitfrere.mvvm.repository.persistence.dao.UserDao;
import com.jonathanpetitfrere.mvvm.repository.persistence.entity.User;
import com.jonathanpetitfrere.mvvm.util.Transformers;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Flowable;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.only;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author jpetit
 */

@RunWith(JUnit4.class)
public class UserRepositoryTest {

    @Rule
    public MockitoRule rule = MockitoJUnit.rule();

    @Rule
    public InstantTaskExecutorRule instantExecutorRule = new InstantTaskExecutorRule();

    @Mock
    UserDao userDao;

    @Mock
    UserApi userApi;

    @Mock
    Transformers transformers;

    private UserRepository userRepository;
    private UserMapper mapper;

    @Before
    public void setup() {
        userRepository = new UserRepository();
        mapper = new UserMapper();

        userRepository.userDao = userDao;
        userRepository.userApi = userApi;
        userRepository.mapper = mapper;
        userRepository.transformers = transformers;
    }

    @Test
    public void getUser() {
        User userEntity = new User("john.doe@gmail.com", "John", "Doe");
        com.jonathanpetitfrere.mvvm.repository.api.model.User userModel = mapper.toModel(userEntity);

        when(transformers.applySchedulersToFlowable()).thenReturn(flowable -> flowable);
        when(userDao.loadUser(anyString())).thenReturn(Flowable.just(userEntity));
        when(userApi.getUser(anyString())).thenReturn(Flowable.just(userModel));

        Observer<User> observer = mock(Observer.class);

        userRepository.getUser(anyString())
                .observeForever(observer);

        verify(userDao, times(1)).loadUser(anyString());
        verify(userApi, times(1)).getUser(anyString());
        verify(userDao, times(1)).saveUser(userEntity);
        verify(observer, only()).onChanged(userEntity);
    }

    @Test
    public void getUsers() {
        User userEntityOne = new User("john.doe@gmail.com", "John", "Doe");
        User userEntityTwo = new User("jane.doe@gmail.com", "Jane", "Doe");

        List<User> userEntities = new ArrayList<>();
        List<com.jonathanpetitfrere.mvvm.repository.api.model.User> userModels = new ArrayList<>();

        userEntities.add(userEntityOne);
        userEntities.add(userEntityTwo);
        userModels.add(mapper.toModel(userEntityOne));
        userModels.add(mapper.toModel(userEntityTwo));

        when(transformers.applySchedulersToFlowable()).thenReturn(flowable -> flowable);
        when(userDao.loadUsers()).thenReturn(Flowable.just(userEntities));
        when(userApi.getUsers()).thenReturn(Flowable.just(userModels));

        Observer<List<User>> observer = mock(Observer.class);

        userRepository.getUsers()
                .observeForever(observer);

        verify(userDao, times(userEntities.size())).saveUser(any());
        verify(userApi, times(1)).getUsers();
        verify(observer, only()).onChanged(userEntities);
    }

    @Test
    public void saveUserSuccess() {
        User userEntity = new User("john.doe@gmail.com", "John", "Doe");

        when(transformers.applySchedulersToCompletable()).thenReturn(completable -> completable);

        Observer<Boolean> observer = mock(Observer.class);

        userRepository.saveUser(userEntity)
                .observeForever(observer);

        verify(userDao, times(1)).saveUser(userEntity);
        verify(observer, only()).onChanged(true);
    }

    @Test
    public void saveUserFail() {
        User userEntity = new User("john.doe@gmail.com", "John", "Doe");

        when(transformers.applySchedulersToCompletable()).thenReturn(completable -> completable);
        doThrow(Exception.class).when(userDao).saveUser(any());

        Observer<Boolean> observer = mock(Observer.class);

        userRepository.saveUser(userEntity)
                .observeForever(observer);

        verify(userDao, times(1)).saveUser(userEntity);
        verify(observer, only()).onChanged(false);
    }

}