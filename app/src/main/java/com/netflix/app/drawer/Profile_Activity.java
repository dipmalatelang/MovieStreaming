package com.netflix.app.drawer;

import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import com.netflix.app.R;

public class Profile_Activity extends AppCompatActivity {
    Toolbar Tb_App;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Tb_App = findViewById(R.id.Tb_App);
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
}
