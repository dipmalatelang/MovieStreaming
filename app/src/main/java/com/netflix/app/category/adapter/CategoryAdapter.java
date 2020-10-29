package com.netflix.app.category.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.netflix.app.R;
import com.netflix.app.home.model.MovieData;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.MyViewHolder> {

    Context mcontext ;
    List<MovieData> mlist;


    public CategoryAdapter(Context mcontext, List<MovieData> mlist) {
        this.mcontext = mcontext;
        this.mlist = mlist;

    }



    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {


        View view = LayoutInflater.from(mcontext).inflate(R.layout.item_category,viewGroup,false);
        return new MyViewHolder(view);


        }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {


        myViewHolder.TvTitle.setText(mlist.get(i).getTitle());
        myViewHolder.ImgMovie.setImageResource(mlist.get(i).getThumbnail());


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

//                    movieItemClickListener.onMovieClick(mlist.get(getAdapterPosition()),ImgMovie);


                }
            });

        }
    }
}
