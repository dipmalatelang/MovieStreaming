package com.netflix.app.loginregister;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.netflix.app.R;
import com.netflix.app.home.model.User;
import com.netflix.app.home.ui.Home_Activity;
import com.netflix.app.main.MainActivity;
import com.netflix.app.utlis.BaseActivity;
import com.netflix.app.utlis.RegisterMvpView;
import com.netflix.app.utlis.RegisterPresenter;
import com.netflix.app.utlis.SharedPrefManager;


import org.jetbrains.annotations.NotNull;


public class RegisterActivity extends BaseActivity implements RegisterMvpView {
    private static final String TAG = "";
    private EditText Et_Name, Et_Email, Et_Password;
    private Button Btn_Signup;
    private String username, email, password;
    private FirebaseAuth mAuth;
    private RelativeLayout ConstraintLayout;

    private RegisterPresenter mPresenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_register);
        Et_Name = findViewById(R.id.Et_Name);
        Et_Email = findViewById(R.id.Et_Email);
        Et_Password = findViewById(R.id.Et_Password);
        Button btn_Signup = findViewById(R.id.Btn_Signup);
        ConstraintLayout = findViewById(R.id.ConstraintLayout);
        this.mPresenter = new RegisterPresenter(this);

        mAuth = FirebaseAuth.getInstance();
//        mregisterViewModel = ViewModelProviders.of(this).get(RegisterViewModel.class);
//        mregisterViewModel.init();
//        mregisterViewModel.getUserData().observe(this, new Observer<UsersResponse>() {
//            @Override
//            public void onChanged(UsersResponse usersResponse) {
//
//            }
//        });


        btn_Signup.setOnClickListener(v -> {
//            if (PackageManager.PERMISSION_GRANTED == ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS)
//                    && PackageManager.PERMISSION_GRANTED == ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE))
//            {
            username = Et_Name.getText().toString();
            email = Et_Email.getText().toString();
            password = Et_Password.getText().toString();


            if(TextUtils.isEmpty(username)){
                Et_Name.setError(getString(R.string.field_can_not_be_empty_tag));
            }
            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                Et_Email.setError(getString(R.string.field_can_not_be_empty_tag));
//                snackBar(ConstraintLayout, "please enter valid email address");
            } else if (password.length() < 6) {
                Et_Password.setError(getString(R.string.field_can_not_be_empty_tag));
//                snackBar(ConstraintLayout, "password must be at least 6 characters");
            }
            else {
                registration(username, email, password);
                User user = new User("", username, email, password, "mobile", "", "", "", "", null, "BASIC");
                mPresenter.adduser(user);

                Log.i(TAG, "onCreateasasaaaaaa: " + user.getEmail());

            }
        });
    }

    private void registration(final String username, final String email, String password) {

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NotNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            snackBar(ConstraintLayout, "Register Successfully..!");
                            FirebaseUser firebaseUser = mAuth.getCurrentUser();
                            if (firebaseUser != null) {
                                String userid = firebaseUser.getUid();
                                User user = new User(firebaseUser.getUid(), username, email, username.toLowerCase(), "", "", "");
                                UsersInstance.child(userid).setValue(user);
                                startActivity(new Intent(RegisterActivity.this, Home_Activity.class));
                            }

                        } else {
                            if (task.getException() != null) {
                                snackBar(ConstraintLayout, "" + task.getException().getMessage());
                            } else {
                                snackBar(ConstraintLayout, "Registeration Failed");
                            }
//                            dismissProgressDialog();
                        }

                    }

                });
    }


    @Override
    public void allreadyExits(String str) {
//        Toast.makeText(this, "User Already Exits", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(getApplicationContext(), Home_Activity.class));


    }


    @Override
    public void registerUsers(User user) {

        Toast.makeText(this, "successfully", Toast.LENGTH_SHORT).show();
//        dismissProgressDialog();
        SharedPrefManager.getInstance(getApplicationContext()).userLogin(user);
//        DeviceUtils.showToastMessage(this, getString(R.string.register_successful_tag));
        startActivity(new Intent(getApplicationContext(), Home_Activity.class));
        finish();
//        dismissProgressDialog();
        if (user != null) {
            SharedPrefManager.getInstance(getApplicationContext()).userLogin(user);
//            DeviceUtils.showToastMessage(this, getString(C1734R.string.register_successful_tag));
            startActivity(new Intent(getApplicationContext(), Home_Activity.class));
            finish();
            return;
        }
//        DeviceUtils.showToastMessage(this, getString(C1734R.string.login_unsuccessful_tag));


    }

//    public void allreadyExits(String toString) {
//        Log.d(TAG, "allreadyExits: "+getString(R.string.mobile_number_exits));
////        this.progressBar.setVisibility(8);
////        Toast.makeText(this, getString(R.string.app_name), Toast.LENGTH_SHORT).show();
//    }
}





