package com.example.matej.priscilla_v2.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.matej.priscilla_v2.R;
import com.example.matej.priscilla_v2.RegisterListener;
import com.example.matej.priscilla_v2.databinding.ActivityLoginBinding;
import com.example.matej.priscilla_v2.databinding.ActivityRegisterBinding;
import com.example.matej.priscilla_v2.model.Oauth;
import com.example.matej.priscilla_v2.model.Register;
import com.example.matej.priscilla_v2.viewmodel.LoginViewModel;
import com.example.matej.priscilla_v2.viewmodel.RegisterViewModel;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

public class RegisterActivity extends AppCompatActivity implements RegisterListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        RegisterViewModel registerViewModel = ViewModelProviders.of(this).get(RegisterViewModel.class);
        registerViewModel.init();  // calling request for access token
//        loginViewModel.getLoginRepository().observe(this, new Observer<Oauth>() {   // observing response of request
//            @Override
//            public void onChanged(Oauth oauth) {
//                email.setText(oauth.getAccessToken());
//            }
//        });
        ActivityRegisterBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_register);
        binding.setViewmodel(registerViewModel);
        registerViewModel.setAuthListener(this);
    }

    @Override
    public void onStarted() {

    }

    @Override
    public void onSuccess(MutableLiveData<Register> registerResponse) {
        registerResponse.observe(this, new Observer<Register>() {
            @Override
            public void onChanged(Register register) {
                if (register.getId() == 400){
                    Toast.makeText(RegisterActivity.this, "User already exists.", Toast.LENGTH_SHORT).show();
                    return;
                }
                setResult(Activity.RESULT_OK, new Intent());
                finish();
            }
        });
    }

    @Override
    public void onFailure(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
