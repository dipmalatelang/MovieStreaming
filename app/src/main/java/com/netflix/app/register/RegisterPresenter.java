package com.netflix.app.register;

import com.netflix.app.home.model.User;
import com.netflix.app.utlis.RetrofitClient;

import java.io.PrintStream;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterPresenter {
    /* access modifiers changed from: private */
    public RegisterMvpView mView;

    RegisterPresenter(RegisterMvpView mView2) {
        this.mView = mView2;
    }

    /* access modifiers changed from: package-private */
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
