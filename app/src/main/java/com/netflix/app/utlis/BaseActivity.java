package com.netflix.app.utlis;

import android.Manifest;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.snackbar.Snackbar;
import com.netflix.app.R;





public abstract class BaseActivity extends AppCompatActivity {

    private static final String TAG = "BaseActivity";

    /*TODO Create Sharedprefrence for Storing Data*/
    private SharedPreferences getPrefData(Context context) {
        return context.getSharedPreferences(getResources().getString(R.string.app_name), Context.MODE_PRIVATE);
    }

    /*TODO Get Data from Sharedprefrence*/
    public String getPref(Context context, String key) {
        return getPrefData(context).getString(key, "null");
    }

    /*TODO Set Data to Sharedprefrence*/
    public void setPref(Context context, String key, String value) {
        SharedPreferences.Editor editor = getPrefData(context).edit();
        editor.putString(key, value);
        editor.apply();
    }

    public void snackBar(View layout, String s) {
        Snackbar snackbar = Snackbar.make(layout, s.toUpperCase(), Snackbar.LENGTH_LONG);
        snackbar.show();
    }












}
