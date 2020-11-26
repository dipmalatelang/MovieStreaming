package com.netflix.app.home.adapter;

import android.widget.ImageView;


import com.netflix.app.home.model.AllDataPojo;


public interface MovieItemClickListener {

    void onMovieClick(AllDataPojo movie, ImageView movieImageView); // we will need the imageview to make the shared animation between the two activity

}
