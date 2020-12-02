package com.netflix.app.utlis;

import android.content.Context;
import android.content.SharedPreferences;
import com.google.gson.Gson;
import com.netflix.app.home.model.User;


public class SharedPrefManager {
    private static final String KEY_USER_ID = "id";
    private static final String SHARED_PREF_NAME = "ottplay";
    private static Context mCtx;
    private static SharedPrefManager mInstance;

    private SharedPrefManager(Context context) {
        mCtx = context;
    }

    public static synchronized SharedPrefManager getInstance(Context context) {
        SharedPrefManager sharedPrefManager;
        synchronized (SharedPrefManager.class) {
            if (mInstance == null) {
                mInstance = new SharedPrefManager(context);
            }
            sharedPrefManager = mInstance;
        }
        return sharedPrefManager;
    }

    public boolean update(User user) {
        SharedPreferences.Editor editor = mCtx.getSharedPreferences(SHARED_PREF_NAME, 0).edit();
        editor.putString("id", new Gson().toJson((Object) user));
        editor.commit();
        return true;
    }

    public boolean userLogin(User user) {
        SharedPreferences.Editor editor = mCtx.getSharedPreferences(SHARED_PREF_NAME, 0).edit();
        editor.putString("id", new Gson().toJson((Object) user));
        editor.apply();
        return true;
    }

    public boolean isLoggedIn() {
        if (mCtx.getSharedPreferences(SHARED_PREF_NAME, 0).getString("id", (String) null) != null) {
            return true;
        }
        return false;
    }

    public User getUser() {
        return (User) new Gson().fromJson(mCtx.getSharedPreferences(SHARED_PREF_NAME, 0).getString("id", (String) null), User.class);
    }

    public boolean logout() {
        SharedPreferences.Editor editor = mCtx.getSharedPreferences(SHARED_PREF_NAME, 0).edit();
        editor.clear();
        editor.apply();
        return true;
    }
}
