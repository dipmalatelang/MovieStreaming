package com.netflix.app.home.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.netflix.app.R;
import com.netflix.app.home.model.AllCategory;
import com.netflix.app.home.model.AllVideo;
import com.netflix.app.home.model.CategoryItem;
import com.netflix.app.home.model.VideoTypePojo;
import com.netflix.app.home.ui.HomeVideoPlay_Fragment;

import java.util.ArrayList;
import java.util.List;
import static androidx.constraintlayout.motion.widget.MotionScene.TAG;


public class MainRecyclerAdapter extends RecyclerView.Adapter<MainRecyclerAdapter.MainViewHolder> {

    private Context context;

    public MainRecyclerAdapter(Context context, List<VideoTypePojo> mlist, MovieItemClickListener movieItemClickListener) {
        this.context = context;
        this.mlist = mlist;
        this.movieItemClickListener = movieItemClickListener;
    }

    private List<VideoTypePojo> mlist;
    MovieItemClickListener movieItemClickListener;




    @NonNull
    @Override
    public MainViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MainViewHolder(LayoutInflater.from(context).inflate(R.layout.main_recycler_row_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MainViewHolder holder, int position) {

//        holder.categoryTitle.setText(mlist.get(position).getVideotype());
//        Log.d(TAG, "onBindViewHoldermainrecyclerview: "+mlist.get(position).getVideotype());
//        setCatItemRecycler(holder.itemRecycler, mlist.get(position).getVlist());
//        List<AllVideo> categoryItemList = new ArrayList<>();
//        for(int i =0 ;i<categoryItemList.size();i++){
//            if((categoryItemList.get(0).getVideoType().equalsIgnoreCase("MOVIE")))
//            {
//                holder.categoryTitle.setText(categoryItemList.get(position).getVideoType());
//
//            }
//
//            Log.d(TAG, "onBindViewHolder: "+mlist.get(position).getVideoType());
//        }
//        setCatItemRecycler(holder.itemRecycler, categoryItemList);
//
 }


    @Override
    public int getItemCount() {
        return mlist.size();
    }

    public static final class MainViewHolder extends RecyclerView.ViewHolder{

        TextView categoryTitle;
        RecyclerView itemRecycler;
        Button btn_SeeAll;

        public MainViewHolder(@NonNull View itemView) {
            super(itemView);

            categoryTitle = itemView.findViewById(R.id.cat_title);
            itemRecycler = itemView.findViewById(R.id.item_recycler);
            btn_SeeAll = itemView.findViewById(R.id.btn_SeeAll);

            btn_SeeAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: alllll");

                AppCompatActivity activity = (AppCompatActivity) itemView.getContext();
                Fragment myFragment = new HomeVideoPlay_Fragment();
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.rl_fragment_container, myFragment).addToBackStack(null).commit();

            }
        });



        }
    }



    private void setCatItemRecycler(RecyclerView recyclerView, List<AllVideo> categoryItemList){

        CategoryItemRecyclerAdapter itemRecyclerAdapter = new CategoryItemRecyclerAdapter(context, categoryItemList,movieItemClickListener);
        recyclerView.setLayoutManager(new LinearLayoutManager(context, RecyclerView.HORIZONTAL, false));
        recyclerView.setAdapter(itemRecyclerAdapter);

    }


}



