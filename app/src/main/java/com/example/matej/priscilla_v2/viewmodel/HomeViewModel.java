package com.example.matej.priscilla_v2.viewmodel;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.matej.priscilla_v2.AuthListener;
import com.example.matej.priscilla_v2.Event;
import com.example.matej.priscilla_v2.R;
import com.example.matej.priscilla_v2.model.MainMenu;
import com.example.matej.priscilla_v2.repository.HomeRepository;
import com.example.matej.priscilla_v2.repository.LoginRepository;
import com.example.matej.priscilla_v2.view.CategoryActivity;
import com.example.matej.priscilla_v2.view.HomeActivity;
import com.example.matej.priscilla_v2.view.LoginActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.lang.ref.WeakReference;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import androidx.annotation.NonNull;
import androidx.databinding.BindingAdapter;
import androidx.databinding.BindingMethod;
import androidx.databinding.BindingMethods;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class HomeViewModel extends ViewModel {

    @BindingAdapter("onNavigationItemSelected")
    public static void setOnNavigationItemSelectedListener(
            BottomNavigationView view, BottomNavigationView.OnNavigationItemSelectedListener listener) {
        view.setOnNavigationItemSelectedListener(listener);
    }

    private HomeRepository homeRepository;
    private MutableLiveData<Event<String>> navigateToDetails = new MutableLiveData<>();


    public MutableLiveData<Event<String>> getNavigateToDetails() {
        return navigateToDetails;
    }

    public void init(){
//        if (mutableLiveData != null){
//            return;
//        }

        homeRepository = HomeRepository.getInstance();
        homeRepository.getMainMenuMenuResponse();
    }

    public LiveData<MainMenu> getHomeRepository(){
        return homeRepository.getMainMenuMenuResponse();
    }

    public boolean onNavigationClick(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.navigation_courses:
                navigateToDetails.setValue(new Event<String>(String.valueOf(item.getItemId())));
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
