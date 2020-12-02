package com.netflix.app.auth;

import android.content.Intent;
import android.content.IntentSender;
import android.net.wifi.hotspot2.pps.Credential;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.core.view.PointerIconCompat;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.credentials.HintRequest;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.material.textfield.TextInputLayout;
import com.hbb20.CountryCodePicker;
import com.netflix.app.R;
import com.netflix.app.utlis.BaseActivity;


import java.util.Objects;


public class ForgotPasswordActivity extends BaseActivity implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {
    private static final int RESOLVE_HINT = 1011;
    private Button btnreset;
    String ccode;

    private CountryCodePicker codePicker;
    private TextInputLayout etPhone;
    String mobileNumber;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) R.layout.activity_forgot_password);
        GoogleApiClient mGoogleApiClient = new GoogleApiClient.Builder(this).addConnectionCallbacks(this).addOnConnectionFailedListener(this).addApi(Auth.CREDENTIALS_API).build();
        if (mGoogleApiClient != null) {
            mGoogleApiClient.connect();
        }
        try {
            startIntentSenderForResult(Auth.CredentialsApi.getHintPickerIntent(mGoogleApiClient, new HintRequest.Builder().setPhoneNumberIdentifierSupported(true).build()).getIntentSender(), PointerIconCompat.TYPE_TEXT, (Intent) null, 0, 0, 0, (Bundle) null);
        } catch (IntentSender.SendIntentException e) {
            Log.e("", "Could not start hint picker Intent", e);
        }
        this.etPhone = (TextInputLayout) findViewById(R.id.phoneReg);
        this.codePicker = (CountryCodePicker) findViewById(R.id.ccp);
        Button button = (Button) findViewById(R.id.resetBtn);
        this.btnreset = button;
        button.setOnClickListener(new View.OnClickListener() {
            public final void onClick(View view) {
                ForgotPasswordActivity.this.lambda$onCreate$0$ForgotPasswordActivity(view);
            }
        });
    }

    public  void lambda$onCreate$0$ForgotPasswordActivity(View v) {
        this.ccode = this.codePicker.getSelectedCountryCode();
        this.mobileNumber = this.etPhone.getEditText().getText().toString().trim();
        String mobile = "+" + this.ccode + this.mobileNumber;
        if (isValidation()) {
            Intent intentq = new Intent(this, OtpVerificationActivity.class);
            intentq.putExtra("mobile", mobile);
            startActivity(intentq);
        }
    }



    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1008) {
            if (resultCode == -1) {
                com.google.android.gms.auth.api.credentials.Credential credential = data.getParcelableExtra(com.google.android.gms.auth.api.credentials.Credential.EXTRA_KEY);
                Log.e("cred.getId", credential.getId());
                this.mobileNumber = credential.getId();
                Intent intentq = new Intent(this, OtpVerificationActivity.class);
                intentq.putExtra("mobile", this.mobileNumber);
                startActivity(intentq);
                return;
            }
            Log.e("cred.getId", "1008 else");
        }
    }

    private boolean isValidation() {
        if (!((EditText) Objects.requireNonNull(this.etPhone.getEditText())).getText().toString().trim().isEmpty() && this.etPhone.getEditText().getText().toString().length() >= 10) {
            return true;
        }
        this.etPhone.setError(getString(R.string.field_can_not_be_empty_tag));
        return false;
    }

    public void finishActivity(View view) {
        finish();
    }

    public void onConnected(Bundle bundle) {
    }

    public void onConnectionSuspended(int i) {
    }

    public void onConnectionFailed(ConnectionResult connectionResult) {
    }
}
