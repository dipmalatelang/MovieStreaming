package com.netflix.app.home.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.netflix.app.R;
import com.netflix.app.home.model.AllDataPojo;


import java.util.List;


public class CatWebseriesAdapter extends RecyclerView.Adapter<CatWebseriesAdapter.MyViewHolder> {
    Context mcontext ;
    List<AllDataPojo> mlist ;

    public CatWebseriesAdapter(Context mcontext, List<AllDataPojo> mlist, MovieItemClickListener movieItemClickListener) {
        this.mcontext = mcontext;
        this.mlist = mlist;
        this.movieItemClickListener = movieItemClickListener;
    }

    MovieItemClickListener movieItemClickListener;


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


    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView categoryTitle;
        ImageView item_image;

        Button btn_SeeAll;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
//            categoryTitle = itemView.findViewById(R.id.cat_title);
            item_image = itemView.findViewById(R.id.item_image);
//            btn_SeeAll = itemView.findViewById(R.id.btn_SeeAll);
//
//            btn_SeeAll.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Log.d(TAG, "onClick: alllll");
//
//                    AppCompatActivity activity = (AppCompatActivity) itemView.getContext();
//                    Fragment myFragment = new HomeVideoPlay_Fragment();
//                    activity.getSupportFragmentManager().beginTransaction().replace(R.id.rl_fragment_container, myFragment).addToBackStack(null).commit();
//
//                }
//            });

        }}}

