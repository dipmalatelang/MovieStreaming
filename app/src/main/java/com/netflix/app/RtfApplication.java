package com.netflix.app;

import android.app.Application;

import com.netflix.app.utlis.SharedPrefs;

public class RtfApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        SharedPrefs.initialize(this);
    }
}
