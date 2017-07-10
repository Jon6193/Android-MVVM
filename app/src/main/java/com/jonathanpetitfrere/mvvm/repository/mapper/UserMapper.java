package com.jonathanpetitfrere.mvvm.repository.mapper;

import com.jonathanpetitfrere.mvvm.repository.api.model.User;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * @author jpetit
 */

@Singleton
public class UserMapper {

    @Inject
    public UserMapper() {}

    public User toModel(com.jonathanpetitfrere.mvvm.repository.persistence.entity.User userEntity) {
        return new User(userEntity.getEmail(), userEntity.getFirstName(), userEntity.getLastName());
    }

    public com.jonathanpetitfrere.mvvm.repository.persistence.entity.User toEntity(User userModel) {
        return new com.jonathanpetitfrere.mvvm.repository.persistence.entity.User(userModel.getEmail(), userModel.getFirstName(), userModel.getLastName());
    }

}
