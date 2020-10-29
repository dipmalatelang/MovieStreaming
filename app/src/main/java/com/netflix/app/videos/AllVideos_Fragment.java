package com.netflix.app.videos;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.netflix.app.R;
import com.netflix.app.home.adapter.MovieAdapter;
import com.netflix.app.home.adapter.MovieItemClickListener;
import com.netflix.app.home.model.CategoryItem;
import com.netflix.app.home.model.MovieData;
import com.netflix.app.utlis.DataSources;

public class AllVideos_Fragment extends Fragment implements MovieItemClickListener {
   RecyclerView allVideos_recyclerview;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_all_videos, container, false);
        allVideos_recyclerview = view.findViewById(R.id.allVideos_recyclerview);
        iniAllVideos();
        return  view;
    }
    private void iniAllVideos(){
        MovieAdapter movieAdapter = new MovieAdapter(getContext(), DataSources.getmovie(),AllVideos_Fragment.this);
        allVideos_recyclerview.setAdapter(movieAdapter);
        allVideos_recyclerview.setLayoutManager(new GridLayoutManager(getContext(),3,GridLayoutManager.VERTICAL,false));
    }



    @Override
    public void onMovieClick(CategoryItem movie, ImageView movieImageView) {

    }
}
