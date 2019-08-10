package com.example.matej.priscilla_v2.viewmodel;

import android.view.View;
import android.widget.TextView;

import com.example.matej.priscilla_v2.AuthListener;
import com.example.matej.priscilla_v2.repository.LoginRepository;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import androidx.databinding.BindingAdapter;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class LoginViewModel extends ViewModel {
    private MutableLiveData<String> mutableLiveData;
    private LoginRepository loginRepository;

    private String email = "";
    private String password = "";
    private String key = "";


    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAuthListener(AuthListener authListener) {
        this.authListener = authListener;
    }

    private AuthListener authListener;

    public void init(){
//        if (mutableLiveData != null){
//            return;
//        }

        loginRepository = LoginRepository.getInstance();
//        mutableLiveData = loginRepository.getOauthResponse();
    }

    public void onLoginButtonClick(View view){
        authListener.onStarted();
        // validation
        if (email.isEmpty() || password.isEmpty()){
            authListener.onFailure("Empty email or password.");
            return;
        }

//        if (!isEmailValid(email.trim())){
//            authListener.onFailure("Invalid email.");
//            return;
//        }

//        if (password.trim().length() < 6){
//            authListener.onFailure("Password must constain six characters.");
//            return;
//        }


        authListener.onSuccess(loginRepository.getOauthResponse(key, email, password));
    }

    public void onRegisterButtonClick(View view){
        authListener.onRegisterClick();
    }

    public static boolean isEmailValid(String email) {
        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
