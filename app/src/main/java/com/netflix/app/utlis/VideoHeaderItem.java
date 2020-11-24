package com.netflix.app.utlis;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.netflix.app.R;

import com.netflix.app.home.adapter.VideoArrayAdapter.ItemType;
import com.netflix.app.home.model.AllVideo;

import java.util.ArrayList;

public class VideoHeaderItem implements  VideoTypeItem{


    ArrayList<AllVideo> allVideo;
    Context context;

    public VideoHeaderItem(ArrayList<AllVideo> allVideo, Context context) {
        this.allVideo = allVideo;
        this.context = context;
        Log.d("TAG", "getViewVVVVVVVVVVV: "+allVideo.get(0).getThumbs());
    }

    @Override
    public int getViewType() {
        return ItemType.HEADER_ITEM.ordinal();
    }

    @Override
    public View getView(LayoutInflater layoutInflater, View view) {
        View v ;
        if(view == null){
            v = layoutInflater.inflate(R.layout.main_recycler_row_item,null);
        } else {
            v = view;

        }
        ImageView item_image = v.findViewById(R.id.item_image);
        for (int i = 0; i <allVideo.size() ; i++) {
            Glide.with(context).load(allVideo.get(i).getThumbs()).into(item_image);
            Log.d("TAG", "getViewVVVVVVVVVVV: "+allVideo.get(i).getThumbs());

        }




        return v;
    }
}
