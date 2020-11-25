package com.netflix.app.home.ui;

import android.app.Activity;
import android.app.ActivityOptions;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.ListFragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.netflix.app.R;

import com.netflix.app.databinding.HFragmentHomeBinding;
import com.netflix.app.home.adapter.MainRecyclerAdapter;
import com.netflix.app.home.adapter.MovieItemClickListener;
import com.netflix.app.home.model.AllVideo;
import com.netflix.app.home.adapter.SliderPagerAdapter;
import com.netflix.app.home.model.VideoTypePojo;
import com.netflix.app.home.viewmodels.AllVideosFragmentViewModel;
import com.netflix.app.home.viewmodels.HomeFragmentViewModel;
import com.netflix.app.upcoming.Upcoming_Activity;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


import static android.content.ContentValues.TAG;


public class HomeFragment extends ListFragment implements MovieItemClickListener {
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
        setHasOptionsMenu(true);
        binding = DataBindingUtil.inflate(inflater, R.layout.h_fragment_home_, container, false);

        hview = binding.getRoot();
        ((AppCompatActivity) getActivity()).setSupportActionBar(binding.topToolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(false);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);


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
//                Log.d(TAG, "onChanged: " + allVideos.size());
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

            }

        });
        iniSlider();
        return hview;
    }
    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        menu.clear();
        inflater.inflate(R.menu.top_bar_menu, menu);
        MenuItem item = menu.findItem(R.id.action_search);

        SearchView searchView = new SearchView(((AppCompatActivity) getContext()).getSupportActionBar().getThemedContext());

        item.setShowAsAction(MenuItem.SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW | MenuItem.SHOW_AS_ACTION_IF_ROOM);
        item.setActionView(searchView);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        searchView.setOnClickListener(new View.OnClickListener() {
                                          @Override
                                          public void onClick(View v) {
                                          }
                                      }
        );


    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.t_upcoming:
                startActivity(new Intent(getContext(), Upcoming_Activity.class));
                Toast.makeText(getContext(), "click upcoming", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
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
                        try {
                            if (binding.sliderpager.getCurrentItem() < mhomeFragmentViewModel.getSlideData().getValue().size() - 1) {
                                binding.sliderpager.setCurrentItem(binding.sliderpager.getCurrentItem() + 1);
                            } else {
                                binding.sliderpager.setCurrentItem(0);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });

            }


        }
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
    }


}
