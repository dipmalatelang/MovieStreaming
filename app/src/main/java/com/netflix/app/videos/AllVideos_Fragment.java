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
import androidx.recyclerview.widget.RecyclerView;

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
import com.netflix.app.home.adapter.SliderPagerAdapter;
import com.netflix.app.home.model.AllVideo;
import com.netflix.app.home.model.CategoryItem;
import com.netflix.app.home.model.MovieData;
import com.netflix.app.home.ui.MovieDetailActivity;
import com.netflix.app.home.viewmodels.AllVideosFragmentViewModel;
import com.netflix.app.home.viewmodels.HomeFragmentViewModel;
import com.netflix.app.utlis.DataSources;

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
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_all_videos,container,false);
        view = binding.getRoot();
        // Assign variable mhomeFragmentViewModel for HomeFragmentViewModel
        mallVideosFragmentViewModel = ViewModelProviders.of(this).get(AllVideosFragmentViewModel.class);
        // init Retrive data from Repository SlideDataRepository
        mallVideosFragmentViewModel.init();
        // observe the changes  getSlideData

        mallVideosFragmentViewModel.getAllData().observe(this, new Observer<List<AllVideo>>() {
            @Override
            public void onChanged(List<AllVideo> allVideos) {
                Log.d(TAG, "onChanged: "+allVideos.size());
                if(allVideos !=null){
                    iniAllVideos();
                }
                else {
                    Toast.makeText(getContext(), "Data Not found", Toast.LENGTH_SHORT).show();
                }

            }
        });



        /* ToDo iniAllVideos to set all video in recycler view */

        return  view;
    }
    private void iniAllVideos(){
        MovieAdapter movieAdapter = new MovieAdapter(getContext(), mallVideosFragmentViewModel.getAllData().getValue(),AllVideos_Fragment.this);
        binding.allVideosRecyclerview.setAdapter(movieAdapter);
        binding.allVideosRecyclerview.setLayoutManager(new GridLayoutManager(getContext(),3,GridLayoutManager.VERTICAL,false));
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
        intent.putExtra("genres", video.getGenresModels());
        intent.putExtra("director", video.getDirectorModels());

        Log.d(TAG, "onMovieClickclick: "+video.getThumbs());

        // lets crezte the animation
        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation((Activity) getContext(),
                movieImageView, "sharedName");

        startActivity(intent, options.toBundle());
        // i l make a simple test to see if the click works

        Toast.makeText(getContext(), "item clicked : " + video.getChannelId(), Toast.LENGTH_LONG).show();
        // it works great


    }
}
