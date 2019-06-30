package com.example.matej.priscilla_v2;

import com.example.matej.priscilla_v2.model.Oauth;

import androidx.lifecycle.LiveData;

public interface AuthListener {
    void onRegisterClick();
    void onStarted();
    void onFailure(String message);
    void onSuccess(LiveData<Oauth> oauthResponse);
}
