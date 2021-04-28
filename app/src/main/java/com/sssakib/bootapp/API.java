package com.sssakib.bootapp;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface API {

    @POST("register")
    Call<ResponseModel> createUser(@Body User user);

    @POST("login")
    Call<User> checkUser( @Body User user);

    @PUT("update/{id}")
    Call<User> updateUser(@Body User user, @Path("id") long id);
}
