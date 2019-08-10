package com.example.matej.priscilla_v2.repository;

import com.example.matej.priscilla_v2.KeystoreHelper;
import com.example.matej.priscilla_v2.RetrofitService;
import com.example.matej.priscilla_v2.jsonApiHolder;
import com.example.matej.priscilla_v2.model.Category;
import com.example.matej.priscilla_v2.model.MainMenu;
import com.google.gson.Gson;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoryRepository {
    private static CategoryRepository categoryRepository;
    String token;

    public static CategoryRepository getInstance() {
        if (categoryRepository == null){
            categoryRepository = new CategoryRepository();
        }

        return categoryRepository;
    }

    private jsonApiHolder myApi;

    public CategoryRepository() {
        myApi = RetrofitService.createService(jsonApiHolder.class);
    }

    public LiveData<List<Category>> getCategoriesResponse(){
        final MutableLiveData<List<Category>> oauthResponse = new MutableLiveData<>();

        String token = "Bearer " + KeystoreHelper.decodeKey("key");

        myApi.getCategories(token).enqueue(new Callback<List<Category>>() {
            @Override
            public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {
                if (!response.isSuccessful()){
                    Category cat = new Category(response.code());
                    List<Category> categories = new ArrayList<>();
                    categories.add(cat);
                    oauthResponse.setValue(categories);
                }

            oauthResponse.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<Category>> call, Throwable t) {
                Category cat = new Category(t);
                List<Category> categories = new ArrayList<>();
                categories.add(cat);
                oauthResponse.setValue(categories);
            }
        });
        return oauthResponse;
    }
}
