package com.netflix.app.home.ui;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;
import com.netflix.app.R;
import com.netflix.app.databinding.FragmentHomeVideoPlayBinding;
import com.netflix.app.home.adapter.MovieAdapter;
import com.netflix.app.home.adapter.MovieItemClickListener;
import com.netflix.app.home.model.AllVideo;
import com.netflix.app.home.model.CategoryItem;
import com.netflix.app.utlis.DataSources;

public class HomeVideoPlay_Fragment extends Fragment implements MovieItemClickListener {
    private FragmentHomeVideoPlayBinding binding;
    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_home_video_play_,container,false);
        view = binding.getRoot();

        /*ToDo iniAllVideos for all video recycler view */
//        iniAllVideos();
        return view;
    }
//    private void iniAllVideos(){
//        MovieAdapter movieAdapter = new MovieAdapter(getContext(), , HomeVideoPlay_Fragment.this);
//        binding.allVideosRecyclerview.setAdapter(movieAdapter);
//        binding.allVideosRecyclerview.setLayoutManager(new GridLayoutManager(getContext(),3,GridLayoutManager.VERTICAL,false));
//    }



    @Override
    public void onMovieClick(AllVideo movie, ImageView movieImageView) {
        Intent intent = new Intent(getContext(), MovieDetailActivity.class);
        // send movie information to deatilActivity
        intent.putExtra("title", movie.getTitle());
        intent.putExtra("imgURL", movie.getThumbs());
        intent.putExtra("imgDescription",movie.getDescription());
        intent.putExtra("imginfo",movie.getCastModels());
        intent.putExtra("videourl",movie.getVdoUrl());
        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation((Activity) getContext(),
                movieImageView,"sharedName");

        startActivity(intent,options.toBundle());
        Toast.makeText(getContext(),"item clicked : " + movie.getChannelId(),Toast.LENGTH_LONG).show();

    }
}
