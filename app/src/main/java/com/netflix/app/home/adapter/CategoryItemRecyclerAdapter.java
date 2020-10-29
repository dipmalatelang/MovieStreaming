package com.netflix.app.home.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.netflix.app.R;
import com.netflix.app.home.model.CategoryItem;

import java.util.List;


public class CategoryItemRecyclerAdapter extends RecyclerView.Adapter<CategoryItemRecyclerAdapter.CategoryItemViewHolder> {

     Context context;
     List<CategoryItem> categoryItemList;

    public CategoryItemRecyclerAdapter(Context context, List<CategoryItem> categoryItemList, MovieItemClickListener movieItemClickListener) {
        this.context = context;
        this.categoryItemList = categoryItemList;
        this.movieItemClickListener = movieItemClickListener;
    }

    MovieItemClickListener movieItemClickListener;
    
    






    @NonNull
    @Override
    public CategoryItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.category_row_items,parent,false);
          return new CategoryItemViewHolder(view);    }

    @Override
    public void onBindViewHolder(@NonNull CategoryItemViewHolder holder, int position) {

            holder.itemImage.setImageResource(categoryItemList.get(position).getImageUrl());
    }

    @Override
    public int getItemCount() {
        return categoryItemList.size();
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
