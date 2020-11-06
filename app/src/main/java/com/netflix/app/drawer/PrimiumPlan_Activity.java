package com.netflix.app.drawer;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.kofigyan.stateprogressbar.StateProgressBar;
import com.netflix.app.R;
import com.netflix.app.databinding.ActivityPrimiumPlanBinding;
import com.netflix.app.home.ui.Home_Activity;
import com.netflix.app.utlis.BaseActivity;

public class PrimiumPlan_Activity extends BaseActivity {

    ActivityPrimiumPlanBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_primium_plan_);

        /* ToDo create iniToolBar to add custom toolbar */
        iniToolBar();
        String[] descriptionData = {"Plans", "Sign In", "Pay", "Watch"};
        StateProgressBar stateProgressBar = findViewById(R.id.state_progress_id);
        stateProgressBar.setStateDescriptionData(descriptionData);
        binding.btnProceed.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {



                switch (stateProgressBar.getCurrentStateNumber())

                {
                    case 1:
                        stateProgressBar.setCurrentStateNumber(StateProgressBar.StateNumber.TWO);
                        BottomSheetDialog dialog = new BottomSheetDialog(PrimiumPlan_Activity.this);
                        dialog.setContentView(R.layout.activity_dialog);
                        dialog.setCanceledOnTouchOutside(true);
                        dialog.show();
                        ConstraintLayout constraintlayout = dialog.findViewById(R.id.constraintlayout);

                        Button Btn_Proceed = dialog.findViewById(R.id.Btn_Proceed);
                        EditText Et_mobileNo = dialog.findViewById(R.id.Et_mobileNo);
                        Btn_Proceed.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if(TextUtils.isEmpty(Et_mobileNo.getText().toString().trim())){
                                    Toast.makeText(PrimiumPlan_Activity.this, "Enter your Moible Number", Toast.LENGTH_SHORT).show();
                                }
                                else if(Et_mobileNo.length() < 10){
                                    Toast.makeText(PrimiumPlan_Activity.this, "Enter correct Moible Number", Toast.LENGTH_SHORT).show();
                                }
                                else {
                                    stateProgressBar.setCurrentStateNumber(StateProgressBar.StateNumber.THREE);
                                    Intent intenpay = new Intent(PrimiumPlan_Activity.this, Dialog_Pay_Activity.class);
                                    startActivity(intenpay);

                                }

                            }
                        });


                        break;
                    case 2:
                        stateProgressBar.setCurrentStateNumber(StateProgressBar.StateNumber.THREE);
                        Intent intenpay = new Intent(PrimiumPlan_Activity.this, Dialog_Pay_Activity.class);
                        startActivity(intenpay);

                        break;
                    case 3:
                        stateProgressBar.setCurrentStateNumber(StateProgressBar.StateNumber.FOUR);
                        Intent intenthome = new Intent(PrimiumPlan_Activity.this, Home_Activity.class);
                        startActivity(intenthome);
                        break;


                }

            }
        });

    }

    void iniToolBar(){
        setSupportActionBar(binding.TbApp);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        final Drawable upArrow = ContextCompat.getDrawable(this, R.drawable.abc_ic_ab_back_material);
        upArrow.setColorFilter(ContextCompat.getColor(this, R.color.color_text_white), PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);
        binding.TbApp.setTitle("Premium Plans");

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}