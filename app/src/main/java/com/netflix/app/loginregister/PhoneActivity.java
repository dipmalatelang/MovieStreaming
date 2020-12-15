package com.netflix.app.loginregister;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
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
    private Spinner Sp_CountryCode;
    private EditText Et_PhoneNumber;
    private String code;
    private ArrayAdapter<State> StateAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_phone);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_phone);
        Sp_CountryCode = findViewById(R.id.Sp_CountryCode);
        Et_PhoneNumber = findViewById(R.id.Et_PhoneNumber);
        Button btn_Next = findViewById(R.id.Btn_Next);

        State[] stateSpinner = new State[]{
                new State("+91", "India"),

        };
        setPopup1();

        StateAdapter = new ArrayAdapter<>(this, R.layout.spinner_text, stateSpinner);
        StateAdapter.setDropDownViewResource(R.layout.spinner_text);
        Sp_CountryCode.setAdapter(StateAdapter);

        Sp_CountryCode.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view,
                                       int position, long id) {
                State user = StateAdapter.getItem(position);
                if (user != null) {
                    code = user.getCode();
                    ((TextView) view).setText(user.getCode());
                    ((TextView) view).setTextColor(getResources().getColor(R.color.colorWhite));
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapter) {
            }
        });

        btn_Next.setOnClickListener(v -> {
            String mobile_no = Et_PhoneNumber.getText().toString();
            if (mobile_no.isEmpty() || mobile_no.length() < 10) {
                Et_PhoneNumber.setError("Enter Valid Mobile Number");
                Et_PhoneNumber.requestFocus();
                return;
            }
            sendDataToClass(mobile_no, code, VerifyPhoneActivity.class, "PhoneActivity");
        });
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

    private void setPopup1() {
        Field popup1;
        try {
            popup1 = Spinner.class.getDeclaredField("mPopup");
            popup1.setAccessible(true);

            ListPopupWindow StatepopupWindow = (ListPopupWindow) popup1.get(Sp_CountryCode);

            Objects.requireNonNull(StatepopupWindow).setHeight(LinearLayout.LayoutParams.WRAP_CONTENT);

        } catch (NoClassDefFoundError | ClassCastException | NoSuchFieldException | IllegalAccessException e) {

        }
    }
}
