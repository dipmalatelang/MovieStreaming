package com.netflix.app.videos;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;

import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.netflix.app.R;
import com.netflix.app.databinding.FragmentAllVideosBinding;
import com.netflix.app.home.adapter.MovieAdapter;

import com.netflix.app.home.adapter.MovieItemClickListener;
import com.netflix.app.home.model.AllDataPojo;
import com.netflix.app.home.ui.MovieDetailActivity;
import com.netflix.app.home.viewmodels.AllVideosFragmentViewModel;

import java.lang.reflect.GenericSignatureFormatError;
import java.util.List;

import static android.content.ContentValues.TAG;

public class AllVideos_Fragment extends Fragment implements MovieItemClickListener {

    private FragmentAllVideosBinding binding;
    private AllVideosFragmentViewModel mallVideosFragmentViewModel;
    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_all_videos, container, false);
        view = binding.getRoot();
        // Assign variable mhomeFragmentViewModel for HomeFragmentViewModel
        mallVideosFragmentViewModel = ViewModelProviders.of(this).get(AllVideosFragmentViewModel.class);
        // init Retrive data from Repository SlideDataRepository
        mallVideosFragmentViewModel.init();
        // observe the changes  getSlideData

        mallVideosFragmentViewModel.getAllData().observe(this, new Observer<List<AllDataPojo>>() {
            @Override
            public void onChanged(List<AllDataPojo> allVideos) {
                Log.d(TAG, "onChanged: " + allVideos.size());
                if (allVideos != null) {
                    iniAllVideos();
                } else {
                    Toast.makeText(getContext(), "Data Not found", Toast.LENGTH_SHORT).show();
                }

            }
        });



        /* ToDo iniAllVideos to set all video in recycler view */

        return view;
    }

    private void iniAllVideos() {
        MovieAdapter movieAdapter = new MovieAdapter(getContext(), mallVideosFragmentViewModel.getAllData().getValue(), AllVideos_Fragment.this);
        binding.allVideosRecyclerview.setAdapter(movieAdapter);
        binding.allVideosRecyclerview.setLayoutManager(new GridLayoutManager(getContext(), 3, GridLayoutManager.VERTICAL, false));
    }




    @Override
    public void onMovieClick(AllDataPojo video, ImageView movieImageView) {
        Intent intent = new Intent(getContext(), MovieDetailActivity.class);
        // send movie information to deatilActivity
        String cast = new String();
        for (int i = 0; i < video.getCasts().size(); i++) {
            cast = cast + video.getCasts().get(i).getName() + ",";
            System.out.println("cast" + video.getCasts().size());
        }
        String dir = new String();
        for (int i2 = 0; i2 < video.getDirectors().size(); i2++) {
            dir = dir + video.getDirectors().get(i2).getDirName() + ",";
            System.out.println("cast" + video.getDirectors().size());
        }
        String gener = new String();
        for (int i3 = 0; i3 < video.getGenres().size(); i3++) {
            gener = gener + video.getGenres().get(i3).getGenreName() + ",";
            System.out.println("cast" + video.getGenres().size());
        }


        intent.putExtra("title", video.getTitle());
        intent.putExtra("imgURL", video.getThumbs());
        intent.putExtra("imgDescription", video.getDescription());
        intent.putExtra("Cast", cast);
        intent.putExtra("videourl", video.getVdoUrl());
        intent.putExtra("Geners", gener);
        intent.putExtra("Directors", dir);
        intent.putExtra("duration", (Bundle) video.getDuration());
        intent.putExtra("videotype", video.getVideoType());
        Log.d(TAG, "onMovieClickclick: " + video.getThumbs());

        // lets crezte the animation
        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation((Activity) getContext(),
                movieImageView, "sharedName");

        startActivity(intent, options.toBundle());
        // i l make a simple test to see if the click works

//        Toast.makeText(getContext(), "item clicked : " + video.getChannelId(), Toast.LENGTH_LONG).show();
        // it works great

    }
}
