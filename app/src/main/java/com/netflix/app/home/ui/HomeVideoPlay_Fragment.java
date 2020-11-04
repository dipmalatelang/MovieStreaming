package com.netflix.app.home.ui;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.netflix.app.R;
import com.netflix.app.home.adapter.MovieAdapter;
import com.netflix.app.home.adapter.MovieItemClickListener;
import com.netflix.app.home.model.CategoryItem;
import com.netflix.app.home.model.MovieData;
import com.netflix.app.utlis.DataSources;
import com.netflix.app.videos.AllVideos_Fragment;

public class HomeVideoPlay_Fragment extends Fragment implements MovieItemClickListener {
    RecyclerView allVideos_recyclerview;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home_video_play_, container, false);
        allVideos_recyclerview = view.findViewById(R.id.allVideos_recyclerview);
//        showOtherFragment();
        iniAllVideos();
        return view;
    }
    private void iniAllVideos(){
        MovieAdapter movieAdapter = new MovieAdapter(getContext(), DataSources.getmovie(), HomeVideoPlay_Fragment.this);
        allVideos_recyclerview.setAdapter(movieAdapter);
        allVideos_recyclerview.setLayoutManager(new GridLayoutManager(getContext(),3,GridLayoutManager.VERTICAL,false));
    }
//
//    @Override
//    public void onMovieClick(MovieData movie, ImageView movieImageView) {
//        Intent intent = new Intent(getContext(),MovieDetailActivity.class);
//
//        // send movie information to deatilActivity
//        intent.putExtra("title",movie.getTitle());
//        intent.putExtra("imgURL",movie.getThumbnail());
//        intent.putExtra("imgCover",movie.getCoverPhoto());
//        // lets crezte the animation
//        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation((Activity) getContext(),
//                movieImageView,"sharedName");
//
//        startActivity(intent,options.toBundle());
//
//
//
//        // i l make a simple test to see if the click works
//
//        Toast.makeText(getContext(),"item clicked : " + movie.getTitle(),Toast.LENGTH_LONG).show();
//        // it works great
//
//    }






    @Override
    public void onMovieClick(CategoryItem movie, ImageView movieImageView) {
        Intent intent = new Intent(getContext(),MovieDetailActivity.class);
        intent.putExtra("title",movie.getItemId());
        intent.putExtra("imgURL",movie.getImageUrl());
//        intent.putExtra("imgCover",movie.getCoverPhoto());
        // lets crezte the animation
        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation((Activity) getContext(),
                movieImageView,"sharedName");

        startActivity(intent,options.toBundle());



        // i l make a simple test to see if the click works

        Toast.makeText(getContext(),"item clicked : " + movie.getItemId(),Toast.LENGTH_LONG).show();
        // it works great

    }
}
