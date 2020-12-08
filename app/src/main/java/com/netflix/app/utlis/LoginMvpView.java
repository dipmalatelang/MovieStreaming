package com.netflix.app.utlis;


import com.netflix.app.home.model.UsersResponse;

public interface LoginMvpView {
    void loginSuccess(UsersResponse usersResponse);
}
