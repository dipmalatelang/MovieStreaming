package com.netflix.app.home.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.netflix.app.R;
import com.netflix.app.category.adapter.CategoryAdapter;
import com.netflix.app.home.adapter.MovieItemClickListener;
import com.netflix.app.home.adapter.SeasonAdapter;
import com.netflix.app.home.model.CategoryItem;
import com.netflix.app.utlis.DataSources;


public class EpisodesFragment extends Fragment  {
    RecyclerView recyclerview_season;

    public EpisodesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_episodes, container, false);
        recyclerview_season = view.findViewById(R.id.recyclerview_season);
        inisesosn();
        return view;
    }
    private void inisesosn() {
        SeasonAdapter seasonAdapter = new SeasonAdapter(getContext(), DataSources.getmovie());
        recyclerview_season.setAdapter(seasonAdapter);
        recyclerview_season.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
    }}
