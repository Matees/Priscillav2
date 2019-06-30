package com.example.matej.priscilla_v2;

import com.example.matej.priscilla_v2.bodies.OauthBody;
import com.example.matej.priscilla_v2.bodies.RegisterBody;
import com.example.matej.priscilla_v2.model.Category;
import com.example.matej.priscilla_v2.model.MainMenu;
import com.example.matej.priscilla_v2.model.Oauth;
import com.example.matej.priscilla_v2.model.Register;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface jsonApiHolder {
    @Headers({
            "Accept: application/json",
            "Content-Type: application/json"
    })

    @POST("/oauth/token")
    Call<Oauth> getToken(@Body OauthBody body);

    @Headers({
            "Accept: application/json",
            "Content-Type: application/json"
    })

    @POST("/register")
    Call<Register> getToken(@Body RegisterBody body);

    @Headers({
            "Accept: application/json",
    })

    @GET("/main-menu")
    Call<MainMenu> getMainMenu(@Header("Authorization") String token);

    @Headers({
            "Accept: application/json",
    })

    @GET("/categories")
    Call<List<Category>> getCategories(@Header("Authorization") String token);
}
