package com.netflix.app.home.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.netflix.app.R;
import com.netflix.app.home.model.AllVideo;
import com.netflix.app.home.model.VideoTypePojo;

import java.util.List;


public class CategoryItemRecyclerAdapter extends RecyclerView.Adapter<CategoryItemRecyclerAdapter.CategoryItemViewHolder> {

     Context context;
     List<AllVideo> categoryItemList;
    MovieItemClickListener movieItemClickListener;


    public CategoryItemRecyclerAdapter(Context context, List<AllVideo> categoryItemList, MovieItemClickListener movieItemClickListener) {
        this.context = context;
        this.categoryItemList = categoryItemList;
        this.movieItemClickListener = movieItemClickListener;
    }





    @NonNull
    @Override
    public CategoryItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.category_row_items,parent,false);
          return new CategoryItemViewHolder(view);    }

    @Override
    public void onBindViewHolder(@NonNull CategoryItemViewHolder holder, int position) {


        Glide.with(context).load(categoryItemList.get(position).getThumbs()).into(holder.itemImage);
        Log.d("TAG", "NNNNNNNNNN: "+categoryItemList.get(position).getThumbs());

    }

    @Override
    public int getItemCount() {
//        return categoryItemList.size();
        return 0;
    }

    public  final class CategoryItemViewHolder extends RecyclerView.ViewHolder{

        ImageView itemImage;


        public CategoryItemViewHolder(@NonNull View itemView) {
            super(itemView);

            itemImage = itemView.findViewById(R.id.item_image);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    movieItemClickListener.onMovieClick(categoryItemList.get(getAdapterPosition()),itemImage);


                }
            });

        }
    }

}
