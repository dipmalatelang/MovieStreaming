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

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.netflix.app.R;
import com.netflix.app.home.model.AllDataPojo;
import com.netflix.app.home.ui.MovieDetailActivity;


import java.util.ArrayList;
import java.util.List;


public class CatWebseriesAdapter extends RecyclerView.Adapter<CatWebseriesAdapter.MyViewHolder> {

    Context mcontext ;
    List<AllDataPojo> mlist ;
    MovieItemClickListener movieItemClickListener;

    public CatWebseriesAdapter(Context mcontext, List<AllDataPojo> mlist, MovieItemClickListener movieItemClickListener) {
        this.mcontext = mcontext;
        this.mlist = mlist;
        this.movieItemClickListener = movieItemClickListener;
    }




    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mcontext).inflate(R.layout.main_recycler_row_item,parent,false);
        return new MyViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Glide.with(mcontext).load(mlist.get(position).getThumbs()).into(holder.item_image);
        Log.d("TAG", "NNNNNNNNNN: "+mlist.get(position).getThumbs());
        Log.i("TAG", "VVVVVVVVVVVVVVVVVVVVV: "+mlist.get(position).getVideoType());

    }


    @Override
    public int getItemCount() {
        return mlist.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView item_image;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            item_image = itemView.findViewById(R.id.item_image);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ArrayList<AllDataPojo> allVideoArrayList = new ArrayList<>();
                    movieItemClickListener.onMovieClick(mlist.get(getAdapterPosition()), item_image);

                    Intent intent = new Intent(mcontext, MovieDetailActivity.class);
                    // send movie information to deatilActivity
                    intent.putExtra("title", mlist.get(getAdapterPosition()));
                    intent.putExtra("imgURL", mlist.get(getAdapterPosition()));
                    intent.putExtra("imgDescription", mlist.get(getAdapterPosition()));
                    intent.putExtra("imginfo", mlist.get(getAdapterPosition()));
                    intent.putExtra("videourl", mlist.get(getAdapterPosition()));
                    intent.putExtra("genres", mlist.get(getAdapterPosition()));
                    intent.putExtra("director", mlist.get(getAdapterPosition()));
                    Log.d("TAG", "imgURL: "+mlist.get(getAdapterPosition()));

                    Bundle bundle = new Bundle();
                    Log.d("TAG", "getAdapterPosition: "+mlist.get(getAdapterPosition()).getPartNumber());
                    bundle.putParcelableArrayList("allepisodelist",allVideoArrayList);

                    // lets crezte the animation
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation((Activity) mcontext,
                            item_image, "sharedName");
                    mcontext.startActivity(intent, options.toBundle());

                }
            });


        }}}

