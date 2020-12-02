package com.netflix.app.auth;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.google.android.material.textfield.TextInputLayout;
import com.netflix.app.R;
import com.netflix.app.utlis.BaseActivity;
import com.netflix.app.utlis.DeviceUtils;

import java.util.Objects;


public class SetPasswordActivity extends BaseActivity {
    private TextInputLayout etcpass;
    private TextInputLayout etpass;
    private Button updateBtn;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) R.layout.activity_set_password);
        this.etpass = (TextInputLayout) findViewById(R.id.newpass);
        this.etcpass = (TextInputLayout) findViewById(R.id.cfpass);
        Button button = (Button) findViewById(R.id.updatebtn);
        this.updateBtn = button;
        button.setOnClickListener(new View.OnClickListener() {
            public final void onClick(View view) {
                SetPasswordActivity.this.lambda$onCreate$0$SetPasswordActivity(view);
            }
        });
    }

    @SuppressLint("WrongConstant")
    public  void lambda$onCreate$0$SetPasswordActivity(View v) {
        if (!validation()) {
            return;
        }
        if (this.etpass.getEditText().getText().toString().trim() == this.etcpass.getEditText().getText().toString().trim()) {
            Intent intent = new Intent(this, UpdatePasswordShowScreen.class);
            intent.addFlags(67141632);
            startActivity(intent);
            return;
        }
        DeviceUtils.showToastMessage(this, getString(R.string.pass_not_match));
    }

    private boolean validation() {
        if (((EditText) Objects.requireNonNull(this.etpass.getEditText())).getText().toString().trim().isEmpty()) {
            this.etpass.setError(getString(R.string.field_can_not_be_empty_tag));
            return false;
        } else if (!((EditText) Objects.requireNonNull(this.etcpass.getEditText())).getText().toString().trim().isEmpty()) {
            return true;
        } else {
            this.etcpass.setError(getString(R.string.field_can_not_be_empty_tag));
            return false;
        }
    }
}
