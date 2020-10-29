package com.netflix.app.favorites;

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

public class Favorites_Fragment extends Fragment implements MovieItemClickListener {
    RecyclerView favorite_recyclerview;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.h_favorite_fragment, container, false);
        favorite_recyclerview = view.findViewById(R.id.favorite_recyclerview);
        iniFavorites();
        return view;

    }

    private void iniFavorites(){
        MovieAdapter movieAdapter = new MovieAdapter(getContext(), DataSources.getmovie(), Favorites_Fragment.this);
        favorite_recyclerview.setAdapter(movieAdapter);
        favorite_recyclerview.setLayoutManager(new GridLayoutManager(getContext(),3,GridLayoutManager.VERTICAL,false));
    }





    @Override
    public void onMovieClick(CategoryItem movie, ImageView movieImageView) {

    }
}

