package com.netflix.app.videos;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.netflix.app.R;
import com.netflix.app.databinding.FragmentAllVideosBinding;
import com.netflix.app.home.adapter.MovieAdapter;
import com.netflix.app.home.adapter.MovieItemClickListener;
import com.netflix.app.home.model.CategoryItem;
import com.netflix.app.home.model.MovieData;
import com.netflix.app.utlis.DataSources;

public class AllVideos_Fragment extends Fragment implements MovieItemClickListener {
   RecyclerView allVideos_recyclerview;
   private FragmentAllVideosBinding binding;
   private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_all_videos,container,false);
        view = binding.getRoot();
        /* ToDo iniAllVideos to set all video in recycler view */
        iniAllVideos();
        return  view;
    }
    private void iniAllVideos(){
        MovieAdapter movieAdapter = new MovieAdapter(getContext(), DataSources.getmovie(),AllVideos_Fragment.this);
        binding.allVideosRecyclerview.setAdapter(movieAdapter);
        binding.allVideosRecyclerview.setLayoutManager(new GridLayoutManager(getContext(),3,GridLayoutManager.VERTICAL,false));
    }



    @Override
    public void onMovieClick(CategoryItem movie, ImageView movieImageView) {

    }
}
