package com.example.matej.priscilla_v2.viewmodel;

import android.content.res.Configuration;
import android.graphics.Color;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MenuItem;

import com.example.matej.priscilla_v2.Constants;
import com.example.matej.priscilla_v2.Event;
import com.example.matej.priscilla_v2.R;
import com.example.matej.priscilla_v2.model.Category;
import com.example.matej.priscilla_v2.model.MainMenu;
import com.example.matej.priscilla_v2.repository.CategoryRepository;
import com.example.matej.priscilla_v2.repository.HomeRepository;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.databinding.BindingAdapter;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

public class CategoryViewModel extends ViewModel {

    @BindingAdapter("onNavigationItemSelected")
    public static void setOnNavigationItemSelectedListener(
            BottomNavigationView view, BottomNavigationView.OnNavigationItemSelectedListener listener) {
        view.setOnNavigationItemSelectedListener(listener);
    }

    private CategoryRepository categoryRepository;

    public void init(){
//        if (mutableLiveData != null){
//            return;
//        }

        categoryRepository = CategoryRepository.getInstance();
        categoryRepository.getCategoriesResponse();
    }

    public LiveData<List<Category>> getCategoryRepository(){
        return categoryRepository.getCategoriesResponse();
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
