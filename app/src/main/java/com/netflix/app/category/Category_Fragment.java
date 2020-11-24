package com.netflix.app.category;

import android.content.Intent;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.netflix.app.R;
import com.netflix.app.category.adapter.CategoryAdapter;
import com.netflix.app.databinding.CFragmentCategoryBinding;
import com.netflix.app.home.adapter.MovieItemClickListener;
import com.netflix.app.home.model.AllVideo;
import com.netflix.app.home.ui.PlayMovieActivity;
import com.netflix.app.home.viewmodels.AllVideosFragmentViewModel;
import com.netflix.app.utlis.SharedPrefs;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static android.content.ContentValues.TAG;
import static com.netflix.app.home.adapter.SliderPagerAdapter.VIDEO_BANNER;
import static com.netflix.app.home.adapter.SliderPagerAdapter.VIDEO_BANNER_Name;
import static java.util.Collections.sort;


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


        mallVideosFragmentViewModel.getAllData().observe(this, new Observer<List<AllVideo>>() {
            @Override
            public void onChanged(List<AllVideo> allVideos) {
                if (allVideos != null) {

                    Collections.reverse(allVideos);
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

    private void iniAllCategory() {
        CategoryAdapter categoryAdapter = new CategoryAdapter(getContext(), mallVideosFragmentViewModel.getAllData().getValue(), Category_Fragment.this);
        binding.allcategoryRecyclerview.setAdapter(categoryAdapter);
        binding.allcategoryRecyclerview.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
    }

    @Override
    public void onMovieClick(AllVideo movie, ImageView movieImageView) {

        String bannerurl =movie.getVdoUrl();
        String  bannername = movie.getTitle();
        SharedPrefs.getInstance().addString(VIDEO_BANNER, bannerurl);
        SharedPrefs.getInstance().addString(VIDEO_BANNER_Name,bannername);
        startActivity(new Intent(getContext(), PlayMovieActivity.class));



    }

    public static Comparator<AllVideo> StuRollno = new Comparator<AllVideo>() {

        public int compare(AllVideo s1, AllVideo s2) {

           String rollno1 = s1.getViews();
            String rollno2 = s2.getViews();

            /*For ascending order*/
            return rollno1.compareTo(rollno2);

            /*For descending order*/
            //rollno2-rollno1;
        }};


}

