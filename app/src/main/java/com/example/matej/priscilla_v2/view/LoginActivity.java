package com.example.matej.priscilla_v2.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.matej.priscilla_v2.AuthListener;
import com.example.matej.priscilla_v2.Constants;
import com.example.matej.priscilla_v2.KeystoreHelper;
import com.example.matej.priscilla_v2.LoginResolver;
import com.example.matej.priscilla_v2.R;
import com.example.matej.priscilla_v2.databinding.ActivityLoginBinding;
import com.example.matej.priscilla_v2.model.Oauth;
import com.example.matej.priscilla_v2.viewmodel.LoginViewModel;

import java.security.Key;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity implements AuthListener {
    LoginViewModel loginViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Constants.instance(this.getApplicationContext());
//        if(!LoginResolver.getSharedPreferences(this).getAll().isEmpty())
//        {
//            startActivity(new Intent(this, HomeActivity.class));
//            this.finish();
//        }

//        keyHelper = new KeystoreHelper();

        loginViewModel = ViewModelProviders.of(this).get(LoginViewModel.class);
        loginViewModel.init();  // creating loginRepository if already does not exist
//        loginViewModel.getLoginRepository().observe(this, new Observer<Oauth>() {   // observing response of request
//            @Override
//            public void onChanged(Oauth oauth) {
//                Toast.makeText(LoginActivity.this, "Check your internet connection.", Toast.LENGTH_SHORT).show();
//            }
//        });

        ActivityLoginBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        binding.setViewmodel(loginViewModel);
        binding.getViewmodel().setKey(getResources().getString(R.string.serviceKey));
        loginViewModel.setAuthListener(this);
    }

    @Override
    public void onRegisterClick() {
        startActivityForResult(new Intent(this, RegisterActivity.class), 1);
    }

    @Override
    public void onStarted() {
        findViewById(R.id.progressBar).setVisibility(View.VISIBLE);
    }

    @Override
    public void onSuccess(LiveData<Oauth> oauthResponse) {
        oauthResponse.observe(this, new Observer<Oauth>() {
            @Override
            public void onChanged(Oauth oauth) {    // observing livedata if button was clicked and validation was successfull
                findViewById(R.id.progressBar).setVisibility(View.GONE);

                if (oauth.getT() != null){
                    Toast.makeText(LoginActivity.this, "Check your internet connection.", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (oauth.getCode() != 0){
                    switch (oauth.getCode()){
                        case 401: Toast.makeText(LoginActivity.this, "You were not authorized.", Toast.LENGTH_SHORT).show();
                        break;
                    }
                    return;
                }

//                Toast.makeText(LoginActivity.this, oauth.getAccessToken(), Toast.LENGTH_SHORT).show();
                KeystoreHelper.encode(oauth.getAccessToken(), "keyy");
                startActivity(new Intent(LoginActivity.this, HomeActivity.class));
            }
        });
    }

    @Override
    public void onFailure(String message) {
        findViewById(R.id.progressBar).setVisibility(View.GONE);
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1) {
            if(resultCode == Activity.RESULT_OK){
                Toast.makeText(this, "You were successfully registered.", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
