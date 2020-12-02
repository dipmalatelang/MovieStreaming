package com.netflix.app.login;


import com.netflix.app.home.model.User;

public interface LoginMvpView {
    void loginSuccess(User user);
}
