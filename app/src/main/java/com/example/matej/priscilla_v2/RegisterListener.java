package com.example.matej.priscilla_v2;

import com.example.matej.priscilla_v2.model.Oauth;
import com.example.matej.priscilla_v2.model.Register;

import androidx.lifecycle.MutableLiveData;

public interface RegisterListener {
        void onStarted();
        void onSuccess(MutableLiveData<Register> oauthResponse);
        void onFailure(String message);
}
