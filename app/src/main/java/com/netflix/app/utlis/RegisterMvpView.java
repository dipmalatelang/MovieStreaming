package com.netflix.app.utlis;


import com.netflix.app.home.model.User;

public interface RegisterMvpView {
    void allreadyExits(String str);

    void registerUsers(User user);
}
