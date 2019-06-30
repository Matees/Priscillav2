package com.example.matej.priscilla_v2.repository;

import com.example.matej.priscilla_v2.RetrofitService;
import com.example.matej.priscilla_v2.bodies.OauthBody;
import com.example.matej.priscilla_v2.jsonApiHolder;
import com.example.matej.priscilla_v2.model.Oauth;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginRepository {

    private static LoginRepository loginRepository;

    public static LoginRepository getInstance() {
        if (loginRepository == null){
            loginRepository = new LoginRepository();
        }

        return loginRepository;
    }

    private jsonApiHolder myApi;

    public LoginRepository() {
        myApi = RetrofitService.createService(jsonApiHolder.class);
    }

    public LiveData<Oauth> getOauthResponse(String key, String email, String password){
        final MutableLiveData<Oauth> oauthResponse = new MutableLiveData<>();

        myApi.getToken(new OauthBody("password", 2, key, email, password)).enqueue(new Callback<Oauth>() {
            @Override
            public void onResponse(Call<Oauth> call, Response<Oauth> response) {
                if (!response.isSuccessful()){
                    oauthResponse.setValue(new Oauth(response.code()));
                    return;
                }

                oauthResponse.setValue(response.body());
            }

            @Override
            public void onFailure(Call<Oauth> call, Throwable t) {
                oauthResponse.setValue(new Oauth(t));
            }
        });
        return oauthResponse;
    }
}
