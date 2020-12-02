package com.netflix.app.home.adapter;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.netflix.app.R;
import com.netflix.app.home.model.AllDataPojo;
import com.netflix.app.home.model.MovieData;
import com.netflix.app.home.ui.MoreFragment;
import com.netflix.app.home.ui.MovieDetailActivity;
import com.netflix.app.home.ui.PlayMovieActivity;
import com.netflix.app.utlis.SharedPrefs;

import java.util.ArrayList;
import java.util.List;

import static com.netflix.app.home.adapter.SliderPagerAdapter.VIDEO_BANNER;
import static com.netflix.app.home.adapter.SliderPagerAdapter.VIDEO_BANNER_Name;

public class MoreAdapter extends RecyclerView.Adapter<MoreAdapter.MyViewHolder>{


    Context mcontext ;
    List<AllDataPojo> mlist ;
    String bannerurl, bannername ;

    public MoreAdapter(Context mcontext, List<AllDataPojo> mlist, MovieItemClickListener movieItemClickListener) {
        this.mcontext = mcontext;
        this.mlist = mlist;
        this.movieItemClickListener = movieItemClickListener;
    }

    MovieItemClickListener movieItemClickListener;

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mcontext).inflate(R.layout.item_allvideos,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int i) {
        holder.TvTitle.setText(mlist.get(i).getTitle());
        String allimage = (mlist.get(i).getThumbs());
        Log.d("TAG", "onBindViewHolderthumbs: " + allimage);
        Glide.with(mcontext).load(mlist.get(i).getThumbs()).into(holder.ImgMovie);
        SharedPrefs.getInstance().addString(VIDEO_BANNER_Name, mlist.get(i).getTitle());


    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView TvTitle;
        private ImageView ImgMovie;



        public MyViewHolder(@NonNull View itemView) {

            super(itemView);
            TvTitle = itemView.findViewById(R.id.item_movie_title);
            ImgMovie = itemView.findViewById(R.id.item_movie_img);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    mlist.get(getAdapterPosition()).getVdoUrl();
                    bannerurl = mlist.get(getAdapterPosition()).getVdoUrl();
                    bannername = mlist.get(getAdapterPosition()).getTitle();
                    SharedPrefs.getInstance().addString(VIDEO_BANNER, bannerurl);
                    SharedPrefs.getInstance().addString(VIDEO_BANNER_Name, bannername);
                    Log.d("TAG", "onClickvideo: " + bannername);
                    mcontext.startActivity(new Intent(mcontext, PlayMovieActivity.class));

                }
//                    movieItemClickListener.onMovieClick(mlist.get(getAdapterPosition()), ImgMovie);
//                    Log.d("TAG", "getAdapterPosition: " + mlist.get(getAdapterPosition()));
//                    Intent intent = new Intent(mcontext, MovieDetailActivity.class);
//                    // send movie information to deatilActivity
//                    intent.putExtra("title", mlist.get(getAdapterPosition()));
//                    intent.putExtra("imgURL", mlist.get(getAdapterPosition()));
//                    intent.putExtra("imgDescription", mlist.get(getAdapterPosition()));
//                    intent.putExtra("imginfo", mlist.get(getAdapterPosition()));
//                    intent.putExtra("videourl", mlist.get(getAdapterPosition()));
//                    intent.putExtra("genres", mlist.get(getAdapterPosition()));
//                    intent.putExtra("director", mlist.get(getAdapterPosition()));
//
//                    Log.d("TAG", "imgURL: " + mlist.get(getAdapterPosition()));
//
//                    // lets crezte the animation
//                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation((Activity) mcontext,
//                            ImgMovie, "sharedName");
//                    mcontext.startActivity(intent, options.toBundle());
//                    // i l make a simple test to see if the click works
//
////                    Toast.makeText(getContext(), "item clicked : " + mlist.size(), Toast.LENGTH_LONG).show();
//                    // it works great
//                }
            });
        }}}