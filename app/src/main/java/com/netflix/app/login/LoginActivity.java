package com.netflix.app.login;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;

import android.widget.Button;
import android.widget.EditText;

import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.netflix.app.R;
import com.netflix.app.auth.ForgotPasswordActivity;
import com.netflix.app.home.model.User;
import com.netflix.app.home.ui.Home_Activity;
import com.netflix.app.register.RegisterActivity;
import com.netflix.app.utlis.BaseActivity;
import com.netflix.app.utlis.DeviceUtils;
import com.netflix.app.utlis.SharedPrefManager;

import java.util.Objects;


public class LoginActivity extends BaseActivity implements LoginMvpView {
    private TextInputLayout et_password;
    private TextInputLayout et_username;
    private LoginPresenter mPresenter;


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) R.layout.activity_login);
        if (SharedPrefManager.getInstance(this).isLoggedIn()) {
            startActivity(new Intent(this, Home_Activity.class));
            finish();
        }
        this.mPresenter = new LoginPresenter(this);
        this.et_username = (TextInputLayout) findViewById(R.id.username);
        this.et_password = (TextInputLayout) findViewById(R.id.password);

        ((Button) findViewById(R.id.login_button)).setOnClickListener(new View.OnClickListener() {
            public final void onClick(View view) {
                lambda$onCreate$0$LoginActivity(view);
            }
        });
    }

    public void lambda$onCreate$0$LoginActivity(View v) {
        if (isValidation()) {
            toggleProgress(true);
           mPresenter.checkLoginCredentials(((EditText)
                   Objects.requireNonNull(this.et_username.getEditText()))
                   .getText().toString(), ((EditText) Objects.requireNonNull
                   (this.et_password.getEditText())).getText().toString());
        }
    }

    public void registerPage(View view) {
        startActivity(new Intent(this, ForgotPasswordActivity.class));
        Toast.makeText(this, "click regi", Toast.LENGTH_SHORT).show();
    }

    private boolean isValidation() {
        if (((EditText) Objects.requireNonNull(this.et_username.getEditText())).getText().toString().trim().isEmpty()) {
            this.et_username.setError(getString(R.string.field_can_not_be_empty_tag));
            return false;
        } else if (((EditText) Objects.requireNonNull(this.et_password.getEditText())).getText().toString().trim().isEmpty()) {
            this.et_password.setError(getString(R.string.field_can_not_be_empty_tag));
            this.et_username.setError((CharSequence) null);
            this.et_username.setErrorEnabled(false);
            return false;
        } else {
            this.et_password.setError((CharSequence) null);
            this.et_password.setErrorEnabled(false);
            return true;
        }
    }

    public void loginSuccess(User user) {
        toggleProgress(false);
        if (user != null) {
            DeviceUtils.showToastMessage(this, getString(R.string.login_successful_tag));

            Intent i = new Intent(LoginActivity.this,Home_Activity.class);
            startActivity(i);
            finish();
            return;
        }
        DeviceUtils.showToastMessage(this, getString(R.string.login_unsuccessful_tag));
    }

    public void homePage(View view) {
        Intent i = new Intent(LoginActivity.this, Home_Activity.class);
        startActivity(i);
        finish();
    }
}
