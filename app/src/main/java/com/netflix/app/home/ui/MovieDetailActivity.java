package com.netflix.app.home.ui;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.netflix.app.R;
import com.netflix.app.home.adapter.MovieItemClickListener;
import com.netflix.app.home.adapter.MyAdapter;
import com.netflix.app.home.model.CategoryItem;


public class MovieDetailActivity extends AppCompatActivity implements MovieItemClickListener {

    private ImageView MovieThumbnailImg, MovieCoverImg;
    private TextView tv_title, tv_description;
    private FloatingActionButton play_fab;
    private CheckBox btn_fev;
    private Button btn_addback;
    TabLayout tabLayout_movie;
    ViewPager vp_movie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.fragment_home_details_);
        iniViews();
        tabView();
        String item_fav_status = "0";
        btn_addback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MovieDetailActivity.this,Home_Activity.class);
                startActivity(i);

            }
        });

        btn_fev.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    btn_fev.setBackgroundResource(R.drawable.ic_heart_shape_silhouette);
                    //Show "Saved to favourite" toast
                } else {
                    btn_fev.setBackgroundResource(R.drawable.ic_fav_unselected);

                    //Show "Removed from favourite" toast
                }
            }
        });




    }
    void tabView(){
        TabLayout.Tab open = tabLayout_movie.newTab();
        open.setText("Episodes");
        tabLayout_movie.addTab(open);

        TabLayout.Tab favorites = tabLayout_movie.newTab();
        favorites.setText("More Like This");

        tabLayout_movie.addTab(favorites);
        tabLayout_movie.setTabGravity(TabLayout.GRAVITY_FILL);

        final MyAdapter adapter = new MyAdapter(this,getSupportFragmentManager(), tabLayout_movie.getTabCount());
        vp_movie.setAdapter(adapter);

        vp_movie.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout_movie));

        tabLayout_movie.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                vp_movie.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }




    void iniViews() {
        tabLayout_movie = findViewById(R.id.tabLayout_movie);
        vp_movie = findViewById(R.id.vp_movie);
        btn_fev = findViewById(R.id.btn_fev);
        btn_addback =findViewById(R.id.btn_addback);

        play_fab = findViewById(R.id.play_fab);

        String movieTitle = getIntent().getExtras().getString("title");
        int imageResourceId = getIntent().getExtras().getInt("imgURL");
        int imagecover = getIntent().getExtras().getInt("imgCover");
        MovieThumbnailImg = findViewById(R.id.detail_movie_img);
        Glide.with(this).load(imageResourceId).into(MovieThumbnailImg);
        MovieThumbnailImg.setImageResource(imageResourceId);
//        MovieCoverImg = findViewById(R.id.detail_movie_cover);
//        Glide.with(this).load(imagecover).into(MovieCoverImg);
        tv_title = findViewById(R.id.detail_movie_title);
//        tv_title.setText(movieTitle);

//        getSupportActionBar().setTitle(movieTitle);
//        tv_description = findViewById(R.id.detail_movie_desc);
        // setup animation
        MovieThumbnailImg.setAnimation(AnimationUtils.loadAnimation(this,R.anim.scale_animation));
        play_fab.setAnimation(AnimationUtils.loadAnimation(this,R.anim.scale_animation));

//        homedetails_recyclerview = findViewById(R.id.homedetails_recyclerview);

        play_fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MovieDetailActivity.this,PlayMovieActivity.class);
                startActivity(intent);
                Toast.makeText(MovieDetailActivity.this, "Click movie detail activity", Toast.LENGTH_SHORT).show();


            }
        });


    }

    @Override
    public void onBackPressed()
    {
        // code here to show dialog
        super.onBackPressed();  // optional depending on your needs
    }

    @Override
    public void onMovieClick(CategoryItem movie, ImageView movieImageView) {

    }
}
