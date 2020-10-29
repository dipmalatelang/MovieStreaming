package com.netflix.app.home.adapter;

import android.widget.ImageView;

import com.netflix.app.home.model.AllCategory;
import com.netflix.app.home.model.CategoryItem;
import com.netflix.app.home.model.MovieData;

public interface MovieItemClickListener {

    void onMovieClick(CategoryItem movie, ImageView movieImageView); // we will need the imageview to make the shared animation between the two activity

}
