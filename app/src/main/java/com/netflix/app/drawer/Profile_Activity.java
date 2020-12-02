package com.netflix.app.drawer;

import android.app.DatePickerDialog;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;

import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;

import com.netflix.app.R;
import com.netflix.app.databinding.ActivityProfileBinding;
import com.netflix.app.utlis.BaseActivity;

import java.util.Calendar;

public class Profile_Activity extends BaseActivity implements View.OnClickListener {


    ActivityProfileBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this,R.layout.activity_profile);
        binding.BtnApply.setOnClickListener(this);
        binding.EtDOB.setOnClickListener(this);

        /* ToDo create iniToolBar to add custom toolbar */
        iniToolBar();
    }
   void iniToolBar(){
       setSupportActionBar(binding.TbApp);
       getSupportActionBar().setDisplayHomeAsUpEnabled(true);
       getSupportActionBar().setDisplayShowHomeEnabled(true);
       final Drawable upArrow = ContextCompat.getDrawable(this, R.drawable.abc_ic_ab_back_material);
       upArrow.setColorFilter(ContextCompat.getColor(this, R.color.color_text_white), PorterDuff.Mode.SRC_ATOP);
       getSupportActionBar().setHomeAsUpIndicator(upArrow);
       binding.TbApp.setTitle("Profile");

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
                String password = binding.EtPassword.getText().toString().trim();
                String email = binding.EtEmail.getText().toString().trim();

                if(TextUtils.isEmpty(binding.EtName.getText().toString().trim())){
                    snackBar(binding.relativeLayoutProfile,"Enter your Name");
                }
                else if(TextUtils.isEmpty(binding.EtEmail.getText().toString().trim())){
                    snackBar(binding.relativeLayoutProfile,"Enter your Email");
                }
                else if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    snackBar(binding.relativeLayoutProfile,"Please Enter Valid Email Address");
                }

                else if(TextUtils.isEmpty(binding.EtDOB.getText().toString().trim())){
                    snackBar(binding.relativeLayoutProfile,"Enter your Mobile Number");
                }

                else  if(TextUtils.isEmpty(binding.EtDOB.getText().toString().trim())){
                    snackBar(binding.relativeLayoutProfile,"Enter your Date Of Birth");
                }
                else if (TextUtils.isEmpty(binding.EtPassword.getText().toString().trim())){
                    snackBar(binding.relativeLayoutProfile,"Enter your password");
                }
                else if (password.length() < 6){
                    snackBar(binding.relativeLayoutProfile,"password must be at least 6 characters");
                }
                else if(!password.matches("[a-zA-Z0-9.? ]*")){
                    snackBar(binding.relativeLayoutProfile,"Special characters are not allowed");
                }
                else if(binding.EtMobile.length() < 10){
                    snackBar(binding.relativeLayoutProfile,"Enter correct Moible Number");
                }
                else {
                    snackBar(binding.relativeLayoutProfile,"Data Successfully Save ");
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

        binding.EtDOB.setText(new StringBuilder().append(sDay)
                .append("-").append(sMonth).append("-").append(year)
                .append(" "));
    };

}
