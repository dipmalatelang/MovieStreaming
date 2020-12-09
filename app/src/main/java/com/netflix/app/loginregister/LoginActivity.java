package com.netflix.app.loginregister;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
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
import androidx.core.content.ContextCompat;


import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.GoogleApiClient;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.netflix.app.R;
import com.netflix.app.home.model.User;

import com.netflix.app.home.ui.Home_Activity;
import com.netflix.app.utlis.BaseActivity;

import com.netflix.app.utlis.LoginMvpView;
import com.netflix.app.utlis.LoginPresenter;
import com.netflix.app.utlis.SharedPrefManager;


public class LoginActivity extends BaseActivity implements View.OnClickListener, GoogleApiClient.OnConnectionFailedListener, LoginMvpView {
    private RelativeLayout ConstraintLayout;
    private EditText Et_Email, Et_Password;
    private String TAG = "AKAKAKA";
    private GoogleApiClient mGoogleApiClient;
    private FirebaseAuth mAuth;
    private LoginPresenter mPresenter;

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


        //fb login code

//        mCallbackManager = CallbackManager.Factory.create();
//        fblogin.setReadPermissions("email", "public_profile");
//        fblogin.registerCallback(mCallbackManager, new FacebookCallback<LoginResult>() {
//            @Override
//            public void onSuccess(LoginResult loginResult) {
//                handleFacebookAccessToken(loginResult.getAccessToken());
//            }
//
//            @Override
//            public void onCancel() {
//                Log.d("Cancel", "facebook:onCancel");
//            }
//
//            @Override
//            public void onError(FacebookException error) {
////                Toasty.error(LoginActivity.this, "" + error, Toasty.LENGTH_LONG).show();
//            }
//        });


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
//                showProgressDialog();
                signIn();
                break;

            case R.id.Btn_fb:
//                fblogin.performClick();
                break;

            case R.id.Btn_Login:
                String email = Et_Email.getText().toString();
                String password = Et_Password.getText().toString();

                if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
                    snackBar(ConstraintLayout, "All fields are required !");
                } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    snackBar(ConstraintLayout, "enter valid email address");
                } else {
                    showProgressDialog();
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
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        dismissProgressDialog();
                        updateUI(mAuth.getCurrentUser());
                        FirebaseUser firebaseUser = mAuth.getCurrentUser();
                        if (firebaseUser != null) {
                            saveLoginDetails(txt_email, txt_password);
                        }
                    } else {
                        if (task.getException() != null) {
                            snackBar(ConstraintLayout, "" + task.getException().getMessage());
                        } else {
                            snackBar(ConstraintLayout, "Login Failed");
                        }
                        dismissProgressDialog();
                    }
                });
    }

    /*TODO Check User Status*/
    public void updateUI(FirebaseUser currentUser) {
        if (currentUser != null) {
//            showProgressDialog();
            retriveUserDetails(currentUser);
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        /*TODO Check if user is already Logged IN*/
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
    }

    /*TODO Gmail Sign In*/
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

//        mCallbackManager.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                GoogleSignInAccount account = task.getResult(ApiException.class);
                if (account != null) {
                    Log.d("GALALAL",""+account.getDisplayName());
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

//    private void handleFacebookAccessToken(AccessToken token) {
//        showProgressDialog();
//        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
//        Log.d("Tiger", "" + credential);
//        mAuth.signInWithCredential(credential)
//                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
//                    @Override
//                    public void onComplete(@NonNull Task<AuthResult> task) {
//                        Log.d("Tiger", "handleFacebookAccessToken:" + task.isSuccessful());
//                        if (task.isSuccessful()) {
//                            FirebaseUser fuser = mAuth.getCurrentUser();
//                            if (fuser != null) {
//                                User user = new User(fuser.getUid(), fuser.getDisplayName(), fuser.getEmail().toLowerCase(), fuser.getEmail(), fuser.getDisplayName().toLowerCase(), fuser.getPhoneNumber(), fuser.getProviderId());
//                                UserInstance.child(fuser.getUid()).setValue(user);
//                                updateUI(fuser);
//                                dismissProgressDialog();
//                            }
//                        } else {
//                            dismissProgressDialog();
//                            LoginManager.getInstance().logOut();
//                            snackBar(ConstraintLayout, "" + task.getException().getMessage());
//                            Log.d("AGAGAGAG", "" + task.getException().getMessage());
//                        }
//                    }
//                });
//    }

    /*TODO Gmail Login*/
//    private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {
//        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
//        mAuth.signInWithCredential(credential)
//                .addOnCompleteListener(this, task -> {
//                    if (task.isSuccessful()) {
//                        Log.d(TAG, "createUserWithEmail:success");
//                        FirebaseUser fuser = mAuth.getCurrentUser();
//                        if (fuser != null) {
//                            User user = new User(fuser.getUid(), fuser.getDisplayName(), fuser.getEmail().toLowerCase(), fuser.getEmail(), fuser.getDisplayName().toLowerCase(), fuser.getPhoneNumber(), fuser.getProviderId());
//                            UserInstance.child(fuser.getUid()).setValue(user);
//                            updateUI(fuser);
//                            dismissProgressDialog();
//                        }
//
//                    } else {
//                        dismissProgressDialog();
//                        snackBar(ConstraintLayout, "Sign In Failed");
//                        Log.w(TAG, "signInWithCredential:failure", task.getException());
//                        updateUI(null);
//                    }
//
//                });
//    }



    private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {
        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        FirebaseUser fuser = mAuth.getCurrentUser();
                        if (fuser != null) {
                            User user = new User(fuser.getUid(), fuser.getDisplayName(), fuser.getEmail().toLowerCase(), fuser.getEmail(), fuser.getDisplayName().toLowerCase(), fuser.getPhoneNumber(), fuser.getProviderId());
                            UserInstance.child(fuser.getUid()).setValue(user);
                            updateUI(fuser);
//                            if (!getPref(this,"username").equalsIgnoreCase(fuser.getDisplayName())){
//                                setPref(this,"firstinstall","null");
//                            }
                            setPref(this, "username", fuser.getDisplayName());
                            setPref(this,"LoginSuccess","true");

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

    @Override
    public void loginSuccess(User usersResponse) {
        dismissProgressDialog();
        if (usersResponse != null) {
            startActivity(new Intent(getApplicationContext(), Home_Activity.class));
            finish();
            return;
       }
    }
}
