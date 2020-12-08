package com.netflix.app.utlis;


import com.netflix.app.home.model.UsersResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginPresenter {

    public LoginMvpView mView;

    public LoginPresenter(LoginMvpView mView2) {
        this.mView = mView2;
    }


    public void checkLoginCredentials(String email, String password) {
        RetrofitClient.getInstance().getApi().checkLoginCredentials(email, password).enqueue(new Callback<UsersResponse>() {
            public void onResponse(Call<UsersResponse> call, Response<UsersResponse> response) {
                try {
                    LoginPresenter.this.mView.loginSuccess(response.body());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            public void onFailure(Call<UsersResponse> call, Throwable t) {
            }
        });
    }
}
