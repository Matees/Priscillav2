package com.example.matej.priscilla_v2.repository;

import com.example.matej.priscilla_v2.Constants;
import com.example.matej.priscilla_v2.KeystoreHelper;
import com.example.matej.priscilla_v2.RetrofitService;
import com.example.matej.priscilla_v2.bodies.OauthBody;
import com.example.matej.priscilla_v2.jsonApiHolder;
import com.example.matej.priscilla_v2.model.MainMenu;
import com.example.matej.priscilla_v2.model.Oauth;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeRepository {
    private static HomeRepository homeRepository;
    String token;

    public static HomeRepository getInstance() {
        if (homeRepository == null){
            homeRepository = new HomeRepository();
        }

        return homeRepository;
    }

    private jsonApiHolder myApi;

    public HomeRepository() {
        myApi = RetrofitService.createService(jsonApiHolder.class);
    }

    public LiveData<MainMenu> getMainMenuMenuResponse(){
        final MutableLiveData<MainMenu> oauthResponse = new MutableLiveData<>();
        String token = "Bearer " + KeystoreHelper.decodeKey("key");

        myApi.getMainMenu(token).enqueue(new Callback<MainMenu>() {
            @Override
            public void onResponse(Call<MainMenu> call, Response<MainMenu> response) {
                if (!response.isSuccessful()){
                    oauthResponse.setValue(new MainMenu(response.code()));
                    return;
                }

                oauthResponse.setValue(response.body());
            }

            @Override
            public void onFailure(Call<MainMenu> call, Throwable t) {
                oauthResponse.setValue(new MainMenu(t));
            }
        });
        return oauthResponse;
    }
}
