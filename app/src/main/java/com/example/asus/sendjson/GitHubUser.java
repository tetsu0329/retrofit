package com.example.asus.sendjson;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by Asus on 11/27/2017.
 */

public interface GitHubUser {
    @POST("view")
    Call<User>  createAccount (@Body User user);
}
