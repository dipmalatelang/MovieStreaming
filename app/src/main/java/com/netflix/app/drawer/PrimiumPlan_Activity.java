package com.netflix.app.drawer;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.kofigyan.stateprogressbar.StateProgressBar;
import com.netflix.app.R;
import com.netflix.app.home.ui.Home_Activity;

public class PrimiumPlan_Activity extends AppCompatActivity {
    Toolbar Tb_App;
    Button btn_proceed;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_primium_plan_);
        Tb_App = findViewById(R.id.Tb_App);
        btn_proceed =findViewById(R.id.btn_proceed);
        iniToolBar();
        String[] descriptionData = {"Plans", "Sign In", "Pay", "Watch"};

        StateProgressBar stateProgressBar = (StateProgressBar) findViewById(R.id.state_progress_id);
        stateProgressBar.setStateDescriptionData(descriptionData);
        btn_proceed.setOnClickListener(new View.OnClickListener() {

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
        setSupportActionBar(Tb_App);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        final Drawable upArrow = ContextCompat.getDrawable(this, R.drawable.abc_ic_ab_back_material);
        upArrow.setColorFilter(ContextCompat.getColor(this, R.color.color_text_white), PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);
        Tb_App.setTitle("Premium Plans");

    }






    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}