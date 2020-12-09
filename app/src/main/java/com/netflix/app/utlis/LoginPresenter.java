package com.netflix.app.utlis;


import android.util.Log;

import com.netflix.app.home.model.User;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginPresenter {

    public LoginMvpView mView;

    public LoginPresenter(LoginMvpView mView2) {
        this.mView = mView2;
    }


    public void LoginCredentials(String email, String password){
        RetrofitClient.getInstance().getApi().checkLoginCredentials(email , password).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {


                if(response.code() == 200){
                    Log.d("TAG", "onResponseLoging: "+response.code());
                    LoginPresenter.this.mView.loginSuccess(response.body());
                }
                else {
                    Log.d("TAG", "onResponseLoging: "+response.code());
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.d("TAG", "onResponseLoging: "+t);
            }
        });
    }}
