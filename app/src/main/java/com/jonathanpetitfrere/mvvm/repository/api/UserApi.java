package com.jonathanpetitfrere.mvvm.repository.api;

import com.jonathanpetitfrere.mvvm.repository.api.model.User;

import java.util.List;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * @author jpetit
 */

public interface UserApi {

    @GET("user/{email}")
    Flowable<User> getUser(@Path("email") String email);

    @GET("users")
    Flowable<List<User>> getUsers();

    @POST("user")
    void createUser(User user);
}
