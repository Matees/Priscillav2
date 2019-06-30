package com.example.matej.priscilla_v2.viewmodel;

import android.view.View;

import com.example.matej.priscilla_v2.RegisterListener;
import com.example.matej.priscilla_v2.repository.RegisterRepository;
import com.example.matej.priscilla_v2.model.Register;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class RegisterViewModel extends ViewModel {
    private MutableLiveData<Register> mutableLiveData;
    private RegisterRepository registerRepository;

    private String email = "";
    private String password = "";
    private String forename = "";
    private String surname = "";

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

    public String getForename() {
        return forename;
    }

    public void setForename(String forename) {
        this.forename = forename;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setAuthListener(RegisterListener registerListener) {
        this.registerListener = registerListener;
    }

    private RegisterListener registerListener;

    public void init(){
        if (mutableLiveData != null){
            return;
        }

        registerRepository = RegisterRepository.getInstance();
//        mutableLiveData = registerRepository.getOregisterResponse();
    }

    public void onRegisterButtonClick(View view){
        registerListener.onStarted();
        // validation
        if (email.isEmpty() || password.isEmpty() || forename.isEmpty() || surname.isEmpty()){
            registerListener.onFailure("Empty one or more fields.");
            return;
        }

        if (!isEmailValid(email)){
            registerListener.onFailure("Invalid email.");
            return;
        }

        if (password.trim().length() < 6){
            registerListener.onFailure("Password must constain six characters.");
            return;
        }

        registerListener.onSuccess(registerRepository.getRegisterResponse(email, password, forename, surname));
    }

    public static boolean isEmailValid(String email) {
        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
