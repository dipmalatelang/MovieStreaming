package com.netflix.app.register;


import com.netflix.app.home.model.User;

public interface RegisterMvpView {
    void allreadyExits(String str);

    void registerUsers(User user);
}
