package com.netflix.app.auth;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.exoplayer2.upstream.DefaultLoadErrorHandlingPolicy;
import com.google.android.gms.tasks.OnCompleteListener;

import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskExecutors;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.netflix.app.R;
import com.netflix.app.register.RegisterActivity;
import com.netflix.app.utlis.BaseActivity;

import java.util.Locale;
import java.util.concurrent.TimeUnit;


public class OtpVerificationActivity extends BaseActivity {
    private CountDownTimer countDownTimer;

    public EditText editTextCode;
    private FirebaseAuth mAuth;
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
        public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
            String code = phoneAuthCredential.getSmsCode();
            OtpVerificationActivity.this.progressBar.setVisibility(View.GONE);
            if (code != null) {
                OtpVerificationActivity.this.editTextCode.setText(code);
                OtpVerificationActivity.this.verifyVerificationCode(code);
                OtpVerificationActivity.this.verifyBtn.setEnabled(false);
            }
        }

        public void onVerificationFailed(FirebaseException e) {
            Toast.makeText(OtpVerificationActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
        }

        public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
            super.onCodeSent(s, forceResendingToken);
            String unused = OtpVerificationActivity.this.mVerificationId = s;
        }
    };
    /* access modifiers changed from: private */
    public String mVerificationId;
    /* access modifiers changed from: private */
    public TextView phoneNum;
    protected ProgressBar progressBar;
    /* access modifiers changed from: private */
    public Button resendOtp;
    private PhoneAuthProvider.ForceResendingToken resendToken;
    /* access modifiers changed from: private */
    public TextView tv_coundown;
    /* access modifiers changed from: private */
    public Button verifyBtn;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) R.layout.activity_otp_verification);
//        getSupportActionBar(View.GONE);
        this.mAuth = FirebaseAuth.getInstance();
        this.phoneNum = (TextView) findViewById(R.id.number);
        this.editTextCode = (EditText) findViewById(R.id.otp);
        this.verifyBtn = (Button) findViewById(R.id.veriftbtn);
        this.progressBar = (ProgressBar) findViewById(R.id.progressBar_main);
        this.resendOtp = (Button) findViewById(R.id.resetBtn);
        this.tv_coundown = (TextView) findViewById(R.id.tv_coundown);
        countDownTimer();
        String mobile = getIntent().getStringExtra("mobile");
        this.phoneNum.setText(mobile);
        sendVerificationCode(mobile);
        this.verifyBtn.setOnClickListener(new View.OnClickListener() {
            public final void onClick(View view) {
                OtpVerificationActivity.this.OtpVerificationActivity(view);
            }
        });
//        this.resendOtp.setOnClickListener(new View.OnClickListener(mobile) {
//            public final  String f$1;
//
//            {
//                String r2 = null;
//                this.f$1 = r2;
//            }
//
//            public final void onClick(View view) {
//                OtpVerificationActivity.this.OtpVerificationActivity(this.f$1, view);
//            }
//        });
    }

    public  void OtpVerificationActivity(View v) {
        String code = this.editTextCode.getText().toString().trim();
        if (code.isEmpty() || code.length() < 6) {
            this.editTextCode.setError("Enter valid code");
            this.editTextCode.requestFocus();
            return;
        }
        verifyVerificationCode(code);
    }

    public void OtpVerificationActivity(String mobile, View view) {
        PhoneAuthProvider.getInstance().verifyPhoneNumber(mobile, 60, TimeUnit.SECONDS, (Activity) this, this.mCallbacks, this.resendToken);
    }

    private void countDownTimer() {
        CountDownTimer r0 = new CountDownTimer(DefaultLoadErrorHandlingPolicy.DEFAULT_TRACK_BLACKLIST_MS, 1000) {
            public void onTick(long l) {
                OtpVerificationActivity.this.tv_coundown.setText(String.format(Locale.getDefault(), "%02d:%02d", new Object[]{Long.valueOf(TimeUnit.MILLISECONDS.toMinutes(l) % 60), Long.valueOf(TimeUnit.MILLISECONDS.toSeconds(l) % 60)}));
            }

            public void onFinish() {
                OtpVerificationActivity.this.tv_coundown.setText("00:00");
                OtpVerificationActivity.this.resendOtp.setVisibility(View.VISIBLE);
                OtpVerificationActivity.this.tv_coundown.setVisibility(View.GONE);
            }
        };
        this.countDownTimer = r0;
        r0.start();
    }

    private void sendVerificationCode(String mobile) {
        PhoneAuthProvider.getInstance().verifyPhoneNumber(mobile, 60, TimeUnit.SECONDS, (Activity) TaskExecutors.MAIN_THREAD, this.mCallbacks);
    }

    /* access modifiers changed from: private */
    public void verifyVerificationCode(String code) {
        signInWithPhoneAuthCredential(PhoneAuthProvider.getCredential(this.mVerificationId, code));
    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential phoneAuthCredential) {
        this.mAuth.signInWithCredential(phoneAuthCredential).addOnCompleteListener((Activity) this, new OnCompleteListener<AuthResult>() {
            public void onComplete(Task<AuthResult> task) {
                OtpVerificationActivity.this.progressBar.setVisibility(View.GONE);
                if (task.isSuccessful()) {
                    OtpVerificationActivity.this.progressBar.setVisibility(View.VISIBLE);
                    Intent intent = new Intent(OtpVerificationActivity.this, RegisterActivity.class);
                    intent.putExtra("mobile", OtpVerificationActivity.this.phoneNum.getText().toString());
//                    intent.setFlags(268468224);
                    OtpVerificationActivity.this.startActivity(intent);
                    return;
                }
                OtpVerificationActivity.this.progressBar.setVisibility(View.GONE);
                String message = "Something is wrong, we will fix it soon...";
                if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                    message = "Invalid code entered...";
                }
                Snackbar snackbar = Snackbar.make(OtpVerificationActivity.this.findViewById(R.id.parent), (CharSequence) message, BaseTransientBottomBar.LENGTH_LONG);
                snackbar.setAction((CharSequence) "Dismiss", (View.OnClickListener) new View.OnClickListener() {
                    public void onClick(View v) {
                    }
                });
                snackbar.show();
            }
        });
    }
}
