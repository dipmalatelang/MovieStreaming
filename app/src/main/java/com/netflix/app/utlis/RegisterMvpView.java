package com.netflix.app.utlis;


import com.netflix.app.home.model.Userpojo;

public interface RegisterMvpView {
    void allreadyExits(String str);

    void registerUsers(Userpojo userpojo);
}
