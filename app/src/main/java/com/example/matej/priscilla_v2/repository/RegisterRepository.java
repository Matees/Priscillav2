package com.example.matej.priscilla_v2.repository;

import com.example.matej.priscilla_v2.RetrofitService;
import com.example.matej.priscilla_v2.bodies.RegisterBody;
import com.example.matej.priscilla_v2.jsonApiHolder;
import com.example.matej.priscilla_v2.model.Register;

import androidx.lifecycle.MutableLiveData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterRepository {
    private static RegisterRepository registerRepository;

    public static RegisterRepository getInstance() {
        if (registerRepository == null){
            registerRepository = new RegisterRepository();
        }

        return registerRepository;
    }

    private jsonApiHolder myApi;

    public RegisterRepository() {
        myApi = RetrofitService.createService(jsonApiHolder.class);
    }

    public MutableLiveData<Register> getRegisterResponse(String email, String password, String forename, String surname){
        final MutableLiveData<Register> registerResponse = new MutableLiveData<>();

        myApi.getToken(new RegisterBody(forename, surname, 1, email, password, 1)).enqueue(new Callback<Register>() {
            @Override
            public void onResponse(Call<Register> call, Response<Register> response) {
                if (!response.isSuccessful()){
                    Register wrongCode = new Register();
                    wrongCode.setId(response.code());
                    registerResponse.setValue(wrongCode);
                    return;
                }

                registerResponse.setValue(response.body());
            }

            @Override
            public void onFailure(Call<Register> call, Throwable t) {
                registerResponse.setValue(null);
            }
        });
        return registerResponse;
    }

}
