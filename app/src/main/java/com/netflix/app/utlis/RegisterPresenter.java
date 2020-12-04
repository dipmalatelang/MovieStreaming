package com.netflix.app.utlis;

import com.netflix.app.home.model.User;

import java.io.PrintStream;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterPresenter {

    public RegisterMvpView mView;

    public RegisterPresenter(RegisterMvpView mView2) {
        this.mView = mView2;
    }


    public void addUserAccount(User userProfile) {
        RetrofitClient.getInstance().getApi().createUser(userProfile).enqueue(new Callback<User>() {
            public void onResponse(Call<User> call, Response<User> response) {
                try {
                    PrintStream printStream = System.out;
                    printStream.println("response all ready" + response.body());
                    PrintStream printStream2 = System.out;
                    printStream2.println("response status ready" + response.code());
                    if (response.code() == 409) {
                        RegisterPresenter.this.mView.allreadyExits("");
                    } else if (response.code() == 201) {
                        System.out.println(response);
                        RegisterPresenter.this.mView.registerUsers(response.body());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            public void onFailure(Call<User> call, Throwable t) {
            }
        });
    }
}
