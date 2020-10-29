package com.netflix.app.home.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.netflix.app.R;
import com.netflix.app.home.model.AllCategory;
import com.netflix.app.home.model.CategoryItem;

import java.util.List;


public class MainRecyclerAdapter extends RecyclerView.Adapter<MainRecyclerAdapter.MainViewHolder> {

    private Context context;
    private List<AllCategory> allCategoryList;
    MovieItemClickListener movieItemClickListener;

    public MainRecyclerAdapter(Context context, List<AllCategory> allCategoryList, MovieItemClickListener movieItemClickListener) {
        this.context = context;
        this.allCategoryList = allCategoryList;
        this.movieItemClickListener = movieItemClickListener;
    }


    @NonNull
    @Override
    public MainViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MainViewHolder(LayoutInflater.from(context).inflate(R.layout.main_recycler_row_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MainViewHolder holder, int position) {

        holder.categoryTitle.setText(allCategoryList.get(position).getCategoryTitle());
        setCatItemRecycler(holder.itemRecycler, allCategoryList.get(position).getCategoryItemList());

    }


    @Override
    public int getItemCount() {
        return allCategoryList.size();
    }

    public static final class MainViewHolder extends RecyclerView.ViewHolder{

        TextView categoryTitle;
        RecyclerView itemRecycler;

        public MainViewHolder(@NonNull View itemView) {
            super(itemView);

            categoryTitle = itemView.findViewById(R.id.cat_title);
            itemRecycler = itemView.findViewById(R.id.item_recycler);


        }
    }

    private void setCatItemRecycler(RecyclerView recyclerView, List<CategoryItem> categoryItemList){

        CategoryItemRecyclerAdapter itemRecyclerAdapter = new CategoryItemRecyclerAdapter(context, categoryItemList,movieItemClickListener);
        recyclerView.setLayoutManager(new LinearLayoutManager(context, RecyclerView.HORIZONTAL, false));
        recyclerView.setAdapter(itemRecyclerAdapter);

    }
}
