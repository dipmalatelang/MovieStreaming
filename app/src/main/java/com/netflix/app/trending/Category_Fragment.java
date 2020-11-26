package com.netflix.app.trending;

import android.content.Intent;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.netflix.app.R;
import com.netflix.app.home.model.AllDataPojo;
import com.netflix.app.trending.adapter.CategoryAdapter;
import com.netflix.app.databinding.CFragmentCategoryBinding;
import com.netflix.app.home.adapter.MovieItemClickListener;
import com.netflix.app.home.ui.PlayMovieActivity;
import com.netflix.app.home.viewmodels.AllVideosFragmentViewModel;
import com.netflix.app.utlis.SharedPrefs;

import java.util.Collections;

import java.util.List;


import static com.netflix.app.home.adapter.SliderPagerAdapter.VIDEO_BANNER;
import static com.netflix.app.home.adapter.SliderPagerAdapter.VIDEO_BANNER_Name;


public class Category_Fragment extends Fragment implements MovieItemClickListener {
    private CFragmentCategoryBinding binding;
    private AllVideosFragmentViewModel mallVideosFragmentViewModel;

    private View view;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.c_fragment_category, container, false);
        view = binding.getRoot();

        /*TODO Create iniAllCategory  for category data in recycler view*/
        mallVideosFragmentViewModel = ViewModelProviders.of(this).get(AllVideosFragmentViewModel.class);
        mallVideosFragmentViewModel.init();


        mallVideosFragmentViewModel.getAllData().observe(this, new Observer<List<AllDataPojo>>() {
            @Override
            public void onChanged(List<AllDataPojo> allVideos) {
                if (allVideos != null) {

                    Collections.sort(allVideos);
                    CategoryAdapter categoryAdapter = new CategoryAdapter(getContext(), allVideos, Category_Fragment.this);
                    binding.allcategoryRecyclerview.setAdapter(categoryAdapter);
                    binding.allcategoryRecyclerview.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));

                } else {
                    Toast.makeText(getContext(), "Data Not found", Toast.LENGTH_SHORT).show();
                }

            }
        });


        return view;
    }




    @Override
    public void onMovieClick(AllDataPojo movie, ImageView movieImageView) {
        String bannerurl =movie.getVdoUrl();
        String  bannername = movie.getTitle();
        SharedPrefs.getInstance().addString(VIDEO_BANNER, bannerurl);
        SharedPrefs.getInstance().addString(VIDEO_BANNER_Name,bannername);
        startActivity(new Intent(getContext(), PlayMovieActivity.class));

    }
}

