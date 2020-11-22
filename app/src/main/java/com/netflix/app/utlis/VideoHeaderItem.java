package com.netflix.app.utlis;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.netflix.app.R;

import com.netflix.app.home.adapter.VideoArrayAdapter.ItemType;
import com.netflix.app.home.model.AllVideo;

public class VideoHeaderItem implements  VideoTypeItem{


    AllVideo allVideo;
    Context context;

    public VideoHeaderItem(AllVideo allVideo, Context context) {
        this.allVideo = allVideo;
        this.context = context;
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
        Glide.with(context).load(allVideo.getThumbs()).into(item_image);




        return v;
    }
}
