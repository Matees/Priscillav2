package com.example.matej.priscilla_v2.viewmodel;

import android.util.Log;
import android.view.MenuItem;

import com.example.matej.priscilla_v2.R;
import com.example.matej.priscilla_v2.model.Area;
import com.example.matej.priscilla_v2.model.Category;
import com.example.matej.priscilla_v2.repository.AreaRepository;
import com.example.matej.priscilla_v2.repository.CategoryRepository;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.databinding.BindingAdapter;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

public class AreaViewModel extends ViewModel {

    @BindingAdapter("onNavigationItemSelected")
    public static void setOnNavigationItemSelectedListener(
            BottomNavigationView view, BottomNavigationView.OnNavigationItemSelectedListener listener) {
        view.setOnNavigationItemSelectedListener(listener);
    }

    private LiveData<List<Area>> areaLiveData;


    public void init(int id){
//        if (mutableLiveData != null){
//            return;
//        }

        AreaRepository categoryRepository = AreaRepository.getInstance();
        areaLiveData = categoryRepository.getAreasResponse(id);
    }

    public LiveData<List<Area>> getAreaRepository(){
        return areaLiveData;
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
