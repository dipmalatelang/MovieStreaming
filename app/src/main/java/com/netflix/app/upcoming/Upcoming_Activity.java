package com.netflix.app.upcoming;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.netflix.app.R;
import com.netflix.app.home.adapter.MovieItemClickListener;
import com.netflix.app.home.model.AllDataPojo;
import com.netflix.app.home.ui.PlayMovieActivity;
import com.netflix.app.home.viewmodels.AllVideosFragmentViewModel;
import com.netflix.app.utlis.SharedPrefs;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static com.netflix.app.home.adapter.SliderPagerAdapter.VIDEO_BANNER;
import static com.netflix.app.home.adapter.SliderPagerAdapter.VIDEO_BANNER_Name;

public class Upcoming_Activity extends AppCompatActivity implements MovieItemClickListener {
    RecyclerView recyclerview_upcoming;
    private AllVideosFragmentViewModel mallVideosFragmentViewModel;
    private View view;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.h_activity_upcoming);
        recyclerview_upcoming = findViewById(R.id.recyclerview_upcoming);
        mallVideosFragmentViewModel = ViewModelProviders.of(this).get(AllVideosFragmentViewModel.class);
        // init Retrive data from Repository SlideDataRepository
        mallVideosFragmentViewModel.init();
        // observe the changes  getSlideData

        mallVideosFragmentViewModel.getAllData().observe(this, new Observer<List<AllDataPojo>>() {
            @Override
            public void onChanged(List<AllDataPojo> allVideos) {

                if(allVideos !=null){
                    ArrayList<AllDataPojo> garda = new ArrayList<>();
                    ArrayList<AllDataPojo> alvdo = new ArrayList<>(mallVideosFragmentViewModel.getAllData().getValue());
                    ArrayList<AllDataPojo> allV = new ArrayList<>();
                    for (int i = alvdo.size() - 1; i >= 0; i--) {
                        allV.add(alvdo.get(i));
                    }
                    Iterator<AllDataPojo> it = allV.iterator();
                    while (it.hasNext()) {
                        AllDataPojo al = it.next();
                        if (al.getVideoType().equalsIgnoreCase("MOVIE")) {
                            garda.add(al);
                        }
                    }

                    UpcomingAdapter upcomingAdapter = new UpcomingAdapter(Upcoming_Activity.this, garda, Upcoming_Activity.this);
                    recyclerview_upcoming.setAdapter(upcomingAdapter);
                    recyclerview_upcoming.setLayoutManager(new LinearLayoutManager(Upcoming_Activity.this,LinearLayoutManager.VERTICAL,false));

                }
                else {
                    Toast.makeText(Upcoming_Activity.this, "Data Not found", Toast.LENGTH_SHORT).show();
                }

            }
        });


    }



    @Override
    public void onMovieClick(AllDataPojo movie, ImageView movieImageView) {
        String bannerurl =movie.getVdoUrl();
        String  bannername = movie.getTitle();
        SharedPrefs.getInstance().addString(VIDEO_BANNER, bannerurl);
        SharedPrefs.getInstance().addString(VIDEO_BANNER_Name,bannername);
        startActivity(new Intent(this, PlayMovieActivity.class));
    }
}
