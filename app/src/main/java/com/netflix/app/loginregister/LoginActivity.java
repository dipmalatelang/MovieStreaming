package com.netflix.app.loginregister;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;


import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.GoogleApiClient;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthCredential;

import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.netflix.app.R;
import com.netflix.app.home.model.User;

import com.netflix.app.home.model.UserDetails;
import com.netflix.app.home.ui.Home_Activity;
import com.netflix.app.utlis.GlobalConstants;
import com.netflix.app.utlis.BaseActivity;

import com.netflix.app.utlis.LoginMvpView;
import com.netflix.app.utlis.LoginPresenter;

import java.text.DateFormat;
import java.util.Date;
import java.util.Objects;


public class LoginActivity extends BaseActivity implements View.OnClickListener, GoogleApiClient.OnConnectionFailedListener, LoginMvpView {
    private RelativeLayout ConstraintLayout;
    private EditText Et_Email, Et_Password;

    private String TAG = "AKAKAKA";
    private GoogleApiClient mGoogleApiClient;
    private FirebaseAuth mAuth;
    private LoginPresenter mPresenter;

    String currentDateTimeString = DateFormat.getDateTimeInstance().format(new Date());

//    private LoginBlutton fblogin;
//    private CallbackManager mCallbackManager;

    private static final int RC_SIGN_IN = 9001;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);
//        if (SharedPrefManager.getInstance(this).isLoggedIn()) {
//            startActivity(new Intent(this, LoginActivity.class));
//            finish();
//        }
        mPresenter = new LoginPresenter(this);
        ConstraintLayout = findViewById(R.id.ConstraintLayout);
        Et_Email = findViewById(R.id.Et_Email);
        Et_Password = findViewById(R.id.Et_Password);
        TextView tv_Signup = findViewById(R.id.Tv_Signup);
        Button btn_Login = findViewById(R.id.Btn_Login);
        TextView btn_gmail = findViewById(R.id.Btn_gmail);
        TextView btn_phone = findViewById(R.id.Btn_phone);
        TextView Btn_fb = findViewById(R.id.Btn_fb);
//        fblogin = findViewById(R.id.fblogin);
        TextView tv_ForgotPassword = findViewById(R.id.Tv_ForgotPassword);

        btn_gmail.setOnClickListener(this);

        mAuth = FirebaseAuth.getInstance();
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, (GoogleApiClient.OnConnectionFailedListener) this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();


        btn_gmail.setOnClickListener(this);
        btn_Login.setOnClickListener(this);
        btn_phone.setOnClickListener(this);
        Btn_fb.setOnClickListener(this);
        tv_ForgotPassword.setOnClickListener(this);
        tv_Signup.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.Btn_gmail:
                signIn();
                break;
            case R.id.Btn_Login:
                String email = Et_Email.getText().toString();
                String password = Et_Password.getText().toString();

                if (TextUtils.isEmpty(email)) {
                    Et_Email.setError(getString(R.string.field_can_not_be_empty_tag));
                } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    Et_Password.setError(getString(R.string.field_can_not_be_empty_tag));

                } else {
//                    showProgressDialog();
                    emailLogin(email, password);
                    mPresenter.LoginCredentials(email, password);
                    setPref(this, "username", email);

                }

                break;
            case R.id.Btn_phone:
                startActivity(new Intent(LoginActivity.this, PhoneActivity.class));
                break;
            case R.id.Tv_ForgotPassword:
//                startActivity(new Intent(LoginActivity.this, ResetPasswordActivity.class));
                break;
            case R.id.Tv_Signup:
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
                break;
        }
    }


    /*TODO Google Login*/
    private void signIn() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    /*TODO Login with Email*/

    private void emailLogin(final String txt_email, final String txt_password) {

        mAuth.signInWithEmailAndPassword(txt_email, txt_password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                            FirebaseUser firebaseUser = mAuth.getCurrentUser();
                            updateUI(firebaseUser);
                            if (firebaseUser != null) {
                                saveLoginDetails(txt_email, txt_password);
                                startActivity(new Intent(LoginActivity.this, Home_Activity.class));
                            }
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(LoginActivity.this, "" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();

                            updateUI(null);
                        }

                        // ...
                    }
                });

    }

    /*TODO Check User Status*/
    private void updateUI(FirebaseUser currentUser) {
        if (currentUser != null) {
            showProgressDialog();
            retrieveUserDetail(currentUser);
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        /*TODO Check if user is already Logged IN*/
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop: login");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "onRestart: resTART");
    }

    /*TODO Gmail Sign In*/
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                GoogleSignInAccount account = task.getResult(ApiException.class);
                if (account != null) {
                    Log.d("GALALAL", "" + account.getDisplayName());
                    firebaseAuthWithGoogle(account);

                } else {
                    snackBar(ConstraintLayout, "Google sign in Failed");
                }

            } catch (ApiException e) {
                snackBar(ConstraintLayout, "Google sign in failed");
                Log.w(TAG, "Google sign in failed", e);
            }
        }


    }


    private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {
        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {

//                        dismissProgressDialog();
                        updateUI(mAuth.getCurrentUser());
                        FirebaseUser fuser = mAuth.getCurrentUser();
                        String userid = fuser.getUid();
                        if (fuser != null) {
                            User user = new User(fuser.getUid(), fuser.getDisplayName(), fuser.getEmail().toLowerCase(), fuser.getEmail(), fuser.getDisplayName().toLowerCase(), fuser.getPhoneNumber(), fuser.getProviderId());
                            UsersInstance.child(userid).setValue(user);
                            Log.d(TAG, "firebaseAuthWithGooglevalue: " + fuser.getUid());
                            updateUI(fuser);

                            setPref(this, "username", fuser.getDisplayName());
                            setPref(this, "LoginSuccess", "true");

                            startActivity(new Intent(this, Home_Activity.class));
                            this.finish();
                        }

                    } else {
                        snackBar(ConstraintLayout, "Sign In Failed");
                        Log.w(TAG, "signInWithCredential:failure", task.getException());
                    }

                });
    }


    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        snackBar(ConstraintLayout, "Connection Failed");
        Log.d(TAG, "onConnectionFailed: " + connectionResult);
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }


    public void loginSuccess(User user) {
        toggleProgress(false);
        if (user != null) {
//            DeviceUtils.showToastMessage(this, getString(R.string.login_successful_tag));
            Intent i = new Intent(LoginActivity.this, Home_Activity.class);
            startActivity(i);
            finish();
            return;
        }
//        DeviceUtils.showToastMessage(this, getString(R.string.login_unsuccessful_tag));
    }

}
