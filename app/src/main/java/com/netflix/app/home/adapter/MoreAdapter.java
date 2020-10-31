package com.netflix.app.home.adapter;

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

public class MoreAdapter extends RecyclerView.Adapter<MoreAdapter.MyViewHolder>{
    Context mcontext;
    List<MovieData> mlist;

    public MoreAdapter(Context mcontext, List<MovieData> mlist, MovieItemClickListener movieItemClickListener) {
        this.mcontext = mcontext;
        this.mlist = mlist;
        this.movieItemClickListener = movieItemClickListener;
    }

    MovieItemClickListener movieItemClickListener;


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mcontext).inflate(R.layout.item_movie,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int i) {
        holder.item_movie_title.setText(mlist.get(i).getTitle());
        holder.item_movie_img.setImageResource(mlist.get(i).getThumbnail());

    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        private ImageView item_movie_img;
        private TextView item_movie_title;


        public MyViewHolder(@NonNull View itemView) {

        super(itemView);
            item_movie_title = itemView.findViewById(R.id.item_movie_title);
            item_movie_img = itemView.findViewById(R.id.item_movie_img);
    }
}

}
