package com.jonathanpetitfrere.mvvm.repository.mapper;

import com.jonathanpetitfrere.mvvm.repository.persistence.entity.User;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mockito;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * @author jpetit
 */

@RunWith(JUnit4.class)
public class UserMapperTest {

    private UserMapper mapper;

    @Before
    public void setup() {
        mapper = new UserMapper();
    }

    @After
    public void tearDown() {
        mapper = null;
    }

    @Test
    public void toModel() {
        User userEntity = new User("test@gmail.com", "First Name", "Last Name");
        com.jonathanpetitfrere.mvvm.repository.api.model.User userModel = mapper.toModel(userEntity);
        assertEquals(userEntity.getEmail(), userModel.getEmail());
        assertEquals(userEntity.getFirstName(), userModel.getFirstName());
        assertEquals(userEntity.getLastName(), userModel.getLastName());
    }

    @Test
    public void toEntity() {
        com.jonathanpetitfrere.mvvm.repository.api.model.User userModel = new com.jonathanpetitfrere.mvvm.repository.api.model.User("test@gmail.com", "First Name", "Last Name");
        User userEntity = mapper.toEntity(userModel);
        assertEquals(userModel.getEmail(), userEntity.getEmail());
        assertEquals(userModel.getFirstName(), userEntity.getFirstName());
        assertEquals(userModel.getLastName(), userEntity.getLastName());
    }

}