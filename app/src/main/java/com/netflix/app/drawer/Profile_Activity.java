package com.netflix.app.drawer;

import android.app.DatePickerDialog;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import com.netflix.app.R;
import com.netflix.app.utlis.BaseActivity;

import java.util.Calendar;

public class Profile_Activity extends BaseActivity implements View.OnClickListener {

    Toolbar Tb_App;
    EditText Et_Name,Et_Email,Et_Mobile,Et_Password,Et_DOB;
    RadioGroup radiogroup;
    Button Btn_apply;
    RelativeLayout relativeLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Tb_App = findViewById(R.id.Tb_App);


        Et_Name = findViewById(R.id.Et_Name);
        Et_Email = findViewById(R.id.Et_Email);
        Et_Mobile = findViewById(R.id.Et_Mobile);
        Et_Password = findViewById(R.id.Et_Password);
        Et_DOB = findViewById(R.id.Et_DOB);
        Btn_apply = findViewById(R.id.Btn_apply);
        relativeLayout = findViewById(R.id.relativeLayout);
        Btn_apply.setOnClickListener(this);
        Et_DOB.setOnClickListener(this);



        iniToolBar();
    }
   void iniToolBar(){
       setSupportActionBar(Tb_App);
       getSupportActionBar().setDisplayHomeAsUpEnabled(true);
       getSupportActionBar().setDisplayShowHomeEnabled(true);
       final Drawable upArrow = ContextCompat.getDrawable(this, R.drawable.abc_ic_ab_back_material);
       upArrow.setColorFilter(ContextCompat.getColor(this, R.color.color_text_white), PorterDuff.Mode.SRC_ATOP);
       getSupportActionBar().setHomeAsUpIndicator(upArrow);
       Tb_App.setTitle("Profile");

    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onClick(View v) {
        switch(v.getId())

        {
            case R.id.Btn_apply:
                String password = Et_Password.getText().toString();
                String email = Et_Email.getText().toString();

                if(TextUtils.isEmpty(Et_Name.getText().toString())){
                    snackBar(relativeLayout,"Enter your Name");
                }
                else if(TextUtils.isEmpty(Et_Email.getText().toString())){
                    snackBar(relativeLayout,"Enter your Email");
                }
                else if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    snackBar(relativeLayout,"Please Enter Valid Email Address");
                }

                else if(TextUtils.isEmpty(Et_Mobile.getText().toString())){
                    snackBar(relativeLayout,"Enter your Mobile Number");
                }

                else  if(TextUtils.isEmpty(Et_DOB.getText().toString())){
                    snackBar(relativeLayout,"Enter your Date Of Birth");
                }
                else if (TextUtils.isEmpty(Et_Password.getText().toString())){
                    snackBar(relativeLayout,"Enter your password");
                }
                else if (password.length() < 6){
                    snackBar(relativeLayout,"password must be at least 6 characters");
                }
                else if(!password.matches("[a-zA-Z0-9.? ]*")){
                    snackBar(relativeLayout,"Special characters are not allowed");
                }
                else if(Et_Mobile.length() < 10){
                    snackBar(relativeLayout,"Enter correct Moible Number");
                }
                else {
                    snackBar(relativeLayout,"Data Successfully Save ");
                }

                break;
             case R.id.Et_DOB:
                Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR);
                int mMonth = c.get(Calendar.MONTH);
                int mDay = c.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog dialog = new DatePickerDialog(Profile_Activity.this,datePickerListener, mYear,mMonth,mDay);
                dialog.getDatePicker().setMaxDate(System.currentTimeMillis() - 568025136000L);
                dialog.show();
                break;
        }

    }
    private int year,month,day;

    DatePickerDialog.OnDateSetListener datePickerListener = (view, selectedYear, selectedMonth, selectedDay) -> {
        String sMonth, sDay;
        year = selectedYear;
        month = selectedMonth + 1;
        day = selectedDay;

        if (month < 10) {
            sMonth = "0" + month;
        } else {
            sMonth = String.valueOf(month);
        }

        if (day < 10) {
            sDay = "0" + day;
        } else {
            sDay = String.valueOf(day);
        }

        Et_DOB.setText(new StringBuilder().append(sDay)
                .append("-").append(sMonth).append("-").append(year)
                .append(" "));
    };

}
