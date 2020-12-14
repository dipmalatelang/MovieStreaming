 package com.netflix.app.home.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.widget.CompoundButton;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.bumptech.glide.Glide;
import com.google.android.material.tabs.TabLayout;
import com.netflix.app.R;

import com.netflix.app.databinding.ActivityMovieDetailsBinding;
import com.netflix.app.home.adapter.MovieItemClickListener;
import com.netflix.app.home.adapter.MyAdapter;
import com.netflix.app.home.model.AllDataPojo;
import com.netflix.app.utlis.FavDB;
import com.netflix.app.utlis.SharedPrefs;

import static com.netflix.app.home.adapter.SliderPagerAdapter.VIDEO_BANNER;
import static com.netflix.app.home.adapter.SliderPagerAdapter.VIDEO_BANNER_Name;
import static com.netflix.app.home.ui.HomeFragment.GETVIDEOTYPE;


 public class MovieDetailActivity extends AppCompatActivity implements MovieItemClickListener {

    public static String VIDEO_channelID = "channelID";
    String movieTitle, imagedesc, cast, videoUrl, Genres, Director ,duration, channelid,videoType ;
    String img;
    int channelID;
    private FavDB favDB;
    private ActivityMovieDetailsBinding binding;
    private static final String TAG = "MovieDetailActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_movie_details);
        iniViews();
        tabView();

    }

    void tabView() {
        Log.d("TAG", "tabView: startinggggggggg");
        if (videoType.equals("WEBSERIES")) {
            TabLayout.Tab episode = binding.tabLayoutMovie.newTab();
            episode.setText("Episodes");
            binding.tabLayoutMovie.addTab(episode);
        }

//        TabLayout.Tab episode = binding.tabLayoutMovie.newTab();
//        episode.setText("Episodes");
//        binding.tabLayoutMovie.addTab(episode);
        TabLayout.Tab favorites = binding.tabLayoutMovie.newTab();
        favorites.setText("More Like This");
        binding.tabLayoutMovie.addTab(favorites);
        binding.tabLayoutMovie.setTabGravity(TabLayout.GRAVITY_FILL);



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
        Log.d("TAG", "tabView: endinggggggggg");

    }

    void iniViews()  {
        movieTitle = getIntent().getExtras().getString("title");
        imagedesc = getIntent().getExtras().getString("imgDescription");
        cast = getIntent().getExtras().getString("Cast");
        duration =getIntent().getExtras().getString("duration");
        videoUrl = getIntent().getExtras().getString("videourl");
        Genres =getIntent().getExtras().getString("Geners");
        Director = getIntent().getExtras().getString("Directors");
        img =getIntent().getExtras().getString("imgURL");
        channelid =getIntent().getExtras().getString("channelID");
        videoType =getIntent().getExtras().getString("videotype");


        Glide.with(this).load(img).into(binding.detailMovieImg);
        Log.d("TAG", "OOOOOOO: "+cast);
        binding.detailMovieTitle.setText(movieTitle);
        SharedPrefs.getInstance().addString(VIDEO_BANNER_Name, movieTitle);
        SharedPrefs.getInstance().getInt(VIDEO_channelID, channelID);
        binding.TvInfo.setText("DESCRIPTION : " +imagedesc);
        binding.TvDesc.setText("CAST : "+cast + "\n" +"GENERS : " + Genres + "\n" +"DIRECTOR : " +Director);

        // setup animation
        binding.detailMovieImg.setAnimation(AnimationUtils.loadAnimation(this, R.anim.scale_animation));



        binding.playFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPrefs.getInstance().addString(VIDEO_BANNER, videoUrl);
                SharedPrefs.getInstance().addString(VIDEO_BANNER_Name, movieTitle);
                Log.d("TAG", "onClickvideo: " + videoUrl);
                Intent intent = new Intent(MovieDetailActivity.this, PlayMovieActivity.class);
                startActivity(intent);

            }
        });

        binding.btnFev.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
//                    Toast.makeText(MovieDetailActivity.this, "chceked", Toast.LENGTH_SHORT).show();
                    binding.btnFev.setBackgroundResource(R.drawable.ic_heart_shape_silhouette);
                    favDB  = new FavDB(MovieDetailActivity.this);
                    String favimg =getIntent().getExtras().getString("imgURL");
                    favDB.insertIntoTheDatabase("",favimg,favimg,"1");
                    Log.i("TAG", "ffffffffffffffff: "+favimg);
//                    Toast.makeText(MovieDetailActivity.this, "Favourite has been Saved  "+ favimg, Toast.LENGTH_SHORT).show();
                } else {
                    binding.btnFev.setBackgroundResource(R.drawable.ic_fav_unselected);
//                    Toast.makeText(MovieDetailActivity.this, "unchceked", Toast.LENGTH_SHORT).show();

                    //Show "Removed from favourite" toast
                }
            }
        });

        binding.btnAddback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent i = new Intent(MovieDetailActivity.this, Home_Activity.class);
//                startActivity(i);

            }
        });



    }

//    @Override
//    public void onBackPressed() {
//        // code here to show dialog
//        super.onBackPressed();  // optional depending on your needs
//    }


    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: enterd");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause: enterd");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop: enterd");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "onRestart: enterd");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart: enterd");
    }

    @Override
    public void onMovieClick(AllDataPojo movie, ImageView movieImageView) {

    }
}
