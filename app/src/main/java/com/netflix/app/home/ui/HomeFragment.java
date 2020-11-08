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

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.netflix.app.R;
import com.netflix.app.databinding.HFragmentHomeBinding;
import com.netflix.app.home.adapter.MainRecyclerAdapter;
import com.netflix.app.home.adapter.MovieItemClickListener;
import com.netflix.app.home.model.SlidePojo;
import com.netflix.app.home.adapter.SliderPagerAdapter;
import com.netflix.app.home.model.AllCategory;
import com.netflix.app.home.model.CategoryItem;
import com.netflix.app.home.viewmodels.HomeFragmentViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import static android.content.ContentValues.TAG;


public class HomeFragment extends Fragment implements MovieItemClickListener {
    private MainRecyclerAdapter mainRecyclerAdapter;

    //Initialize HomeFragmentViewModel for Viewmodel class
    private HomeFragmentViewModel mhomeFragmentViewModel;

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
        // init Retrive data from Repository SlideDataRepository
        mhomeFragmentViewModel.init("slider");
        // observe the changes  getSlideData
        mhomeFragmentViewModel.getSlideData().observe(this, new Observer<List<SlidePojo>>() {
            @Override
            public void onChanged(List<SlidePojo> slidePojos) {
                if (slidePojos != null) {
                    Log.d(TAG, "onChanged: " + slidePojos.size());
                    SliderPagerAdapter  sliderPagerAdapter = new SliderPagerAdapter(getContext(), mhomeFragmentViewModel.getSlideData().getValue());
                    binding.sliderpager.setAdapter(sliderPagerAdapter);
                    binding.progressBar.setVisibility(View.GONE);
                } else {
                    Toast.makeText(getContext(), "Data not found", Toast.LENGTH_SHORT).show();
                    Log.d(TAG, "not found: ");
                }
            }
        });


        iniViews(hview);
        iniSlider();
        return hview;

    }

    private void iniViews(View view) {

        List<CategoryItem> categoryItemList = new ArrayList<>();
        categoryItemList.add(new CategoryItem(1, R.drawable.hollywood5));
        categoryItemList.add(new CategoryItem(1, R.drawable.hollywood4));
        categoryItemList.add(new CategoryItem(1, R.drawable.hollywood5));
        categoryItemList.add(new CategoryItem(1, R.drawable.hollywood4));
        categoryItemList.add(new CategoryItem(1, R.drawable.hollywood5));
        categoryItemList.add(new CategoryItem(1, R.drawable.hollywood4));

        // added in second category
        List<CategoryItem> categoryItemList2 = new ArrayList<>();
        categoryItemList2.add(new CategoryItem(1, R.drawable.bestofoscar1));
        categoryItemList2.add(new CategoryItem(1, R.drawable.bestofoscar2));
        categoryItemList2.add(new CategoryItem(1, R.drawable.bestofoscar1));
        categoryItemList2.add(new CategoryItem(1, R.drawable.bestofoscar2));
        categoryItemList2.add(new CategoryItem(1, R.drawable.bestofoscar1));
        categoryItemList2.add(new CategoryItem(1, R.drawable.bestofoscar2));

        // added in 3rd category
        List<CategoryItem> categoryItemList3 = new ArrayList<>();
        categoryItemList3.add(new CategoryItem(1, R.drawable.moviedubbedinhindi1));
        categoryItemList3.add(new CategoryItem(1, R.drawable.moviedubbedinhindi2));
        categoryItemList3.add(new CategoryItem(1, R.drawable.moviedubbedinhindi1));
        categoryItemList3.add(new CategoryItem(1, R.drawable.moviedubbedinhindi2));
        categoryItemList3.add(new CategoryItem(1, R.drawable.moviedubbedinhindi1));
        categoryItemList3.add(new CategoryItem(1, R.drawable.moviedubbedinhindi2));
        List<AllCategory> allCategoryList = new ArrayList<>();
        allCategoryList.add(new AllCategory("Hollywood", categoryItemList));
        allCategoryList.add(new AllCategory("Best of Oscars", categoryItemList2));
        allCategoryList.add(new AllCategory("Movies Dubbed in Hindi", categoryItemList3));

        setMainCategoryRecycler(allCategoryList);
    }

    /* ToDo nested recycler view setMainCategoryRecycler */
    private void setMainCategoryRecycler(List<AllCategory> allCategoryList) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        binding.mainRecycler.setLayoutManager(layoutManager);
        mainRecyclerAdapter = new MainRecyclerAdapter(getContext(), allCategoryList, HomeFragment.this);
        binding.mainRecycler.setAdapter(mainRecyclerAdapter);
    }


    public void onMovieClick(CategoryItem movie, ImageView movieImageView) {


        Intent intent = new Intent(getContext(), MovieDetailActivity.class);
        // send movie information to deatilActivity
        intent.putExtra("title", movie.getItemId());
        intent.putExtra("imgURL", movie.getImageUrl());
//        intent.putExtra("imgCover",movie.getCoverPhoto());
        // lets crezte the animation
        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation((Activity) getContext(),
                movieImageView, "sharedName");

        startActivity(intent, options.toBundle());
        // i l make a simple test to see if the click works

        Toast.makeText(getContext(), "item clicked : " + movie.getItemId(), Toast.LENGTH_LONG).show();
        // it works great


    }




    /* ToDo iniSlider setTimer for slide */
    private void iniSlider() {

        // setup timer
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new SliderTimer(), 4000, 6000);
        binding.indicator.setupWithViewPager(binding.sliderpager, true);
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
