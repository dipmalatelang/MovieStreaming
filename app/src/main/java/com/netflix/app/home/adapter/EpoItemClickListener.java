package com.netflix.app.home.adapter;

import android.widget.ImageView;

import com.netflix.app.home.model.AllDataPojo.Ep;


public interface EpoItemClickListener {

    void onMovieClick(Ep movie, ImageView movieImageView); // we will need the imageview to make the shared animation between the two activity

}
