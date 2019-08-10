package com.example.matej.priscilla_v2.viewmodel;

import android.util.Log;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.databinding.BindingAdapter;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.matej.priscilla_v2.R;
import com.example.matej.priscilla_v2.model.CoursePreview.CoursePreview;
import com.example.matej.priscilla_v2.model.Courses;
import com.example.matej.priscilla_v2.repository.CoursePreviewRepository;
import com.example.matej.priscilla_v2.repository.CourseRepository;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class CoursePreviewViewModel extends ViewModel {

    @BindingAdapter("onNavigationItemSelected")
    public static void setOnNavigationItemSelectedListener(
            BottomNavigationView view, BottomNavigationView.OnNavigationItemSelectedListener listener) {
        view.setOnNavigationItemSelectedListener(listener);
    }

    private LiveData<CoursePreview> coursePreviewLiveData;


    public void init(int courseId){
//        if (mutableLiveData != null){
//            return;
//        }

        CoursePreviewRepository coursePreviewRepository = CoursePreviewRepository.getInstance();
        coursePreviewLiveData = coursePreviewRepository.getCoursePreviewResponse(courseId);
    }

    public LiveData<CoursePreview> getCoursePreviewRepository(){
        return coursePreviewLiveData;
    }

    public boolean onNavigationClick(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.navigation_courses:
                Log.d(":AAAA",":AAAAA");
                return true;
            case R.id.navigation_competitions:
                Log.d(":AAAA",":AAAA");
                return true;
            case R.id.navigation_leaderboard:
                Log.d(":AAAA",":AAA");
                return true;
            case R.id.navigation_practice:
                Log.d(":AAAA",":AA");
                return true;
            case R.id.navigation_discussions:
                Log.d(":AAAA",":A");
                return true;
        }
        return false;
    }
}
