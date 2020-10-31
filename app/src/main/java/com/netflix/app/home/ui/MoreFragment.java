package com.netflix.app.home.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.netflix.app.R;
import com.netflix.app.favorites.Favorites_Fragment;
import com.netflix.app.home.adapter.MoreAdapter;
import com.netflix.app.home.adapter.MovieAdapter;
import com.netflix.app.home.adapter.MovieItemClickListener;
import com.netflix.app.home.model.CategoryItem;
import com.netflix.app.utlis.DataSources;


public class MoreFragment extends Fragment implements MovieItemClickListener {
    RecyclerView recyclerview_more;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_more, container, false);
        recyclerview_more = view.findViewById(R.id.recyclerview_more);
        iniFavorites();

        return view;
    }
    private void iniFavorites(){
        MoreAdapter moreAdapter = new MoreAdapter(getContext(), DataSources.getmovie(), MoreFragment.this);
        recyclerview_more.setAdapter(moreAdapter);
        recyclerview_more.setLayoutManager(new GridLayoutManager(getContext(),3,GridLayoutManager.VERTICAL,false));
    }

    @Override
    public void onMovieClick(CategoryItem movie, ImageView movieImageView) {

    }
}
