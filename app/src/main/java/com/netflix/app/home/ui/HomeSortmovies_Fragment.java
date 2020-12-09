package com.netflix.app.home.ui;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;

import com.netflix.app.R;
import com.netflix.app.databinding.FragmentHomeVideoPlayBinding;
import com.netflix.app.home.adapter.MovieAdapter;
import com.netflix.app.home.adapter.MovieItemClickListener;
import com.netflix.app.home.model.AllDataPojo;
import com.netflix.app.home.viewmodels.AllVideosFragmentViewModel;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class HomeSortmovies_Fragment extends Fragment implements MovieItemClickListener {
    private FragmentHomeVideoPlayBinding binding;
    private View view;
    private AllVideosFragmentViewModel mallVideosFragmentViewModel;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_home_video_play_,container,false);
        view = binding.getRoot();

        mallVideosFragmentViewModel = ViewModelProviders.of(this).get(AllVideosFragmentViewModel .class);
        mallVideosFragmentViewModel.init();


            mallVideosFragmentViewModel.getAllData().observe(this, new Observer<List<AllDataPojo>>() {
            @Override
            public void onChanged(List<AllDataPojo> allCategoryList) {

                if(allCategoryList !=null){
                    ArrayList<AllDataPojo> werbseries = new ArrayList<>();
                    ArrayList<AllDataPojo> alvdo = new ArrayList<>(mallVideosFragmentViewModel.getAllData().getValue());
                    ArrayList<AllDataPojo> allV = new ArrayList<>();
                    for (int i = alvdo.size() - 1; i >= 0; i--) {
                        allV.add(alvdo.get(i));
                    }
                    Iterator<AllDataPojo> it = allV.iterator();
                    while (it.hasNext()) {
                        AllDataPojo al = it.next();
                        if (al.getVideoType().equalsIgnoreCase("SORTMOVIE")) {
                            werbseries.add(al);

                            if (werbseries.size() == 0) {
                                binding.allVideosRecyclerview.setVisibility(View.GONE);

                            } else {

                                binding.allVideosRecyclerview.setVisibility(View.VISIBLE);

                            }
                        }


                    }

                    MovieAdapter movieAdapter = new MovieAdapter(getContext(), werbseries, HomeSortmovies_Fragment.this);
                    binding.allVideosRecyclerview.setAdapter(movieAdapter);
                    binding.allVideosRecyclerview.setLayoutManager(new GridLayoutManager(getContext(),3,GridLayoutManager.VERTICAL,false));



                }
                else {
                    Toast.makeText(getContext(), "Data Not found", Toast.LENGTH_SHORT).show();
                }

            }
        });


        /*ToDo iniAllVideos for all video recycler view */
//        iniAllVideos();
        return view;
    }




    @Override
    public void onMovieClick(AllDataPojo video, ImageView movieImageView) {
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

        Intent intent = new Intent(getContext(), MovieDetailActivity.class);
        // send movie information to deatilActivity
        intent.putExtra("title", video.getTitle());
        intent.putExtra("imgURL", video.getThumbs());
        intent.putExtra("imgDescription", video.getDescription());
        intent.putExtra("Cast", cast);
        intent.putExtra("Directors", dir);
        intent.putExtra("Geners", gener);
        intent.putExtra("videourl", video.getVdoUrl());


        Log.d("TAG", "onMovieClickclick: " + video.getTitle());

        // lets crezte the animation
        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation((Activity) getContext(),
                movieImageView, "sharedName");

        startActivity(intent, options.toBundle());
        // i l make a simple test to see if the click works
//        Toast.makeText(getContext(), "item clicked : " + video.getChannelId(), Toast.LENGTH_LONG).show();
        // it works great



    }
}
