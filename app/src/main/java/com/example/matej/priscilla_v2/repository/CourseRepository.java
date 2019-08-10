package com.example.matej.priscilla_v2.repository;

import com.example.matej.priscilla_v2.KeystoreHelper;
import com.example.matej.priscilla_v2.RetrofitService;
import com.example.matej.priscilla_v2.jsonApiHolder;
import com.example.matej.priscilla_v2.model.Area;
import com.example.matej.priscilla_v2.model.Course;
import com.example.matej.priscilla_v2.model.Courses;

import java.util.ArrayList;
import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CourseRepository {
    private static CourseRepository courseRepository;

    public static CourseRepository getInstance() {
        if (courseRepository == null){
            courseRepository = new CourseRepository();
        }

        return courseRepository;
    }

    private jsonApiHolder myApi;

    public CourseRepository() {
        myApi = RetrofitService.createService(jsonApiHolder.class);
    }

    public LiveData<Courses> getCourseResponse(int areaId){
        final MutableLiveData<Courses> oauthResponse = new MutableLiveData<>();

        String token = "Bearer " + KeystoreHelper.decodeKey("key");

        myApi.getCourses(areaId, token).enqueue(new Callback<Courses>() {
            @Override
            public void onResponse(Call<Courses> call, Response<Courses> response) {
                if (!response.isSuccessful()){
                    Courses course = new Courses(response.code());
                    oauthResponse.setValue(course);
                }

            oauthResponse.setValue(response.body());
            }

            @Override
            public void onFailure(Call<Courses> call, Throwable t) {
                Courses course = new Courses(t);
                oauthResponse.setValue(course);
            }
        });
        return oauthResponse;
    }
}
