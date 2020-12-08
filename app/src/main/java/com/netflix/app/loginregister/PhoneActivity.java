package com.netflix.app.loginregister;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.widget.ListPopupWindow;


import com.netflix.app.R;
import com.netflix.app.utlis.BaseActivity;

import java.lang.reflect.Field;
import java.util.Objects;


public class PhoneActivity extends BaseActivity {


    EditText edNumber;
    Button btnSub;
    Spinner spinner;
    private String code;

    String mobile,mobileCode;
    ArrayAdapter<State> StateAdapter;
    State[] StateSpinner;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_phone);
        spinner  = findViewById(R.id.spinner);
        btnSub = findViewById(R.id.btn_sub);
        edNumber = findViewById(R.id.ed_number);

        StateSpinner = new State[]{
                new State("+91", "India"),

        };

        setPopup1();

        StateAdapter = new ArrayAdapter<>(this, R.layout.spinner_text, StateSpinner);
        StateAdapter.setDropDownViewResource(R.layout.spinner_text);
        spinner.setAdapter(StateAdapter);



        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view,
                                       int position, long id) {
                // Here you get the current item (a User object) that is selected by its position
                State user = StateAdapter.getItem(position);
                // Here you can do the action you want to...
                code=user.getCode();
                ((TextView) view).setText(user.getCode());
                ((TextView) view).setTextColor(getResources().getColor(R.color.color_text_white));
//                Toast.makeText(EditPhoneActivity.this, ""+user.getCode(), Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapter) {  }
        });
        btnSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mobile = edNumber.getText().toString().trim();

                if (mobile.isEmpty() || mobile.length() < 10) {
                    edNumber.setError("Enter a valid mobile");
                    edNumber.requestFocus();
                    return;
                }

                Intent intent = new Intent(PhoneActivity.this, VerifyPhoneActivity.class);
                intent.putExtra("mobileCode",code);
                intent.putExtra("mobile", mobile);
                startActivity(intent);
                finish();
            }
        });

        SharedPreferences sharedPreferences = getSharedPreferences("LoginDetails", Context.MODE_PRIVATE);

        if (sharedPreferences.contains("Phone")) {
            mobile = (sharedPreferences.getString("Phone", ""));
            mobileCode=sharedPreferences.getString("mobileCode","");
            if (!mobile.equalsIgnoreCase("")) {
                edNumber.setEnabled(false);
                spinner.setEnabled(false);
                btnSub.setClickable(false);
                edNumber.setText(mobile);
                spinner.setSelection(getPos(mobileCode));

            } else {
                edNumber.setEnabled(true);
                spinner.setEnabled(true);
                btnSub.setClickable(true);
            }

        }



    }

    int pos=0;
    private int getPos(String s) {
        for(int i=0;i<StateSpinner.length;i++)
        {
            if(StateSpinner[i].getCode().equalsIgnoreCase(s))
            {
                pos=i;
            }
        }
        return pos;
    }

    private void setPopup1() {
        Field popup1;
        try {
            popup1 = Spinner.class.getDeclaredField("mPopup");
            popup1.setAccessible(true);

            ListPopupWindow StatepopupWindow = (ListPopupWindow) popup1.get(spinner);

            Objects.requireNonNull(StatepopupWindow).setHeight(LinearLayout.LayoutParams.WRAP_CONTENT);

        } catch (NoClassDefFoundError | ClassCastException | NoSuchFieldException | IllegalAccessException e) {

        }
    }




    private class State {
        private String code;
        private String statename;

        public State(String code, String statename) {
            this.code = code;
            this.statename = statename;
        }

        public String getCode() {
            return code;
        }

        public String getStatename() {
            return statename;
        }

        @Override
        public String toString() {
            return this.statename;

        }
    }
}