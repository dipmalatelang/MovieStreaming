package com.netflix.app.home.adapter;

import android.widget.ImageView;


import com.netflix.app.home.model.AllVideo;



public interface MovieItemClickListener {

    void onMovieClick(AllVideo movie, ImageView movieImageView); // we will need the imageview to make the shared animation between the two activity

}
