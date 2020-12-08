package com.netflix.app.loginregister;


import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.service.autofill.UserData;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;


import com.chaos.view.PinView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.netflix.app.R;
import com.netflix.app.home.model.User;
import com.netflix.app.home.ui.Home_Activity;
import com.netflix.app.utlis.BaseActivity;

import java.util.concurrent.TimeUnit;


public class VerifyPhoneActivity extends BaseActivity {


    Button buttonSignIn;
    ConstraintLayout clVerify;
    PinView pinView;
    TextView resendCode;
    TextView tvcountDown;
    private FirebaseAuth mAuth;
    private FirebaseUser fuser;
    private String mVerificationId;

    String mobile, mobileCode;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_phone);

        tvcountDown = findViewById(R.id.tvcountDown);
        resendCode = findViewById(R.id.resendCode);
        pinView = findViewById(R.id.pinView);

        //initializing objects
        fuser = FirebaseAuth.getInstance().getCurrentUser();
        mAuth = FirebaseAuth.getInstance();
        Intent intent = getIntent();
        mobile = intent.getStringExtra("mobile");
        mobileCode = intent.getStringExtra("mobileCode");
        sendVerificationCode(mobile, mobileCode);
        startCounter();


        buttonSignIn = findViewById(R.id.buttonSignIn);
        buttonSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String code = pinView.getText().toString().trim();
                if (code.isEmpty() || code.length() < 6) {
                    pinView.setError("Enter valid code");
                    pinView.requestFocus();
                    return;
                }
                verifyVerificationCode(code);
            }
        });
        resendCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendVerificationCode(mobile, mobileCode);
                startCounter();
            }
        });

    }


    private void startCounter() {
        new CountDownTimer(21000, 1000) {

            public void onTick(long millisUntilFinished) {
                tvcountDown.setVisibility(View.VISIBLE);
                resendCode.setVisibility(View.GONE);
                tvcountDown.setText("seconds remaining: " + millisUntilFinished / 1000);
                //here you can have your logic to set text to edittext
            }

            public void onFinish() {
                tvcountDown.setVisibility(View.GONE);
                resendCode.setVisibility(View.VISIBLE);
            }

        }.start();
    }


    private void sendVerificationCode(String mobile, String mobileCode) {
        PhoneAuthOptions options =
                PhoneAuthOptions.newBuilder(mAuth)
                        .setPhoneNumber(mobileCode + mobile)       // Phone number to verify
                        .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                        .setActivity(this)                 // Activity (for callback binding)
                        .setCallbacks(mCallbacks)          // OnVerificationStateChangedCallbacks
                        .build();
        PhoneAuthProvider.verifyPhoneNumber(options);

    }


    //the callback to detect the verification status
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
        @Override
        public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {

            //Getting the code sent by SMS
            String code = phoneAuthCredential.getSmsCode();

            //sometime the code is not detected automatically
            //in this case the code will be null
            //so user has to manually enter the code
            if (code != null) {
                pinView.setText(code);
                //verifying the code
                verifyVerificationCode(code);
            }
        }

        @Override
        public void onVerificationFailed(FirebaseException e) {
            Toast.makeText(VerifyPhoneActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
        }

        @Override
        public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
            super.onCodeSent(s, forceResendingToken);

            //storing the verification id that is sent to the user
            mVerificationId = s;
        }
    };


    private void verifyVerificationCode(String code) {
        //creating the credential
        showProgressDialog();
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(mVerificationId, code);

        signInWithPhoneAuthCredential(credential);
    }

    private void updateUI(FirebaseUser currentUser) {
        if (currentUser != null) {
            retriveUserDetails(currentUser);

        }
    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        if (fuser != null) {
            Log.i("TAG", "signInWithPhoneAuthCredential: if");
            fuser.linkWithCredential(credential)
                    .addOnCompleteListener(VerifyPhoneActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                //verification successful we will start the profile activity
                                FirebaseUser user = task.getResult().getUser();
                                setPhoneNumber(fuser.getUid(), mobile, mobileCode);
                                updateUI(user);


                            } else {
                                //verification unsuccessful.. display an error message
                                Log.i("TAG", "signInWithPhoneAuthCredential: if else");
                                String message = "Somthing is wrong, we will fix it soon...";

                                if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                    message = "Invalid code entered...";
                                }
                                Log.i("TAG", "onComplete: " + fuser.getUid());
                                updateUI(null);

                            }
                        }
                    });
        } else {
            Log.i("TAG", "signInWithPhoneAuthCredential: else");
            phoneLogin(credential);
        }

    }

    private void phoneLogin(PhoneAuthCredential credential) {
        Log.i("", "phoneLogin: ");
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(VerifyPhoneActivity.this, task -> {
                    if (task.isSuccessful()) {
                        FirebaseUser firebaseUser = mAuth.getCurrentUser();
                        if (firebaseUser != null){
                            Log.d("GAHHAA",""+mobile);
                            User user = new User(firebaseUser.getUid(), "Test", "", "", "", mobile, mobileCode);
                            UserInstance.child(firebaseUser.getUid()).setValue(user);
                            startActivity(new Intent(VerifyPhoneActivity.this, Home_Activity.class));
                            finishAffinity();
                        }

                        if (task.getResult() != null){
                            FirebaseUser user = task.getResult().getUser();

                            if (user != null){
                                UserInstance.child(user.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull com.google.firebase.database.DataSnapshot dataSnapshot) {
                                        if (!dataSnapshot.exists()) {

                                            updateUI(user);
                                        } else {
                                            updateUI(user);
                                        }
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError databaseError) {

                                    }
                                });
                            }
                        }



                    } else {
                        if (task.getException() != null){
//                            snackBar(Cl_Verify, ""+task.getException().getMessage());
                            Toast.makeText(VerifyPhoneActivity.this, "" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();

                        }
                    }
                });
    }}

//    private void phoneLogin(PhoneAuthCredential credential) {
//        Log.i("TAG", "phoneLogin: ");
//        mAuth.signInWithCredential(credential)
//                .addOnCompleteListener(VerifyPhoneActivity.this, new OnCompleteListener<AuthResult>() {
//                    @Override
//                    public void onComplete(@NonNull Task<AuthResult> task) {
//
//                        if (task.isSuccessful()) {
//                            Log.d("GAHHAA", "" + mobile);
//                            FirebaseUser firebaseUseruser = task.getResult().getUser();
//
//                            if (firebaseUseruser != null) {
//                                User user = new User(firebaseUseruser.getUid(), "Test", "", "", "", mobile, mobileCode);
//                                UserInstance.child(firebaseUseruser.getUid()).setValue(user);
//                                startActivity(new Intent(VerifyPhoneActivity.this, Home_Activity.class));
//                                finishAffinity();
//                            }
//                            if (task.getResult() != null) {
//                                FirebaseUser user = task.getResult().getUser();
//
//                                if (user != null) {
//                                    UserInstance.child(user.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
//                                        @Override
//                                        public void onDataChange(@NonNull com.google.firebase.database.DataSnapshot dataSnapshot) {
//                                            if (!dataSnapshot.exists()) {
//
//                                                updateUI(user);
//                                            } else {
//                                                updateUI(user);
//                                            }
//                                        }
//
//                                        @Override
//                                        public void onCancelled(@NonNull DatabaseError databaseError) {
//
//                                        }
//                                    });
//                                }
//                            }
//
//
//                        } else {
//                            if (task.getException() != null) {
//                                Toast.makeText(VerifyPhoneActivity.this, "" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
//                            }
//                        }
//                    });
//                }
//    }