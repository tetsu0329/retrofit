package com.example.asus.sendjson;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Asus on 11/24/2017.
 */

public interface GitHubClient {

    @GET("/Volley/{user}/")
    Call<List<User>> reposForUser(@Path("user") String user);
}
