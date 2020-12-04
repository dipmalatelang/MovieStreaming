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

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.netflix.app.R;
import com.netflix.app.home.model.UserPayment;
import com.netflix.app.home.ui.Home_Activity;
import com.netflix.app.main.MainActivity;
import com.netflix.app.utlis.BaseActivity;
import com.netflix.app.utlis.RegisterMvpView;
import com.netflix.app.utlis.RegisterPresenter;
import com.netflix.app.utlis.SharedPrefManager;

import java.text.SimpleDateFormat;
import java.util.Date;



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


        btn_Signup.setOnClickListener(v -> {
//            if (PackageManager.PERMISSION_GRANTED == ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS)
//                    && PackageManager.PERMISSION_GRANTED == ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE))
//            {
            username = Et_Name.getText().toString();
            email = Et_Email.getText().toString();
            password = Et_Password.getText().toString();

            if (TextUtils.isEmpty(username) || TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
                snackBar(ConstraintLayout, "All fileds are required");

            } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                snackBar(ConstraintLayout, "please enter valid email address");
            } else if (password.length() < 6) {

                snackBar(ConstraintLayout, "password must be at least 6 characters");
            } else {
                registration(username, email, password);
                com.netflix.app.home.model.User user = new com.netflix.app.home.model.User(String.valueOf((int) (((Math.random() * 90.0d) + 1000.0d) / 1000.0d)), username, email, password, "", "", new SimpleDateFormat("yyyy-MM-dd").format(new Date()).toString(), new SimpleDateFormat("yyyy-MM-dd").format(new Date()).toString(), new SimpleDateFormat("yyyy-MM-dd").format(new Date()).toString(), (UserPayment) null, "BASIC", "");
                mPresenter.addUserAccount(user);



            }


        });
    }

    private void registration(final String username, final String email, String password) {

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            snackBar(ConstraintLayout, "Register Successfully..!");
                            FirebaseUser firebaseUser = mAuth.getCurrentUser();
                            if (firebaseUser != null) {
                                String userid = firebaseUser.getUid();
                                User user = new User(firebaseUser.getUid(), username, email, username.toLowerCase(), "", "", "");
                                UserInstance.child(userid).setValue(user);
                                startActivity(new Intent(RegisterActivity.this, Home_Activity.class));
                                Log.d(TAG, "onComplete: homeactivity");
                            }

                        } else {
                            if (task.getException() != null) {
                                snackBar(ConstraintLayout, "" + task.getException().getMessage());
                            } else {
                                snackBar(ConstraintLayout, "Registeration Failed");
                            }
                            dismissProgressDialog();
                        }

                    }

                });
    }


//    private Data createInputData(String key, String value) {
//        return new Data.Builder().putString(key, value).build();
//    }

//    private final int MULTIPLE_PERMISSIONS = 10;

//    private void checkPermissions() {
//        int result;
//        List<String> listPermissionsNeeded = new ArrayList<>();
//        for (String p : permissions) {
//            result = ContextCompat.checkSelfPermission(this, p);
//            if (result != PackageManager.PERMISSION_GRANTED) {
//                listPermissionsNeeded.add(p);
//            }
//        }
//        if (listPermissionsNeeded.size() != 0) {
//            ActivityCompat.requestPermissions(this, listPermissionsNeeded.toArray(new String[0]), MULTIPLE_PERMISSIONS);
//        }
//    }

//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//
//        if (requestCode == MULTIPLE_PERMISSIONS) {
//            if (grantResults.length > 0) {
//                ArrayList<String> permissionsDenied = new ArrayList<>();
//                for (String per : permissions) {
//                    if (grantResults[0] == PackageManager.PERMISSION_DENIED) {
//                        permissionsDenied.add(per);
//                    }
//                }
//
//                if (permissionsDenied.size() > 0) {
//                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//                        if (shouldShowRequestPermissionRationale(Manifest.permission.READ_CONTACTS) || shouldShowRequestPermissionRationale(Manifest.permission.READ_PHONE_STATE)) {
//                            alertDialogPermission(true);
//                        } else {
//                            alertDialogPermission(false);
//                        }
//                    }
//                }
//            }
//
//        } else {
//            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        }
//    }

    private void alertDialogPermission(boolean check) {
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(this);
        builder.setTitle("Need Permission");
        builder.setIcon(R.mipmap.ic_launcher);
        builder.setCancelable(false);

        if (check) {
            builder.setMessage("Please Allow Permission,\nWhich will help us to Improve your App Experience");
            builder.setPositiveButton("Grant", (dialog, id) -> {

                dialog.cancel();
            });
        } else {
            builder.setMessage("App Need Contact Permission,\nGrant Permission in Setting➟Permissions");
            builder.setPositiveButton("Grant", (dialog, id) -> {
                sendToSetting();
                dialog.cancel();
            });
        }

        builder.setNegativeButton("Cancel", (dialog, id) -> dialog.cancel());
        android.app.AlertDialog alert = builder.create();
        alert.show();
    }

    int REQUEST_PERMISSION_SETTING = 27;

    private void sendToSetting() {
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        Uri uri = Uri.fromParts("package", getPackageName(), null);
        intent.setData(uri);
        startActivityForResult(intent, REQUEST_PERMISSION_SETTING);
    }

    @Override
    public void allreadyExits(String str) {

    }

    @Override
    public void registerUsers(com.netflix.app.home.model.User user) {

        System.out.println(user.toString());
        dismissProgressDialog();
        SharedPrefManager.getInstance(getApplicationContext()).userLogin(user);
//        DeviceUtils.showToastMessage(this, getString(R.string.register_successful_tag));
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
        finish();
        dismissProgressDialog();
        if (user != null) {
            SharedPrefManager.getInstance(getApplicationContext()).userLogin(user);
//            DeviceUtils.showToastMessage(this, getString(C1734R.string.register_successful_tag));
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            finish();
            return;
        }
//        DeviceUtils.showToastMessage(this, getString(C1734R.string.login_unsuccessful_tag));


    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == REQUEST_PERMISSION_SETTING) {
//            if (PackageManager.PERMISSION_GRANTED == ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS)
//                    && PackageManager.PERMISSION_GRANTED == ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE)) {
//                Toast.makeText(this, "Thank You For Permission", Toast.LENGTH_SHORT).show();
//            }
//        }
//    }
}
