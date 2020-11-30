package com.netflix.app.home.adapter;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.netflix.app.R;
import com.netflix.app.home.model.AllDataPojo;
import com.netflix.app.home.ui.MovieDetailActivity;
import com.netflix.app.utlis.SharedPrefs;


import java.util.List;

import static com.netflix.app.home.adapter.SliderPagerAdapter.VIDEO_BANNER_Name;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MyViewHolder> {

    Context mcontext;
    List<AllDataPojo> mlist;
    MovieItemClickListener movieItemClickListener;


    public MovieAdapter(Context mcontext, List<AllDataPojo> mlist, MovieItemClickListener listener) {
        this.mcontext = mcontext;
        this.mlist = mlist;
        movieItemClickListener = listener;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {


        View view = LayoutInflater.from(mcontext).inflate(R.layout.item_allvideos, viewGroup, false);
        return new MyViewHolder(view);


    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {


        myViewHolder.TvTitle.setText(mlist.get(i).getTitle());
        String allimage = (mlist.get(i).getThumbs());
        Log.d("TAG", "onBindViewHolderthumbs: " + allimage);
        Glide.with(mcontext).load(mlist.get(i).getThumbs()).into(myViewHolder.ImgMovie);
        SharedPrefs.getInstance().addString(VIDEO_BANNER_Name, mlist.get(i).getTitle());


    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {


        private TextView TvTitle;
        private ImageView ImgMovie;


        public MyViewHolder(@NonNull View itemView) {

            super(itemView);
            TvTitle = itemView.findViewById(R.id.item_movie_title);
            ImgMovie = itemView.findViewById(R.id.item_movie_img);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    movieItemClickListener.onMovieClick(mlist.get(getAdapterPosition()), ImgMovie);
                    Log.d("TAG", "getAdapterPosition: "+mlist.get(getAdapterPosition()));
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

                    // lets crezte the animation
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation((Activity) mcontext,
                            ImgMovie, "sharedName");
                    mcontext.startActivity(intent, options.toBundle());
                    // i l make a simple test to see if the click works

//                    Toast.makeText(getContext(), "item clicked : " + mlist.size(), Toast.LENGTH_LONG).show();
                    // it works great
                }
            });



        }
    }
}
