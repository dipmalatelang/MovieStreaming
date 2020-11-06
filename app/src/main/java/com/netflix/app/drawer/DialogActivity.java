package com.netflix.app.drawer;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.netflix.app.R;
import com.netflix.app.databinding.ActivityDialogBinding;

public class DialogActivity extends AppCompatActivity {
    ActivityDialogBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_dialog);

    }
}