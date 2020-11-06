package com.netflix.app.home.ui;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.bumptech.glide.Glide;
import com.google.android.material.tabs.TabLayout;
import com.netflix.app.R;
import com.netflix.app.databinding.FragmentHomeDetailsBinding;
import com.netflix.app.home.adapter.MovieItemClickListener;
import com.netflix.app.home.adapter.MyAdapter;
import com.netflix.app.home.model.CategoryItem;
import com.netflix.app.utlis.FavDB;


public class MovieDetailActivity extends AppCompatActivity implements MovieItemClickListener {

  FragmentHomeDetailsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        binding = DataBindingUtil.setContentView(this,R.layout.fragment_home_details_);
        iniViews();
        tabView();

        binding.btnAddback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MovieDetailActivity.this,Home_Activity.class);
                startActivity(i);

            }
        });

        binding.btnFev.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    binding.btnFev.setBackgroundResource(R.drawable.ic_heart_shape_silhouette);
                    FavDB favDB  = new FavDB(MovieDetailActivity.this);
//                    String imageResourceId = String.valueOf(getIntent().getExtras().getInt("imgURL"));

//                    favDB.insertIntoTheDatabase("",imageResourceId,"","1");

                    //Show "Saved to favourite" toast
                } else {
                    binding.btnFev.setBackgroundResource(R.drawable.ic_fav_unselected);

                    //Show "Removed from favourite" toast
                }
            }
        });


    }
    void tabView(){
        TabLayout.Tab open = binding.tabLayoutMovie.newTab();
        open.setText("Episodes");
        binding.tabLayoutMovie.addTab(open);

        TabLayout.Tab favorites = binding.tabLayoutMovie.newTab();
        favorites.setText("More Like This");

        binding.tabLayoutMovie.addTab(favorites);
        binding.tabLayoutMovie.setTabGravity(TabLayout.GRAVITY_FILL);

        final MyAdapter adapter = new MyAdapter(this,getSupportFragmentManager(), binding.tabLayoutMovie.getTabCount());
        binding.vpMovie.setAdapter(adapter);

        binding.vpMovie.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(binding.tabLayoutMovie));

        binding.tabLayoutMovie.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                binding.vpMovie.setCurrentItem(tab.getPosition());
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


        String movieTitle = getIntent().getExtras().getString("title");
        int imagecover = getIntent().getExtras().getInt("imgCover");

        int imageResourceId = getIntent().getExtras().getInt("imgURL");
        Glide.with(MovieDetailActivity.this).load(imageResourceId).into(binding.detailMovieImg);
        binding.detailMovieImg.setImageResource(imageResourceId);
//        MovieCoverImg = findViewById(R.id.detail_movie_cover);
//        Glide.with(this).load(imagecover).into(MovieCoverImg);
//        tv_title.setText(movieTitle);
//        getSupportActionBar().setTitle(movieTitle);
//        tv_description = findViewById(R.id.detail_movie_desc);
        // setup animation
        binding.detailMovieImg.setAnimation(AnimationUtils.loadAnimation(this,R.anim.scale_animation));
        binding.playFab.setAnimation(AnimationUtils.loadAnimation(this,R.anim.scale_animation));


        binding.playFab.setOnClickListener(new View.OnClickListener() {
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
