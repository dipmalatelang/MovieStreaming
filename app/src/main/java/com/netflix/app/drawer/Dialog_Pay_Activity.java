package com.netflix.app.drawer;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.kofigyan.stateprogressbar.StateProgressBar;
import com.netflix.app.R;
import com.netflix.app.databinding.ActivityDialogPayBinding;

public class Dialog_Pay_Activity extends AppCompatActivity {
    ActivityDialogPayBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_dialog__pay_);
        String[] descriptionData = {"Plans", "Sign In", "Pay", "Watch"};
        StateProgressBar stateProgressBar =  findViewById(R.id.state_progress_id);
        stateProgressBar.setStateDescriptionData(descriptionData);
    }
}