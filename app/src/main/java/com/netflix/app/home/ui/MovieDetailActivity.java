package com.netflix.app.home.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.bumptech.glide.Glide;
import com.google.android.material.tabs.TabLayout;
import com.netflix.app.R;
import com.netflix.app.databinding.FragmentHomeDetailsBinding;
import com.netflix.app.home.adapter.MovieItemClickListener;
import com.netflix.app.home.adapter.MyAdapter;
import com.netflix.app.home.model.AllVideo;
import com.netflix.app.home.model.CategoryItem;
import com.netflix.app.utlis.FavDB;
import com.netflix.app.utlis.SharedPrefs;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import static com.netflix.app.home.adapter.SliderPagerAdapter.VIDEO_BANNER;
import static com.netflix.app.home.adapter.SliderPagerAdapter.VIDEO_BANNER_Name;


public class MovieDetailActivity extends AppCompatActivity implements MovieItemClickListener {

    FragmentHomeDetailsBinding binding;
    String movieTitle, imagedesc, info, videoUrl, Genres, Director;
    TextView detail_movie_title, Tv_Desc, Tv_info;
    ImageView detail_movie_img;
    String img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        binding = DataBindingUtil.setContentView(this, R.layout.fragment_home_details_);
        iniViews();
        tabView();

        binding.btnAddback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MovieDetailActivity.this, Home_Activity.class);
                startActivity(i);

            }
        });

        binding.btnFev.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    binding.btnFev.setBackgroundResource(R.drawable.ic_heart_shape_silhouette);
                    FavDB favDB = new FavDB(MovieDetailActivity.this);
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

    void tabView() {
        TabLayout.Tab open = binding.tabLayoutMovie.newTab();
        open.setText("Episodes");
        binding.tabLayoutMovie.addTab(open);


        TabLayout.Tab favorites = binding.tabLayoutMovie.newTab();
        favorites.setText("More Like This");


        binding.tabLayoutMovie.addTab(favorites);
        binding.tabLayoutMovie.setTabGravity(TabLayout.GRAVITY_FILL);
//        ((ViewGroup) binding.tabLayoutMovie.getChildAt(0)).getChildAt(0).setVisibility(View.GONE);
//        TabLayout.Tab tab = binding.tabLayoutMovie.getTabAt(1);
//        tab.select();


        final MyAdapter adapter = new MyAdapter(this, getSupportFragmentManager(), binding.tabLayoutMovie.getTabCount());
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

    void iniViews()  {
        detail_movie_title = findViewById(R.id.detail_movie_title);
        Tv_Desc = findViewById(R.id.Tv_Desc);
        Tv_info = findViewById(R.id.Tv_info);
        detail_movie_img = findViewById(R.id.detail_movie_img);

        movieTitle = getIntent().getExtras().getString("title");
        imagedesc = getIntent().getExtras().getString("imgDescription");
        info = getIntent().getExtras().getString("imginfo");


        videoUrl = getIntent().getExtras().getString("videourl");
        Genres =getIntent().getExtras().getString("Genres");
        Director = getIntent().getExtras().getString("director");
        img =getIntent().getExtras().getString("imgURL");

        Glide.with(this).load(img).into(detail_movie_img);
        Log.d("TAG", "OOOOOOO: "+info);




        detail_movie_title.setText(movieTitle);
//        getSupportActionBar().setTitle(movieTitle);
        Tv_info.setText("Description :" + imagedesc + "\n" +"Info :" + info);
//        Tv_Desc.setText("GENERS :" + Genres + "\n" +"Director :" +Director);




        // setup animation
        binding.detailMovieImg.setAnimation(AnimationUtils.loadAnimation(this, R.anim.scale_animation));
        binding.playFab.setAnimation(AnimationUtils.loadAnimation(this, R.anim.scale_animation));


        binding.playFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPrefs.getInstance().addString(VIDEO_BANNER, videoUrl);
                SharedPrefs.getInstance().addString(VIDEO_BANNER_Name, movieTitle);
                Log.d("TAG", "onClickvideo: " + videoUrl);
                Intent intent = new Intent(MovieDetailActivity.this, PlayMovieActivity.class);
                startActivity(intent);
                Toast.makeText(MovieDetailActivity.this, "Click movie detail activity", Toast.LENGTH_SHORT).show();


            }
        });


    }

    @Override
    public void onBackPressed() {
        // code here to show dialog
        super.onBackPressed();  // optional depending on your needs
    }


    @Override
    public void onMovieClick(AllVideo movie, ImageView movieImageView) {

    }
}
