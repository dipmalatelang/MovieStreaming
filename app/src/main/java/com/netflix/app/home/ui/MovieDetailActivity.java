package com.netflix.app.home.ui;



import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.netflix.app.R;
import com.netflix.app.category.adapter.CategoryAdapter;
import com.netflix.app.home.adapter.MovieAdapter;
import com.netflix.app.home.adapter.MovieItemClickListener;
import com.netflix.app.home.model.CategoryItem;
import com.netflix.app.home.model.MovieData;
import com.netflix.app.utlis.DataSources;

public class MovieDetailActivity extends AppCompatActivity implements MovieItemClickListener {

    private ImageView MovieThumbnailImg,MovieCoverImg;
    private TextView tv_title,tv_description;
    private FloatingActionButton play_fab;
    private RecyclerView homedetails_recyclerview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.fragment_home_details_);
        // ini views
        iniViews();

        iniHomedetailsmovie();



    }

    void iniViews() {
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
        tv_title.setText(movieTitle);

//        getSupportActionBar().setTitle(movieTitle);
//        tv_description = findViewById(R.id.detail_movie_desc);
        // setup animation
        MovieThumbnailImg.setAnimation(AnimationUtils.loadAnimation(this,R.anim.scale_animation));
        play_fab.setAnimation(AnimationUtils.loadAnimation(this,R.anim.scale_animation));

        homedetails_recyclerview = findViewById(R.id.homedetails_recyclerview);

        play_fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MovieDetailActivity.this,PlayMovieActivity.class);
                startActivity(intent);

                Toast.makeText(MovieDetailActivity.this, "Click movie detail activity", Toast.LENGTH_SHORT).show();


            }
        });


    }

    private void iniHomedetailsmovie() {
        MovieAdapter movieAdapter = new MovieAdapter(this, DataSources.getmovie(),this);
        homedetails_recyclerview.setAdapter(movieAdapter);
        homedetails_recyclerview.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
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
