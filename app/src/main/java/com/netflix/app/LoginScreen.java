package com.netflix.app;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;

import com.google.android.material.textfield.TextInputLayout;
import com.netflix.app.auth.ForgotPasswordActivity;
import com.netflix.app.home.model.User;
import com.netflix.app.home.ui.Home_Activity;
import com.netflix.app.utlis.BaseActivity;
import com.netflix.app.utlis.DeviceUtils;
import com.netflix.app.utlis.RetrofitClient;
import com.netflix.app.utlis.SharedPrefManager;

import java.io.PrintStream;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LoginScreen extends BaseActivity {
    private TextInputLayout et_email;
    private TextInputLayout et_password;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) R.layout.activity_login);
        if (SharedPrefManager.getInstance(this).isLoggedIn()) {
            startActivity(new Intent(this, Home_Activity.class));
            finish();
        }
        this.et_email = (TextInputLayout) findViewById(R.id.username);
        this.et_password = (TextInputLayout) findViewById(R.id.password);
        Button button = (Button) findViewById(R.id.login_button);

        button.setOnClickListener(new View.OnClickListener() {
            public final void onClick(View view) {
                LoginScreen.this.lambda$onCreate$0$LoginScreen(view);
            }
        });
    }

    public /* synthetic */ void lambda$onCreate$0$LoginScreen(View view) {
        if (DeviceUtils.isNetworkAvailable(this)) {
            userLogin();
        } else {
            showNetwork();
        }
    }

    private void showNetwork() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Please connect to the internet to proceed future").setCancelable(false).setPositiveButton("Connect", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                LoginScreen.this.startActivity(new Intent("android.settings.WIFI_SETTINGS"));
            }
        }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                LoginScreen.this.finish();
            }
        });
        builder.create().show();
    }

    private void userLogin() {
        toggleProgress(true);
        String email = this.et_email.getEditText().getText().toString().trim();
        String password = this.et_password.getEditText().getText().toString().trim();
        if (TextUtils.isEmpty(email)) {
            this.et_email.setError(getString(R.string.field_can_not_be_empty_tag));
            this.et_password.setError((CharSequence) null);
            toggleProgress(false);
        } else if (TextUtils.isEmpty(password)) {
            this.et_password.setError(getString(R.string.field_can_not_be_empty_tag));
            this.et_email.setError((CharSequence) null);
            toggleProgress(false);
        } else {
            this.et_password.setError((CharSequence) null);
            RetrofitClient.getInstance().getApi().checkLoginCredentials(email, password).enqueue(new Callback<User>() {
                public void onResponse(Call<User> call, Response<User> response) {
                    try {
                        PrintStream printStream = System.out;
                        printStream.println("login user" + response);
                        LoginScreen.this.toggleProgress(false);
                        User user = response.body();
                        PrintStream printStream2 = System.out;
                        printStream2.println("hey" + user.getId());
                        if (user != null) {
                            SharedPrefManager.getInstance(LoginScreen.this.getApplicationContext()).userLogin(user);
                            Intent i = new Intent(LoginScreen.this,Home_Activity.class);
                            startActivity(i);
                            LoginScreen.this.finish();
                            DeviceUtils.showToastMessage(LoginScreen.this, LoginScreen.this.getString(R.string.login_successful_tag));
                            return;
                        }
                        DeviceUtils.showToastMessage(LoginScreen.this, LoginScreen.this.getString(R.string.login_unsuccessful_tag));
                    } catch (Exception e) {
                        System.out.println(response.message());
                        LoginScreen.this.toggleProgress(false);
                    }
                }

                public void onFailure(Call<User> call, Throwable t) {
                    LoginScreen.this.toggleProgress(false);
                    DeviceUtils.showToastMessage(LoginScreen.this.getApplicationContext(), LoginScreen.this.getString(R.string.login_unsuccessful_tag));
                }
            });
        }
    }

    public void homePage(View view) {
        Intent i = new Intent(LoginScreen.this,Home_Activity.class);
        startActivity(i);

        finish();
    }

    public void registerPage(View view) {
        Intent i = new Intent(LoginScreen.this,ForgotPasswordActivity.class);
        startActivity(i);
    }
}
