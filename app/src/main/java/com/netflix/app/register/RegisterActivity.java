package com.netflix.app.register;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import com.google.android.material.textfield.TextInputLayout;
import com.netflix.app.R;
import com.netflix.app.home.model.User;
import com.netflix.app.home.model.UserPayment;
import com.netflix.app.home.ui.Home_Activity;
import com.netflix.app.utlis.BaseActivity;
import com.netflix.app.utlis.DeviceUtils;
import com.netflix.app.utlis.SharedPrefManager;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;


public class RegisterActivity extends BaseActivity implements RegisterMvpView {
    private TextInputLayout et_email;
    private TextInputLayout et_name;
    private TextInputLayout et_pass;
    private EditText et_phone;
    private RegisterPresenter mPresenter;
    private ProgressBar progressBar;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) R.layout.activity_register);
        this.mPresenter = new RegisterPresenter(this);
        String mobile = getIntent().getStringExtra("mobile");
        this.et_name = (TextInputLayout) findViewById(R.id.fullname);
        this.et_email = (TextInputLayout) findViewById(R.id.email);
        this.et_pass = (TextInputLayout) findViewById(R.id.password);
        this.et_phone = (EditText) findViewById(R.id.number);
        this.progressBar = (ProgressBar) findViewById(R.id.progressBar);
        this.et_phone.setText(mobile);
        ((Button) findViewById(R.id.registerBtn)).setOnClickListener(new View.OnClickListener() {
//            public final /* synthetic */ String f$1;
//
//            {
//                this.f$1 = r2;
//            }

            public final void onClick(View view) {
                RegisterActivity.this.lambda$onCreate$0$RegisterActivity(mobile, view);
            }
        });
    }






    public /* synthetic */ void lambda$onCreate$0$RegisterActivity(String mobile, View v) {
        if (isValidation()) {
            this.progressBar.setVisibility(View.INVISIBLE);
            User user = new User(String.valueOf((int) (((Math.random() * 90.0d) + 1000.0d) / 1000.0d)), ((EditText) Objects.requireNonNull(this.et_name.getEditText())).getText().toString(), ((EditText) Objects.requireNonNull(this.et_email.getEditText())).getText().toString(), ((EditText) Objects.requireNonNull(this.et_pass.getEditText())).getText().toString(), mobile, "", new SimpleDateFormat("yyyy-MM-dd").format(new Date()).toString(), new SimpleDateFormat("yyyy-MM-dd").format(new Date()).toString(), new SimpleDateFormat("yyyy-MM-dd").format(new Date()).toString(), null, "BASIC", "");
            System.out.println(user.toString());
            this.mPresenter.addUserAccount(user);
        }
    }

    public void loginPage(View view) {
        finish();
    }

    private boolean isValidation() {
        if (((EditText) Objects.requireNonNull(this.et_name.getEditText())).getText().toString().trim().isEmpty()) {
            this.et_name.setError(getString(R.string.field_can_not_be_empty_tag));
            this.progressBar.setVisibility(View.GONE);
            return false;
        } else if (((EditText) Objects.requireNonNull(this.et_pass.getEditText())).getText().toString().trim().isEmpty()) {
            this.et_pass.setError(getString(R.string.field_can_not_be_empty_tag));
            this.progressBar.setVisibility(View.GONE);
            this.et_name.setError((CharSequence) null);
            this.et_name.setErrorEnabled(false);
            return false;
        } else if (((EditText) Objects.requireNonNull(this.et_email.getEditText())).getText().toString().trim().isEmpty()) {
            this.et_email.setError(getString(R.string.field_can_not_be_empty_tag));
            this.progressBar.setVisibility(View.GONE);
            return false;
        } else {
            this.et_pass.setError((CharSequence) null);
            this.et_pass.setErrorEnabled(false);
            return true;
        }
    }



    @Override
    public void registerUsers(User user) {
        System.out.println(user.toString());
        this.progressBar.setVisibility(View.GONE);
        SharedPrefManager.getInstance(getApplicationContext()).userLogin(user);
        DeviceUtils.showToastMessage(this, getString(R.string.register_successful_tag));
        startActivity(new Intent(getApplicationContext(), Home_Activity.class));
        finish();
        this.progressBar.setVisibility(View.VISIBLE);
        if (user != null) {
            SharedPrefManager.getInstance(getApplicationContext()).userLogin(user);
            DeviceUtils.showToastMessage(this, getString(R.string.register_successful_tag));
            startActivity(new Intent(getApplicationContext(), Home_Activity.class));
            finish();
            return;
        }
        DeviceUtils.showToastMessage(this, getString(R.string.login_unsuccessful_tag));
    }

    public void allreadyExits(String toString) {
        DeviceUtils.showToastMessage(this, getString(R.string.mobile_number_exits));
        this.progressBar.setVisibility(View.GONE);

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
