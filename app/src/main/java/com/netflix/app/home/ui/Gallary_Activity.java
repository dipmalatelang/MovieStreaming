package com.netflix.app.home.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.databinding.DataBindingUtil;
import com.netflix.app.R;
import com.netflix.app.databinding.ActivityGallaryBinding;

public class Gallary_Activity extends Activity {
    ActivityGallaryBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_gallary_);

        binding.btnBackpress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Gallary_Activity.this,Home_Activity.class);
                startActivity(intent);
            }
        });


    }
    @Override
    public void onBackPressed()
    {
        // code here to show dialog
        super.onBackPressed();  // optional depending on your needs
    }
}
