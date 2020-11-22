package com.netflix.app.home.ui;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.netflix.app.R;
import com.netflix.app.databinding.HFragmentHomeBinding;
import com.netflix.app.home.adapter.MainRecyclerAdapter;
import com.netflix.app.home.adapter.MovieItemClickListener;
import com.netflix.app.home.model.AllVideo;
import com.netflix.app.home.model.SlidePojo;
import com.netflix.app.home.adapter.SliderPagerAdapter;
import com.netflix.app.home.model.AllCategory;
import com.netflix.app.home.model.CategoryItem;
import com.netflix.app.home.model.VideoTypePojo;
import com.netflix.app.home.viewmodels.AllVideosFragmentViewModel;
import com.netflix.app.home.viewmodels.HomeFragmentViewModel;
import com.netflix.app.networks.Api;
import com.netflix.app.networks.Constant;
import com.netflix.app.utlis.VideoHeader;
import com.netflix.app.utlis.VideoHeaderItem;
import com.netflix.app.utlis.VideoTypeItem;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.content.ContentValues.TAG;


public class HomeFragment extends Fragment implements MovieItemClickListener {
    private MainRecyclerAdapter mainRecyclerAdapter;

    //Initialize HomeFragmentViewModel for Viewmodel class
    private HomeFragmentViewModel mhomeFragmentViewModel;
    private AllVideosFragmentViewModel mallVideosFragmentViewModel;

    //Initialize variable
    private HFragmentHomeBinding binding;
    private View hview;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Assign variable
        binding = DataBindingUtil.inflate(inflater, R.layout.h_fragment_home_, container, false);
        hview = binding.getRoot();


        // Assign variable mhomeFragmentViewModel for HomeFragmentViewModel
        mhomeFragmentViewModel = ViewModelProviders.of(this).get(HomeFragmentViewModel.class);
        mallVideosFragmentViewModel = ViewModelProviders.of(this).get(AllVideosFragmentViewModel.class);
        // init Retrive data from Repository SlideDataRepository
        mhomeFragmentViewModel.init();
        mallVideosFragmentViewModel.init();
        // observe the changes  getSlideData
        mhomeFragmentViewModel.getSlideData().observe(this, new Observer<List<AllVideo>>() {
            @Override
            public void onChanged(List<AllVideo> allVideos) {
                Log.d(TAG, "onChanged: " + allVideos.size());
                if (allVideos != null) {
                    SliderPagerAdapter sliderPagerAdapter = new SliderPagerAdapter(getContext(), mhomeFragmentViewModel.getSlideData().getValue());

                    binding.sliderpager.setAdapter(sliderPagerAdapter);
                    binding.progressBar.setVisibility(View.GONE);
                } else {
                    Toast.makeText(getContext(), "Data not found", Toast.LENGTH_SHORT).show();
                }

            }
        });

        mallVideosFragmentViewModel.getAllData().observe(this, new Observer<List<AllVideo>>() {
            @Override
            public void onChanged(List<AllVideo> allCategoryList) {

                ArrayList<AllVideo> webseries = new ArrayList<>();
                for (AllVideo al : allCategoryList) {
                    if (al.getVideoType().equalsIgnoreCase("WEBSERIES")) {
                        webseries.add(al);

//                    } else if (al.getVideoType().equalsIgnoreCase("SORTMOVIE")) {
//                        sortvideo.add(al);
//                    } else if (al.getVideoType().equalsIgnoreCase("MOVIE")) {
//                        movie.add(al);
//                    } else if (al.getVideoType().equalsIgnoreCase("SINGLEVIDEO")) {
//                        music.add(al);
                    } else {
//                        webseries.add(null);
                        Log.d(TAG, "onChanged: data not found");

                    }

                }


                List<VideoTypeItem> videoTypeItems = new ArrayList<>();
                videoTypeItems.add(new VideoHeader("WEBSERIES"));
                Log.d(TAG, "videoTypeItems: " + videoTypeItems);
//              videoTypeItems.add(new VideoHeaderItem(webseries, getContext()));

            }

        });
        iniViews(hview);
        iniSlider();
        return hview;


    }

    private void iniViews(View view) {

    }

    /* ToDo nested recycler view setMainCategoryRecycler */
    private void setMainCategoryRecycler(List<VideoTypePojo> alv) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        binding.mainRecycler.setLayoutManager(layoutManager);
        mainRecyclerAdapter = new MainRecyclerAdapter(getContext(), alv, HomeFragment.this);
        Log.d(TAG, "setMainCategoryRecycler: " + alv.get(0).getVideotype());
        binding.mainRecycler.setAdapter(mainRecyclerAdapter);
    }


    /* ToDo iniSlider setTimer for slide */
    private void iniSlider() {

        // setup timer
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new SliderTimer(), 4000, 6000);
        binding.indicator.setupWithViewPager(binding.sliderpager, true);
    }

    @Override
    public void onMovieClick(AllVideo video, ImageView movieImageView) {

        Intent intent = new Intent(getContext(), MovieDetailActivity.class);
        // send movie information to deatilActivity
        intent.putExtra("title", video.getTitle());
        intent.putExtra("imgURL", video.getThumbs());
        intent.putExtra("imgDescription", video.getDescription());
        intent.putExtra("imginfo", video.getCastModels());
        intent.putExtra("videourl", video.getVdoUrl());


        Log.d(TAG, "onMovieClickclick: " + video.getTitle());

        // lets crezte the animation
        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation((Activity) getContext(),
                movieImageView, "sharedName");

        startActivity(intent, options.toBundle());
        // i l make a simple test to see if the click works
        Toast.makeText(getContext(), "item clicked : " + video.getChannelId(), Toast.LENGTH_LONG).show();
        // it works great


    }

    class SliderTimer extends TimerTask {

        @Override
        public void run() {
            if (getActivity() != null) {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (binding.sliderpager.getCurrentItem() < mhomeFragmentViewModel.getSlideData().getValue().size() - 1) {
                            binding.sliderpager.setCurrentItem(binding.sliderpager.getCurrentItem() + 1);
                        } else
                            binding.sliderpager.setCurrentItem(0);
                    }
                });

            }


        }
    }


}
