package com.netflix.app.utlis;



import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;


import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.netflix.app.R;
import com.netflix.app.home.model.User;
import com.netflix.app.home.ui.Home_Activity;

import java.text.DateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;


public abstract class BaseActivity extends AppCompatActivity {

    private static final String TAG = "BaseActivity";
    public DatabaseReference UsersInstance = FirebaseDatabase.getInstance().getReference("UsersDetails");
    public DatabaseReference UserDataInstance = FirebaseDatabase.getInstance().getReference("UserData");
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;




//
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//    }


    /*TODO Create Sharedprefrence for Storing Data*/
    private SharedPreferences getPrefData(Context context) {
        return context.getSharedPreferences(getResources().getString(R.string.app_name), Context.MODE_PRIVATE);
    }

    /*TODO Get Data from Sharedprefrence*/
    public String getPref(Context context, String key) {
        return getPrefData(context).getString(key, "null");
    }

    /*TODO Set Data to Sharedprefrence*/
    public void setPref(Context context, String key, String value) {
        SharedPreferences.Editor editor = getPrefData(context).edit();
        editor.putString(key, String.valueOf(value));
        editor.apply();
    }

    public void snackBar(View layout, String s) {
        Snackbar snackbar = Snackbar.make(layout, s.toUpperCase(), Snackbar.LENGTH_LONG);
        snackbar.show();
    }
    public void showProgressDialog() {
        if (!isFinishing()) {
            ProgressActivity.showDialog(BaseActivity.this);
        }
    }

    public void dismissProgressDialog() {
        if (!isFinishing()) {
            ProgressActivity.dismissDialog();
        }
    }



    public  void retriveUserDetails(FirebaseUser fUser){
        UsersInstance.child(fUser.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User user = snapshot.getValue(User.class);
                if(user !=null)
                    saveDetails(user);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
//    public void setPhoneNumber(String id, String mobile, String mobileCode)
//    {
//        UserInstance.child(id).child("phone").setValue(mobile);
//        UserInstance.child(id).child("mobileCode").setValue(mobileCode);
//    }

    private void saveDetails(User user) {
        dismissProgressDialog();
        startActivity(new Intent(this, Home_Activity.class));
        finish();

        sharedPreferences = getSharedPreferences("LoginDetails", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        editor.putString("Id", user.getId());
        editor.putString("Name", user.getName());
        editor.putString("Email", user.getEmail());
        editor.putString("Phone", user.getPhone());
        editor.putString("gmail", user.getGmail());
        editor.putString("mobileCode", user.getMobilecode());
        editor.apply();

    }

    public void saveLoginDetails(String email, String password){


        sharedPreferences = getSharedPreferences("LoginDetails", Context.MODE_PRIVATE);
        {
            editor = sharedPreferences.edit();
            editor.putString("Email", email);
            editor.putString("Password", password);
            editor.commit();

        }
    }
//    private void saveContacts(String user) {
//        OneTimeWorkRequest workRequest = new OneTimeWorkRequest.Builder(ContactWork.class)
//                .setInputData(createInputData(user))
//                .setInitialDelay(2, TimeUnit.SECONDS)
//                .build();
//        WorkManager.getInstance(this).enqueue(workRequest);
//    }

    public void sendDataToClass(String mobile_no, String code, Class nextClass, String activityName) {

//            saveContacts(mobile_no);
            if (!getPref(this,"username").equalsIgnoreCase(mobile_no)){
                setPref(this,"firstinstall","null");
            }
            setPref(this, "username", mobile_no);

            Intent intent = new Intent(this, nextClass);
            intent.putExtra("mobileCode", code);
            intent.putExtra("mobile", mobile_no);
            intent.putExtra("activityName", activityName);
            startActivity(intent);
            finish();

    }

    public void sendVerificationCode(String mobile, String mobileCode, PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks) {
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                mobileCode + mobile,
                60,
                TimeUnit.SECONDS,
                this,
                mCallbacks);

        Log.d("FALALA", "Sent");
    }
    public void verifyVerificationCode(FirebaseAuth mAuth, String mVerificationId, String code, String mobileCode, String mobile, ViewGroup Cl_Verify, String activityName) {
        //creating the credential
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(mVerificationId, code);
        Log.d(TAG, "verifyVerificationCode: " + activityName);
        if (activityName.equalsIgnoreCase("Codeactivity")) {
            SharedPreferences shared = getSharedPreferences("LoginDetails", MODE_PRIVATE);
            SharedPreferences.Editor editor = shared.edit();
            editor.putString("Phone", mobile);
            editor.putString("phoneCode", mobileCode);
            editor.putString("securityType", "2Auth");
            editor.putString("securityCode", "");
            editor.apply();
        }

        phoneLogin(mAuth, credential, mobile, Cl_Verify, activityName);
    }
    private void phoneLogin(FirebaseAuth mAuth, PhoneAuthCredential credential, String mobile, ViewGroup Cl_Verify, String activityName) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {

                        setPref(this, "LoginSuccess", "true");
                        setPref(this, "NormalLogin", "false");
                        startActivity(new Intent(this, Home_Activity.class));
                        this.finishAffinity();
                        Log.d(TAG, "phoneLogin: " + activityName);
                        FirebaseUser firebaseUser = mAuth.getCurrentUser();
                        String currentDateTimeString = DateFormat.getDateTimeInstance().format(new Date());
                        if (firebaseUser != null) {
                            Log.d("GAHHAA", "" + mobile);
                            User user = new User(firebaseUser.getUid(),"", firebaseUser.getEmail(), "", mobile, currentDateTimeString, "", "2Auth", "");
                            UsersInstance.child(firebaseUser.getUid()).setValue(user);
                        }

                        Log.d(TAG, "phoneLogin: " + task.isSuccessful());


                        if (task.getResult() != null) {
                            FirebaseUser user = task.getResult().getUser();

                            if (user != null) {
                                UsersInstance.child(user.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
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
                        if (task.getException() != null) {
                            snackBar(Cl_Verify, "" + task.getException().getMessage());
                        }
                    }
                });
    }

    public void updateUI(FirebaseUser currentUser) {
        if (currentUser != null) {
            retriveUserDetails(currentUser);
        }
    }

    public void toggleProgress(boolean flag) {
        ConstraintLayout progressLayout = (ConstraintLayout) findViewById(R.id.layout_progress_bar);
        if (flag) {
            progressLayout.setVisibility(View.VISIBLE);
        } else {
            progressLayout.setVisibility(View.GONE);
        }
    }

}
