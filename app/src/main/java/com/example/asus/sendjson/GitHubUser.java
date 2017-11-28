package com.example.asus.sendjson;

import java.util.List;
import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by Asus on 11/27/2017.
 */

public interface GitHubUser {
    @FormUrlEncoded
    @POST("/Volley/view/")
    Call<ResponseBody>  createAccount (@FieldMap
                                       Map<String,String> map);
}
