package com.netflix.app.utlis;


import android.util.Log;

import com.netflix.app.home.model.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterPresenter {

    public RegisterMvpView mView;


    public RegisterPresenter(RegisterMvpView mView2) {
        this.mView = mView2;
    }
    public void adduser(User user) {
        RetrofitClient.getInstance().getApi().createUser(user).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.code() == 200) {
                    RegisterPresenter.this.mView.registerUsers(response.body());
                    Log.d("TAG", "onResponse: " + response.code());

                } else if (response.code() == 409){
                    RegisterPresenter.this.mView.allreadyExits("");
                    Log.d("TAG", "onResponse: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.d("TAG", "onFailure: " + t);
            }
        });
    }



}
